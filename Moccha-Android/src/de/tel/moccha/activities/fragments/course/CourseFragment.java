/*
 * Copyright 2015 Quality and Usability Lab, Telekom Innvation Laboratories, TU Berlin..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tel.moccha.activities.fragments.course;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.course.Course;
import de.tel.moccha.entities.course.CourseDate;
import de.tel.moccha.entities.course.GroupDate;
import de.tel.moccha.entities.course.Person;
import de.tel.moccha.entities.course.Responsibility;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * Represents the course fragment which shows the details of a course.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseFragment extends EntityFragment {

  @Override
  protected void restoreInstance(Bundle values) {
    // do nothing
  }

  @Override
  protected void loadEntity() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        entity = JSONUnmarshaller.unmarshall(response, Course.class);
        showEntity(entity);
      }

      public void doExeptionHandling(Throwable t) {
        Log.e(CourseFragment.class.getName(), t.getMessage(), t);
      }

      public void handleNewEtag(String url, String newEtag) {
        //TODO
      }
    });
    request.showProgress(((MainNavigationActivity) getActivity()).getProgressBar());
    GetRequestInfo info = new GetRequestInfo(getURL(), null);
    request.execute(info);
  }

  @Override
  protected void showEntity(Entity entity) {
    Course c = (Course) entity;
    if (c == null) {
      return;
    }

    View root = this.getView();
    if (root == null) {
      return;
    }

    getTextView(root, R.id.course_header_info)
            .setText(c.getTitle());
    getTextView(root, R.id.course_content)
            .setText(c.getContent());
    getTextView(root, R.id.course_kind)
            .setText(c.getKindOfEvent());

    showCourseResponsibilities(c, root);
    showCourseInfo(c, root);
    getTextView(root, R.id.course_comment)
            .setText(c.getComment());

    showCourseGroupDates(c, root);
  }

  /**
   * The course responsibilities are added to the corresponding text view and
   * showed to the user.
   *
   * @param course the course which contains the information
   * @param root the root view which contains the text views
   */
  private void showCourseResponsibilities(Course course, View root) {
    List<Responsibility> responsibilities = course.getResponsibilities();
    StringBuilder responsibilitiesBuilder = new StringBuilder();
    for (Responsibility responsibility : responsibilities) {
      Person person = responsibility.getPerson();
      if (person != null) {
        StringBuilder personBuilder = new StringBuilder();
        addContentToStringBuilder(person.getTitle(), personBuilder);
        personBuilder.append(" ");
        addContentToStringBuilder(person.getPrename(), personBuilder);
        personBuilder.append(" ");
        addContentToStringBuilder(person.getLastname(), personBuilder);
        addContentToStringBuilder(personBuilder.toString(), responsibilitiesBuilder);
        responsibilitiesBuilder.append("\n");
      }
    }
    getTextView(root, R.id.course_responsibilities).setText(responsibilitiesBuilder.toString());
  }

  /**
   * The course informations are added to the corresponding information text
   * view and are shown to the user.
   *
   * @param course the course which contains the information
   * @param root the root view which contains the info text view
   */
  private void showCourseInfo(Course course, View root) {
    StringBuilder builder = new StringBuilder();
    addContentToStringBuilder(course.getHyperlink(), builder);
    builder.append("\n");
    addContentToStringBuilder(course.getSemester(), builder);
    builder.append("\n");
    addContentToStringBuilder(course.getRhythmus(), builder);
    builder.append("\n");
    getTextView(root, R.id.course_info)
            .setText(builder.toString());
  }

  private void showCourseGroupDates(Course course, View root) {
    StringBuilder grpDatesBuilder = new StringBuilder();
    List<GroupDate> groupDates = course.getGroupDates();
    for (GroupDate groupDate : groupDates) {
      List<CourseDate> courseDates = groupDate.getDates();
      addContentToStringBuilder(groupDate.getName(), grpDatesBuilder);
      for (CourseDate date : courseDates) {
        StringBuilder builder = new StringBuilder("\n\t");
        addContentToStringBuilder(getString(R.string.course_iteration), builder);
        addContentToStringBuilder(date.getIteration(), builder);
        builder.append("\n\t");
        addContentToStringBuilder(getDateString(date.getFrom()), builder);
        addContentToStringBuilder(getString(R.string.until), builder);
        addContentToStringBuilder(getDateString(date.getUntil()), builder);
        builder.append("\n\t");
        addContentToStringBuilder(date.getTimeFrom(), builder);
        addContentToStringBuilder(getString(R.string.until), builder);
        addContentToStringBuilder(date.getTimeUntil(), builder);
        builder.append("\n\t");
        addContentToStringBuilder(getString(R.string.course_max_members), builder);
        Integer maxCount = date.getMaxMembers();
        addContentToStringBuilder(maxCount == null
                ? getString(R.string.course_max_members_no_value)
                : maxCount.toString(), builder);
        addContentToStringBuilder(builder.toString(), grpDatesBuilder);
      }
    }
    getTextView(root, R.id.course_group_dates).setText(grpDatesBuilder.toString());
  }

  private String getDateString(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.time_pattern));
    String dateStr = "";
    if (date != null && !date.isEmpty()) {
      try {
        Date d = sdf.parse(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_pattern));
        if (d != null) {
          dateStr = dateFormat.format(d);
        }
      } catch (ParseException ex) {
        Logger.getLogger(CourseFragment.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return dateStr;
  }

  /**
   * Adds the content to the builder. Before adding it checks the content if its
   * not null and not empty the content will be added to the string builder.
   *
   * @param content the content which should be added
   * @param builder
   */
  private void addContentToStringBuilder(String content, StringBuilder builder) {
    if (content != null && !content.isEmpty()) {
      builder.append(content);
    }
  }

  @Override
  protected void postRestore() {
    //do nothing
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_course, container, false);
    return rootView;
  }

}
