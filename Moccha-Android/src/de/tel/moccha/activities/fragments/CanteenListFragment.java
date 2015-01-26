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
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import de.tel.moccha.entities.Canteen;
import de.zell.android.util.EntityListAdapter;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CanteenListFragment extends ListFragment {

  public static final String JSON_CANTEENS_KEY = "canteens";
  public static final String JSON_CANTEEN_ID_KEY = "id";
  public static final String JSON_CANTEEN_TITLE_KEY = "title";
  public static final String JSON_CANTEEN_URI_KEY = "uri";
  public static final String JSON_CANTEEN_UNI_KEY = "university";
  public static final String JSON_CANTEEN_LONG_KEY = "longitude";
  public static final String JSON_CANTEEN_LAT_KEY = "latitude";
  
  
  private int index = -1;
  private int top = 0;
  public static final String ARG_CANTEENS_URL = "canteens.url";
  private static final String TAG_CANTEENS_CONTENT = "canteens.content";
  private ArrayList<Canteen> canteens = new ArrayList<Canteen>();
  private String uri;

  @Override
  public void onResume() {
    super.onResume();
    if (index != -1) {
      this.getListView().setSelectionFromTop(index, top);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    index = this.getListView().getFirstVisiblePosition();
    View v = this.getListView().getChildAt(0);
    top = (v == null) ? 0 : v.getTop();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (null == savedInstanceState) {
      savedInstanceState = getArguments();
    }

    if (null != savedInstanceState) {
      canteens = (ArrayList<Canteen>) savedInstanceState.getSerializable(TAG_CANTEENS_CONTENT);
    }

    if (null == canteens) {
      uri = (String) savedInstanceState.getSerializable(ARG_CANTEENS_URL);
    }


    loadCanteens();
    setListAdapter(new EntityListAdapter((List) canteens, getActivity()));
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Fragment frg = null;
    Bundle args = new Bundle();
    Toast.makeText(getActivity(), canteens.get(position).toString(), Toast.LENGTH_LONG).show();
//
//    if (frg != null) {
//      frg.setArguments(args);
//      FragmentManager mgr = ((FragmentActivity) getActivity()).getSupportFragmentManager();
//      Fragment old = mgr.findFragmentById(R.id.content_frame);
//
//      FragmentTransaction trx = mgr.beginTransaction();
//      if (old != null) {
//        trx.remove(old);
//      }
//
//      trx.add(R.id.content_frame, frg)
//              .addToBackStack(null) //TODO
//              .commit();
//
//    }
//    
  }

  protected void loadCanteens() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        if (canteens == null)
          canteens = new ArrayList<Canteen>();
        
        try {
          //TODO remove String keys
          JSONArray cs = response.getJSONArray(JSON_CANTEENS_KEY);
          int len = cs.length();
          for (int i = 0; i < len; i++) {
            JSONObject obj = cs.getJSONObject(i);
            Canteen canteen = new Canteen(obj.getInt(JSON_CANTEEN_ID_KEY),
                    obj.getString(JSON_CANTEEN_TITLE_KEY),
                    obj.getString(JSON_CANTEEN_URI_KEY),
                    obj.getString(JSON_CANTEEN_UNI_KEY),
                    obj.getLong(JSON_CANTEEN_LONG_KEY),
                    obj.getLong(JSON_CANTEEN_LAT_KEY));
            canteens.add(canteen);
          }

        } catch (JSONException ex) {
          Log.e(CanteenListFragment.class.getName(), ex.getMessage(), ex);
        }

        EntityListAdapter adapter = (EntityListAdapter) getListAdapter();
        adapter.setResults(canteens);
        adapter.notifyDataSetChanged();
      }

      public void doExeptionHandling(Throwable t) {
        Log.e(CanteenListFragment.class.getName(), t.getMessage(), t);
      }

      public void handleNewEtag(String url, String newEtag) {
        //TODO
      }
    });

    GetRequestInfo info = new GetRequestInfo(uri, null);
    request.execute(info);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_CANTEENS_CONTENT, canteens);
  }
}
