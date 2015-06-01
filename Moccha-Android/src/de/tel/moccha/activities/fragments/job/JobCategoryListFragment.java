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
package de.tel.moccha.activities.fragments.job;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import de.tel.moccha.activities.MoCChaMainNavigationActivity;
import de.tel.moccha.activities.fragments.CategoryListFragment;
import de.tel.moccha.activities.fragments.adapters.job.JobCategoryListAdapter;
import de.tel.moccha.entities.job.JobCategory;
import de.zell.android.util.PropertiesProvider;
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
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobCategoryListFragment extends EntityListFragment {

  public static final String JSON_JOB_DATABASE_KEY = "jobDatabase";

  public Bundle getArgumentsForFragment(Entity e) {
    Bundle args = new Bundle();
    PropertiesProvider provider = MoCChaMainNavigationActivity.propProvider;
    if (provider != null) {
      String jobDetailUrl = provider.getProperty(MoCChaMainNavigationActivity.PROP_KEY_JOBS_DETAIL_URL);
      jobDetailUrl = String.format(jobDetailUrl, e.getID());
      args.putCharSequence(JobDetailFragment.ARG_ENTITY_URL, jobDetailUrl);
    }
    return args;
  }

  public Fragment getOnClickFragment() {
    return new JobDetailFragment(); //TODO job detail fragment
  }

  @Override
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new JobCategoryListAdapter(ctx);
  }

  @Override
  protected void loadEntities() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        if (entities == null) {
          entities = new ArrayList<Entity>();
        }

        try {
          response = response.getJSONObject(JSON_JOB_DATABASE_KEY);
          if (response != null) {
            JSONArray categories = response.getJSONArray(CategoryListFragment.JSON_CATEGORY_KEY);
            if (categories != null) {
              int len = categories.length();
              for (int i = 0; i < len; i++) {
                JSONObject obj = categories.getJSONObject(i);
                entities.add(JSONUnmarshaller.unmarshall(obj, JobCategory.class));
              }
            }
          }
        } catch (JSONException ex) {
          Log.e(CategoryListFragment.class.getName(), ex.getMessage(), ex);
        }

        JobCategoryListAdapter adapter = (JobCategoryListAdapter) getListAdapter();
        adapter.setEntities(entities);
        adapter.notifyDataSetChanged();
      }

      public void doExeptionHandling(Throwable t) {
        if (t != null) {
          Log.e(CategoryListFragment.class.getName(), t.getMessage(), t);
        }
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
  protected void onEntityClick(Entity e) {
    Fragment frg = getOnClickFragment();
    if (frg != null) {
      frg.setArguments(getArgumentsForFragment(e));
      FragmentReplacer.replace(getActivity().getSupportFragmentManager(),
                              frg,
                               FragmentReplacer.MAIN_CONTENT);
    }
  }

  @Override
  protected void onSectionClick(Object o) {
    //nothing to do
  }
  
  

}
