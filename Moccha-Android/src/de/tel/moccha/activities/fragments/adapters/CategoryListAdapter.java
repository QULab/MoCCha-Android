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
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.Category;
import de.tel.moccha.entities.course.CategoryNameConverter;
import de.tel.moccha.util.CategoryComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;

/**
 * Represents the list adapter for the category list.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CategoryListAdapter extends EntityListAdapter {

  private final String sectionTitle;
  /**
   * The ctor the create the categoryListAdapter object.
   * 
   * @param c the context which is used by the adapter
   * @param sectionTitle the title for the section heading
   */
  public CategoryListAdapter(Context c, String sectionTitle) {
    super(c);
    this.sectionTitle = sectionTitle;
  }
  
  @Override
  protected EntityComparator getComparator() {
    return new CategoryComparator();
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Category category = (Category) entities.get(pos);
    if (category != null) {
      setTextViewVisible(row, R.id.entity_row_title,
                         CategoryNameConverter.getLongName(category.getName()));
    }
  }
  
  @Override
  protected String getSection(Entity e) {
    return sectionTitle;
  }
}
