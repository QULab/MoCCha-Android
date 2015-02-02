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
import java.util.List;

/**
 * Represents a dish which contains a name, date, price and the 
 * additives.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Dish implements Entity<String> {
  
  @JSONElement(name="date")
  private String date;
  @JSONElement(name="name")
  private String name;
  @JSONElement(name="price")
  private String price;
  @JSONElement(name = "additives")
  private List<Additive> additives;

  public Dish() {
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public List<Additive> getAdditives() {
    return additives;
  }

  public void setAdditives(List<Additive> additives) {
    this.additives = additives;
  }
  
  public String getID() {
    return getName();
  }

  public String getTableName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  
}
