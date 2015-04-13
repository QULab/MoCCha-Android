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
package de.tel.moccha.activities.fragments.adapters.canteen;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.tel.moccha.entities.canteen.Category;
import de.tel.moccha.entities.canteen.Dish;
import de.tel.moccha.util.CategoryComperator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import java.util.Collections;
import java.util.List;

/**
 * The ItemAdapter to display the categories of the canteen in the ListView.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CategoryListAdapter extends EntityListAdapter {

  public CategoryListAdapter(Context c) {
    super(c);
  }

  @Override
  protected EntityComparator getComparator() {
    return new CategoryComperator();
  }

  @Override
  public void setEntities(List<Entity> entities) {
    int count = 0;
    Collections.sort(entities, getComparator());
    for (Entity e : entities) {
      Category c = (Category) e;
      sections.put(count++, c.getName());
      List<Dish> dishes = c.getDishes();
      for (Dish d : dishes) {
        this.entities.put(count++, d);
      }
    }
  }

  @Override
  protected String getSection(Entity e) {
    return ((Category) e).getName();
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Dish d = (Dish) entities.get(pos);
    if (d != null) {
      TextView title = (TextView) row.findViewById(de.zell.android.util.R.id.entity_title);
      title.setText(d.getName());
      title.setVisibility(View.VISIBLE);
      TextView desc = (TextView) row.findViewById(de.zell.android.util.R.id.entity_description);
      desc.setText(d.getPrice());
      desc.setVisibility(View.VISIBLE);
    }
  }

}
