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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import de.tel.moccha.activities.R;
import de.tel.moccha.activities.fragments.adapters.CategoryListAdapter;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import de.zell.android.util.fragments.FragmentReplacer;

/**
 * Represents the category list fragment which shows the categories for a
 * canteen.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CategoryListFragment extends EntityListFragment {

  @Override
  protected void onEntityClick(Entity e) {
    Bundle args = new Bundle();
    args.putSerializable(EntityViewPagerFragment.ARG_ENITY, e);
    Fragment frg = new CategoryFragment();
    frg.setArguments(args);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(),
                             frg, R.id.canteen_diet);
    Toast.makeText(getActivity(), e.getTableName(), Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onSectionClick(Object o) {
    Toast.makeText(getActivity(), o.toString(), Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void loadEntities() {
    //
  }

  @Override
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new CategoryListAdapter(ctx);
  }
  
  
}
