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
import android.widget.ListAdapter;
import de.tel.moccha.activities.fragments.canteen.CanteenListFragment;
import de.tel.moccha.activities.fragments.WelcomeFragment;
import de.tel.moccha.activities.fragments.CategoryListFragment;
import de.tel.moccha.activities.fragments.course.UniversityListFragment;
import de.tel.moccha.activities.fragments.event.EventCategoryListFragment;
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

  /**
   * The name of the properties file.
   */
  public static final String PROP_FILE = "moccha.properties";

  /**
   * The properties provider instance, to read the property file.
   */
  public static final PropertiesProvider propProvider = PropertiesProvider.getInstance();

  /**
   * The property key for the canteen url.
   */
  public static final String PROP_KEY_CANTEEN_URL = "canteens.url";
  
  /**
   * The property key for the course url.
   */
  public static final String PROP_KEY_COURSE_URL = "courses.url";
  
  /**
   * The property key for the events url.
   */
  public static final String PROP_KEY_EVENTS_URL = "events.url";
  
  /**
   * Static initializer to initialize the properties provider with the moccha
   * properties file.
   */
  static {
    PropertiesProvider.setPropertyFile(MoCChaMainNavigationActivity.class, PROP_FILE);
  }

  /**
   * The fragments which should be placed in the center if selected the
   * corresponding name is selected in the navigation drawer.
   */
  private static final Fragment[] fragments = {new WelcomeFragment(), createCanteenListFragment(),
                                        createUniversityFragment(), createEventCategoryListFragment()};
  
  private static final int[] fragmentIcons = {R.drawable.ic_launcher, R.drawable.ic_fork_knife, R.drawable.ic_book, R.drawable.ic_launcher};

  /**
   * The available fragments which can be selected, the names are shown in the
   * left menu of the navigation drawer.
   */
  private String[] fragmentNames;

  /**
   * Creates a fragment object for the canteen list fragment, with the
   * correct canteen URL as argument.
   * 
   * @return the canteen list fragment
   */
  private static Fragment createCanteenListFragment() {
    Fragment fragment = new CanteenListFragment();
    Bundle args = new Bundle();
    args.putString(EntityListFragment.ARG_ENTITIES_URL, propProvider.getProperty(PROP_KEY_CANTEEN_URL));
    fragment.setArguments(args);
    return fragment;
  }

  /**
   * Creates a fragment object for the university list fragment, with the
   * correct university URL as argument.
   * 
   * @return the university list fragment
   */
  private static Fragment createUniversityFragment() {
    Fragment fragment = new UniversityListFragment();
    Bundle args = new Bundle();
    args.putString(EntityListFragment.ARG_ENTITIES_URL, propProvider.getProperty(PROP_KEY_COURSE_URL));
    fragment.setArguments(args);
    return fragment;
  }
  
  /**
   * Creates a fragment object for the university list fragment, with the
   * correct university URL as argument.
   * 
   * @return the university list fragment
   */
  private static Fragment createEventCategoryListFragment() {
    Fragment fragment = new EventCategoryListFragment();
    Bundle args = new Bundle();
    args.putString(EntityListFragment.ARG_ENTITIES_URL, propProvider.getProperty(PROP_KEY_EVENTS_URL));
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  protected ListAdapter getNavigationListAdapter() {
    return new NavigationListAdapter(fragmentNames, fragmentIcons, this);
  }
  
  @Override
  protected Fragment[] getNavigationFragments() {
    return fragments;
  }

  @Override
  protected String[] getNavigationFragmentNames() {
    if (fragmentNames == null)
      fragmentNames = getResources().getStringArray(R.array.applications);
   return fragmentNames;
  }

  @Override
  protected void startSearch(String query) {
  }
}
