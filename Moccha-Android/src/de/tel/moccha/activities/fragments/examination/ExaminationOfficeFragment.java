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
package de.tel.moccha.activities.fragments.examination;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.office.ExaminationOffice;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import de.zell.android.util.fragments.FragmentReplacer;
import de.zell.android.util.fragments.InfoMenuFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ExaminationOfficeFragment extends InfoMenuFragment {
  
  /**
   * The argument key of the fragment.
   */
  public static final String ARG_EXAMINATION_OFFICE_URL = "office.examination.url";
  
  /**
   * The tag of the examination office view.
   */
  private static final String TAG_EXAMINATION_OFFICE = "examination";
  
  /**
   * The url of the examination office entity.
   */
  private String url;
  
  /**
   * The examination office object which contains the office informations.
   */
  private ExaminationOffice office;
  
  /**
   * Represents the canteen info dialog, which shows the details of the canteen.
   */
  private ExaminationInfoDialogFragment info;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }

    if (savedInstanceState != null) {
      office = (ExaminationOffice) savedInstanceState.getSerializable(TAG_EXAMINATION_OFFICE);
      
      if (office == null) {
        url = savedInstanceState.getString(ARG_EXAMINATION_OFFICE_URL);
        loadOffice();
      } else
        showOffice(office);
    }
  }
  
  /**
   * Loads the office information from the examination office resource which
   * is identified via the url.
   */
  public void loadOffice() {
    AsyncGETRequester get = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {

      public void doJob(JSONObject response) {
        office = JSONUnmarshaller.unmarshall(response, ExaminationOffice.class);
        showOffice(office);
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
   * Shows the office informations in the views which are available in the fragment.
   * 
   * @param o the office object which contains the informations
   */
  private void showOffice(ExaminationOffice o) {
    if (o == null)
      return;
    
    getActivity().setTitle(getString(R.string.examination_office));
    Bundle args = new Bundle();
    args.putSerializable(EntityViewPagerFragment.ARG_ENTITY, office);
    Fragment frg = new WaitingTableViewPager();
    frg.setArguments(args);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(), frg, R.id.examination_office_tables, false);
    setInfoDialogArguments();
  }
  
  /**
   * Adds the initialized canteen as argument to the dialog fragment.
   * So the info dialog can show the needed information.
   */
  private void setInfoDialogArguments() {
    if (office != null) {
      Bundle args = new Bundle();
      args.putSerializable(ExaminationInfoDialogFragment.ARG_OFFICE_INFO, office);
      getInfoDialog().setArguments(args);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_examination_office, container, false);
    return rootView;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_EXAMINATION_OFFICE, office);
  }

  @Override
  protected DialogFragment getInfoDialog() {
    if (info == null)
      info = new ExaminationInfoDialogFragment();
    return info;
  }

  @Override
  public void onResume() {
    super.onResume();
    showOffice(office);
  }
  
}
