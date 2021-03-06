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
import de.tel.moccha.activities.R;
import de.tel.moccha.activities.fragments.CategoryListFragment;
import de.tel.moccha.activities.fragments.adapters.CategoryListAdapter;
import de.tel.moccha.entities.Category;
import de.zell.android.util.adapters.EntityListAdapter;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class EventCategoryListFragment extends CategoryListFragment {

  @Override
  public Bundle getArgumentsForFragment(Category c) {
    Bundle b = new Bundle();
    b.putString(EventListFragment.ARG_ENTITIES_URL, c.getUrl());
    return b;
  }

  @Override
  public Fragment getOnClickFragment() {
    return new EventListFragment();
  }
  
  @Override
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new CategoryListAdapter(ctx, getString(R.string.event_category_list_header));
  }
  
}
