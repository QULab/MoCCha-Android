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

import android.os.Bundle;
import de.tel.moccha.entities.course.Course;
import de.tel.moccha.entities.course.CourseCategory;
import de.zell.android.util.fragments.EntityListFragment;
import java.util.List;

/**
 * Represents a factory for the course category list and course list fragments.
 * For a given content category the corresponding entity list fragment will be created
 * with the given list values.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ListFragmentFactory {

  /**
   * Creates for the given content category the corresponding entity list fragment.
   * 
   * @param category the category which is chosen
   * @param values the values for the list view
   * @return the corresponding list view
   */
  public static EntityListFragment createListFragment(ContentCategories category, List values) {
    if (category == ContentCategories.CourseOfStudies) {
      return createCourseCategoryListFragment(values);
    } else if (category == ContentCategories.Courses) {
      return createCourseListFragment(values);
    } else {
      return null;
    }
  }

  /**
   * Creates for the course category list the corresponding course category list
   * fragment.
   *
   * @param categories the course categories
   * @return the corresponding list fragment
   */
  private static EntityListFragment createCourseCategoryListFragment(List categories) {
    EntityListFragment frg = new CourseCategoryListFragment();
    if (categories != null) {
      Bundle args = new Bundle();
      args.putSerializable(EntityListFragment.ARG_ENTITIES, categories.toArray(new CourseCategory[categories.size()]));
      frg.setArguments(args);
    }
    return frg;
  }

  /**
   * Creates for the course list the corresponding course list fragment.
   *
   * @param courses the courses
   * @return the corresponding list fragment
   */
  private static EntityListFragment createCourseListFragment(List courses) {
    EntityListFragment frg = new CourseListFragment();
    if (courses != null) {
      Bundle args = new Bundle();
      args.putSerializable(EntityListFragment.ARG_ENTITIES, courses.toArray(new Course[courses.size()]));
      frg.setArguments(args);
    }
    return frg;
  }

}
