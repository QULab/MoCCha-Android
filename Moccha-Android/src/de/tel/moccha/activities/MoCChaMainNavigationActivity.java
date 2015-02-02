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
package de.tel.moccha.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import de.tel.moccha.activities.fragments.CanteenListFragment;
import de.tel.moccha.activities.fragments.WelcomeFragment;
import de.zell.android.util.PropertiesProvider;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.fragments.EntityListFragment;

/**
 * Represents the main navigation activity with a navigation drawer. On the left
 * side of the drawer displays the navigation to other existing views. If some
 * of them are selected the current fragment are replaced with the selected view
 * (respectively fragment). That means it exists only one main fragment in the
 * center which will be replaced every time.
 *
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class MoCChaMainNavigationActivity extends MainNavigationActivity {

  
  public static final String PROP_FILE = "moccha.properties";
  public static final PropertiesProvider propProvider = PropertiesProvider.getInstance();
  public static final String PROP_KEY_CANTEEN_URL = "canteen.url";
  
  static {
    PropertiesProvider.setPropertyFile(MoCChaMainNavigationActivity.class, PROP_FILE);
  }
  private final Fragment[] fragments = {new WelcomeFragment(), createCanteenListFragment()};
  private final String[] fragmentNames = {"Welcome", "Canteens"};

  

  protected Fragment createCanteenListFragment() {
    Fragment fragment = new CanteenListFragment();
    Bundle args = new Bundle();
    args.putString(EntityListFragment.ARG_ENTITIES_URL, propProvider.getProperty(PROP_KEY_CANTEEN_URL));
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  protected Fragment[] getNavigationFragments() {
    return fragments;
  }

  @Override
  protected String[] getNavigationFragmentNames() {
    return fragmentNames;
  }

  @Override
  protected void startSearch(String query) {
  }
}