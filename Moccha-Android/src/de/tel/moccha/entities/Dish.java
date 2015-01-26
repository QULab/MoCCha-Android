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
import java.util.Date;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Dish implements Entity<String> {
  
  private Date date;
  private String name;
  private String price;

  public Dish(Date date, String name, String price) {
    this.date = date;
    this.name = name;
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
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

  public String getID() {
    return getName();
  }

  public String getTableName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  
}
