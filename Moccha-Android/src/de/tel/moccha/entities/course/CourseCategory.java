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
package de.tel.moccha.entities.course;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;
import java.util.List;

/**
 * Represents a course category like the course of studies 
 * but contains recursive course categories and courses.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseCategory implements Entity<String>{
  @JSONElement(name = "title")
  private String name;
  @JSONElement(name = "uri")
  private String url;
  @JSONElement(name = "categories")
  private List<CourseCategory> categories;
  @JSONElement(name = "courses")
  private List<Course> courses;
  

  public CourseCategory() {
  }

  public String getID() {
    return getName();
  }

  public String getTableName() {
    return CourseCategory.class.getName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<CourseCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<CourseCategory> categories) {
    this.categories = categories;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
  
}
