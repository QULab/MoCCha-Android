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
package de.tel.moccha.activities.fragments.adapters.course;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.course.CourseCategory;
import de.tel.moccha.util.CourseCategoryComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;

/**
 * The entity list adapter to show the course category list.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseCategoryListAdapter extends EntityListAdapter {

  public CourseCategoryListAdapter(Context c) {
    super(c);
  }

  @Override
  protected EntityComparator getComparator() {
    return new CourseCategoryComparator();
  }

  @Override
  protected CharSequence getEntityTitle(Entity e) {
    return ((CourseCategory) e).getName();
  }

  @Override
  protected String getSection(Entity e) {
    return context.getString(R.string.course_category_list_section_header);
  }

}
