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
package de.tel.moccha.entities;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;

/**
 * Represents an category which contains the name and URL to the
 * corresponding resource.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Category implements Entity<String>{
  
  /**
   * The name of the category.
   */
  @JSONElement(name = "category")
  private String name;
  
  /**
   * The URL of the category resource.
   */
  @JSONElement(name = "uri")
  private String url;

  public Category() {
  }

  public String getID() {
    return getName();
  }

  public String getTableName() {
    return Category.class.getName();
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
  
}
