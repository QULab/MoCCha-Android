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
package de.tel.moccha.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;
import de.tel.moccha.activities.fragments.course.MainCourseCategoryFragment;
import de.tel.moccha.entities.Category;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.FragmentReplacer;
import de.zell.android.util.json.JSONUnmarshaller;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the category list fragment which shows the
 * current categories.
 * 
 * For example different universities or event categories.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public abstract class CategoryListFragment extends EntityListFragment {

  /**
   * The json key to extract the categories json array.
   */
  public static final String JSON_CATEGORY_KEY = "categories";
  
  @Override
  protected void onEntityClick(Entity e) {
    Category c = (Category) e;
    Bundle b = new Bundle();
    b.putString(MainCourseCategoryFragment.ARG_ENTITY_URL, c.getUrl());
    Fragment frg = new MainCourseCategoryFragment();
    frg.setArguments(b);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(),
                             frg,
                             FragmentReplacer.MAIN_CONTENT);
    Toast.makeText(getActivity(), e.getID().toString(), Toast.LENGTH_LONG).show();
    
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
        
        try {
          JSONArray categories = response.getJSONArray(JSON_CATEGORY_KEY);
          int len = categories.length();
          for (int i = 0; i < len; i++) {
            JSONObject obj = categories.getJSONObject(i);
            entities.add(JSONUnmarshaller.unmarshall(obj, Category.class));
          }
        } catch (JSONException ex) {
          Log.e(CategoryListFragment.class.getName(), ex.getMessage(), ex);
        }

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
  
}
