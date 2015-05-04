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
import de.tel.moccha.entities.canteen.Additive;
import de.tel.moccha.entities.canteen.Dish;
import de.tel.moccha.util.StringFormatter;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.canteen.DishCategory;
import de.tel.moccha.util.DishCategoryComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import java.util.Collections;
import java.util.List;

/**
 * The ItemAdapter to display the dishes in the ListView.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class DishListAdapter extends EntityListAdapter {

  private final StringFormatter formatter;
  
  public DishListAdapter(Context c) {
    super(c);
    formatter = new StringFormatter(c);
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Dish d = (Dish) entities.get(pos);
    if (d != null) {
      
      TextView title = (TextView) row.findViewById(R.id.entity_title);
      List<Additive> additives = d.getAdditives();
      if (additives != null && !additives.isEmpty()) {
        StringBuilder builder = new StringBuilder();
        int len = additives.size();
        for (int i = 0; i < len; i++) {
          builder.append(additives.get(i).getID());
          if (i+1 != len) 
            builder.append(',');
        }
        title.setText(formatter.getFormatedHTMLString(R.string.canteen_dish, d.getName(), builder.toString()));
      } else {
        title.setText(d.getName());
      }
      
      title.setVisibility(View.VISIBLE);
      TextView desc = (TextView) row.findViewById(R.id.entity_description);
      desc.setText(d.getPrice());
      desc.setVisibility(View.VISIBLE);
    }

  }

  @Override
  protected EntityComparator getComparator() {
    return new DishCategoryComparator();
  }

  @Override
  public void setEntities(List<Entity> entities) {
    int count = 0;
    Collections.sort(entities, getComparator());
    for (Entity e : entities) {
      DishCategory c = (DishCategory) e;
      this.sections.put(count++, c.getName());
      List<Dish> dishes = c.getDishes();
      for (Dish d : dishes) {
        this.entities.put(count++, d);
      }
    }
  }

  @Override
  protected String getSection(Entity e) {
    return ((DishCategory) e).getName();
  }
}
