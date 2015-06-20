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
package de.tel.moccha.activities.fragments.course;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import de.tel.moccha.activities.fragments.adapters.course.CourseListAdapter;
import de.tel.moccha.entities.course.Course;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.FragmentReplacer;

/**
 * Represents the course list fragment which shows the courses for a course of study.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseListFragment extends EntityListFragment {

  @Override
  protected void onEntityClick(Entity e) {
    Course course = (Course) e;
    Bundle b = new Bundle();
    b.putString(MainCourseCategoryFragment.ARG_ENTITY_URL, course.getUrl());
    b.putString(MainCourseCategoryFragment.ARG_FRG_TITLE, course.getTitle());
    Fragment frg = new CourseFragment();
    frg.setArguments(b);
    FragmentReplacer.replace(getActivity().getSupportFragmentManager(),
                             frg,
                             FragmentReplacer.MAIN_CONTENT);
  }

  @Override
  protected void onSectionClick(Object o) {
    //do nothing
  }

  @Override
  protected void loadEntities() {
    //do nothing
  }

  @Override
  protected EntityListAdapter getEntityListAdapter(Context ctx) {
    return new CourseListAdapter(ctx);
  }
}
