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
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.course.Course;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import org.json.JSONObject;

/**
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
          showCourse((Course) entity);
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

  private void showCourse(Course c) {
    if (c == null)
      return;
    
    View root = this.getView();
    getTextView(root, R.id.course_header_info)
            .setText(c.getTitle());
    getTextView(root, R.id.course_comment)
            .setText(c.getComment());
    getTextView(root, R.id.course_content)
            .setText(c.getContent());
    getTextView(root, R.id.course_kind)
            .setText(c.getKindOfEvent());
    
    StringBuilder builder = new StringBuilder();
    builder.append(c.getHyperlink()).append(" ").append(c.getSemester()).append(" ").append(c.getRhythmus());
    getTextView(root, R.id.course_info)
            .setText(builder.toString());
  }
  
  
  /**
   * Returns the text view for the given id from the root view.
   * 
   * @param root the root view
   * @param id the id which identifies the view
   * @return the corresponding view
   */
  private TextView getTextView(View root, int id) {
    return ((TextView) root.findViewById(id));
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
