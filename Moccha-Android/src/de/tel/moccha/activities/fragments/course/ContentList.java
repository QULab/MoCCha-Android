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

import java.util.List;

/**
 * Represents a content list which contains a category and the corresponding
 * value for the category.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ContentList {
  private ContentCategories category;
  private List content;

  public ContentList(ContentCategories category, List content) {
    this.category = category;
    this.content = content;
  }

  public ContentCategories getCategory() {
    return category;
  }

  public void setCategory(ContentCategories category) {
    this.category = category;
  }
  
  public List getContent() {
    return content;
  }

  public void setContent(List content) {
    this.content = content;
  }
  
  
}
