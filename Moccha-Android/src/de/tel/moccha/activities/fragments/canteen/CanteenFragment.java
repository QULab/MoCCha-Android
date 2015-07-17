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
package de.tel.moccha.activities.fragments.canteen;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.canteen.Canteen;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import de.zell.android.util.fragments.FragmentReplacer;
import de.zell.android.util.fragments.InfoMenuFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import org.json.JSONObject;

/**
 * Represents the CateenFragment which shows the information for a canteen.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CanteenFragment extends InfoMenuFragment {

  /**
   * The argument key of the fragment.
   */
  public static final String ARG_CANTEEN_URL = "canteen.url";
  
  /**
   * The tag of the canteen view.
   */
  private static final String TAG_CANTEEN = "canteen";
  
  /**
   * The url of the canteen entity.
   */
  private String url;
  
  /**
   * The canteen object which contains the canteen informations.
   */
  private Canteen c;
  
  /**
   * Represents the canteen info dialog, which shows the details of the canteen.
   */
  private CanteenInfoDialogFragment info;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }

    if (savedInstanceState != null) {
      c = (Canteen) savedInstanceState.getSerializable(TAG_CANTEEN);

      if (c == null) {
        url = savedInstanceState.getString(ARG_CANTEEN_URL);
        loadCanteen();
      } else
        showCanteen(c);
    }
  }
  
  /**
   * Loads the canteen information from the canteen resource which
   * is identified via the url.
   */
  public void loadCanteen() {
    AsyncGETRequester get = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {

      public void doJob(JSONObject response) {
        c = JSONUnmarshaller.unmarshall(response, Canteen.class);
        showCanteen(c);
      }

      public void doExeptionHandling(Throwable t) {
        //TODO
      }

      public void handleNewEtag(String url, String newEtag) {
        //TODO
      }
    });
   get.showProgress(((MainNavigationActivity) getActivity()).getProgressBar());
   get.execute(new GetRequestInfo(url, null));
  }
  
  /**
   * Shows the canteen informations in the views which are available in the fragment.
   * 
   * @param c the canteen object which contains the informations
   */
  private void showCanteen(Canteen c) {
    if (c == null)
      return;
    
    getActivity().setTitle(c.getTitle());
    Bundle args = new Bundle();
    args.putSerializable(EntityViewPagerFragment.ARG_ENTITY, c);
    Fragment frg = new WeekCanteenPagerFragment();
    frg.setArguments(args);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(), frg, R.id.canteen_diet, false);
    
    setInfoDialogArguments();
  }
  
  /**
   * Adds the initialized canteen as argument to the dialog fragment.
   * So the info dialog can show the needed information.
   */
  private void setInfoDialogArguments() {
    if (c != null) {
      Bundle args = new Bundle();
      args.putSerializable(CanteenInfoDialogFragment.ARG_CANTEEN_INFO, c);
      
      getInfoDialog().setArguments(args);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_canteen, container, false);
    return rootView;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_CANTEEN, c);
  }

  @Override
  protected DialogFragment getInfoDialog() {
    if (info == null)
      info = new CanteenInfoDialogFragment();
    return info;
  }

  @Override
  public void onResume() {
    super.onResume();
    showCanteen(c);
  }
}
