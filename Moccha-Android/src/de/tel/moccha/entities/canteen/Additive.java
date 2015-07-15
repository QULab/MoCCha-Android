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
package de.tel.moccha.entities.canteen;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;

/**
 * Represents an additive for a dish. Contains the id and a name.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Additive implements Entity<Integer> {
  @JSONElement(name="additiveID")
  private Integer ID;
  @JSONElement(name="name")
  private String name;
  
  @JSONElement(name = "image")
  private String image;
  
  @JSONElement(name = "information")
  private String information;

  public Additive() {
  }

  public Integer getID() {
    return ID;
  }

  public void setID(Integer ID) {
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTableName() {
    return Additive.class.getName();
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }
}
