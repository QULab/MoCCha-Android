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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import de.tel.moccha.activities.fragments.CategoryListFragment;
import de.tel.moccha.activities.fragments.adapters.event.EventListAdapter;
import de.tel.moccha.entities.event.Event;
import de.tel.moccha.entities.event.Events;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.ActionBarTitleManager;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.FragmentReplacer;
import de.zell.android.util.json.JSONUnmarshaller;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class EventListFragment extends EntityListFragment {

  
  @Override
  protected void onEntityClick(Entity e) {
    Event event = (Event) e;
    Bundle b = new Bundle();
    b.putString(EntityFragment.ARG_ENTITY_URL, event.getUri());
    b.putString(ActionBarTitleManager.ARG_ACTION_BAR_TITLE, event.getTitle());
    Fragment frg = new EventFragment();
    frg.setArguments(b);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(),
                             frg,
                             FragmentReplacer.MAIN_CONTENT);
  }

  @Override
  protected void onSectionClick(Object o) {
    //do nothing
  }

  @Override
  protected void loadEntities() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        if (entities == null)
          entities = new ArrayList<Entity>();
        
        Events events = JSONUnmarshaller.unmarshall(response, Events.class);
        List<Event> e = events.getEvents();
        if (e != null)
          entities.addAll(e);
        
        EntityListAdapter adapter = (EntityListAdapter) getListAdapter();
        adapter.setEntities(entities);
        adapter.notifyDataSetChanged();
      }

      public void doExeptionHandling(Throwable t) {
        if (t != null)
          Log.e(CategoryListFragment.class.getName(), t.getMessage(), t);
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
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new EventListAdapter(ctx);
  }
  
}
