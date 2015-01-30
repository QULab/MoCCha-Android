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
import de.tel.moccha.entities.Canteen;
import de.tel.moccha.entities.Category;
import de.tel.moccha.util.CategoryComperator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.R;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;

/**
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
  protected String getSection(Entity e) {
    return ((Category) e).getName();
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Category c = (Category) entities.get(pos);
    if (c != null) {
      TextView title = (TextView) row.findViewById(R.id.entity_title);
      title.setText(c.getName());
      title.setVisibility(View.VISIBLE);
      TextView desc = (TextView) row.findViewById(R.id.entity_description);
      desc.setText("Dishes: " + c.getDishes().size());
      desc.setVisibility(View.VISIBLE);
    }

  }
  
}
