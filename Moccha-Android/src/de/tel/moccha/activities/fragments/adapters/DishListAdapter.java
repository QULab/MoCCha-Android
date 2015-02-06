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
package de.tel.moccha.activities.fragments.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.tel.moccha.entities.Dish;
import de.zell.android.util.R;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;

/**
 * The ItemAdapter to display the dishes in the ListView.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class DishListAdapter extends EntityListAdapter {

  public DishListAdapter(Context c) {
    super(c);
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Dish d = (Dish) entities.get(pos);
    if (d != null) {
      TextView title = (TextView) row.findViewById(R.id.entity_title);
      title.setText(d.getName());
      title.setVisibility(View.VISIBLE);
      TextView desc = (TextView) row.findViewById(R.id.entity_description);
      desc.setText(d.getPrice());
      desc.setVisibility(View.VISIBLE);
    }

  }

  @Override
  protected String getSection(Entity e) {
    return "Dishes";
  }

}