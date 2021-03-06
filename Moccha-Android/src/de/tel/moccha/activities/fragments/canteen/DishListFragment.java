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

import android.content.Context;
import android.os.Bundle;
import de.tel.moccha.activities.fragments.adapters.canteen.DishListAdapter;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityListFragment;

/**
 * Represents the dish list fragment which shows the dishes in a list view.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class DishListFragment extends EntityListFragment {

  @Override
  protected void onEntityClick(Entity e) {
    //entity
    Bundle b = new Bundle();
    b.putSerializable(DishInfoDialogFragment.ARG_DISH_INFO, e);
    DishInfoDialogFragment info = new DishInfoDialogFragment();
    info.setArguments(b);
    info.show(getActivity().getSupportFragmentManager(), ARG_ENTITIES);
  }

  @Override
  protected void onSectionClick(Object o) {
    //sec
  }

  @Override
  protected void loadEntities() {
    //nothing todo
  }

  @Override
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new DishListAdapter(ctx);
  }
}
