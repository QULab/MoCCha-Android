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
package de.tel.moccha.entities.office;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;

/**
 * Represents the current waiting number.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class WaitingNumber implements Entity<String> {
  
  @JSONElement(name = "date")
  private long date;
  @JSONElement(name = "src")
  private String table;
  @JSONElement(name = "num")
  private int currentNumber;
//TODO ask tilo for usage
//  @JSONElement(name = "avg") 
//  private double avg;

  public WaitingNumber() {
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public int getCurrentNumber() {
    return currentNumber;
  }

  public void setCurrentNumber(int currentNumber) {
    this.currentNumber = currentNumber;
  }

  public String getID() {
    return table;
  }

  public String getTableName() {
    return WaitingNumber.class.getName();
  }
}
