/*
 * Copyright 2014 Quality and Usability Lab, Telekom Innvation Laboratories, TU Berlin..
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
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.zell.android.util.fragments.ActionBarManagerFragment;

/**
 * Represents the Fragment for the welcome page.
 * 
 * Created by deLaczkovich on 26-Aug-14.
 * @author deLaczkovich, Christopher Zell <zelldon91@googlemail.com>
 */
public class WelcomeFragment extends ActionBarManagerFragment {

  /**
   * The info menu item.
   */
  protected MenuItem infoMenuItem;
  
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.application_info, menu);
    infoMenuItem = menu.findItem(R.id.action_app_info);
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().invalidateOptionsMenu();
  }
  
  
  @Override
  public boolean onOptionsItemSelected(MenuItem favItem) {
    if (favItem.getItemId() == R.id.action_app_info) {
      new ApplicationInfoDialog().show(getActivity().getSupportFragmentManager(), null);
    }
    return super.onOptionsItemSelected(favItem);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
    LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.welcome_main_ll);
    
    addTextViewToLayout(getString(R.string.welcome_intro_description), ll);
    return rootView;
  }
  
  
  private void addTextViewToLayout(String content, LinearLayout ll) {
    TextView text = new TextView(getActivity());
    text.setAutoLinkMask(Linkify.ALL);
    text.setText(content);
    ll.addView(text);
  }
}
