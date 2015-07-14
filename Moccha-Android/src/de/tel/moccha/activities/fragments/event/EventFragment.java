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
package de.tel.moccha.activities.fragments.event;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tel.moccha.activities.R;
import de.tel.moccha.activities.fragments.course.CourseFragment;
import de.tel.moccha.entities.event.Event;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class EventFragment extends EntityFragment {

  @Override
  protected void showEntity(Entity entity) {
    Event event = (Event) entity;
    if (event == null) {
      return;
    }

    View root = this.getView();
    if (root == null) {
      return;
    }

    getTextView(root, R.id.event_header_info)
            .setText(event.getTitle());
    getTextView(root, R.id.event_description)
            .setText(event.getDescription());
    getTextView(root, R.id.event_organizer)
            .setText(event.getOrganizer());
    
    getTextView(root, R.id.event_start)
            .setText(formatDateString(event.getStart()));
    getTextView(root, R.id.event_period)
            .setText(event.getPeriod());
  }

  private String formatDateString(String dateStr) {
    if (dateStr == null)
      return dateStr;
    
    SimpleDateFormat parsedFormat = new SimpleDateFormat(getString(R.string.time_pattern));
    Date d = null;
    try {
      d = parsedFormat.parse(dateStr);
    } catch (ParseException ex) {
      Logger.getLogger(EventFragment.class.getName()).log(Level.SEVERE, null, ex);
    }
    SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.event_time_pattern));
    return sdf.format(d);
  }
  
  @Override
  protected void restoreInstance(Bundle values) {
    //do nothing
  }

  @Override
  protected void loadEntity() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        entity = JSONUnmarshaller.unmarshall(response, Event.class);
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
  protected void postRestore() {
    //do nothing
  }
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_event, container, false);
    return rootView;
  }

  
}
