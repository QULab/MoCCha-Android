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
import java.util.List;

/**
 * Represents the waiting table which shows the current waiting numbers.
 * 
 * @see WaitingNumber
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class WaitingTable implements Entity<Integer> {
  
  @JSONElement(name = "date")
  private Integer date;
  @JSONElement(name = "src")
  private Integer table;
  @JSONElement(name = "numbers")
  private List<WaitingNumber> waitingNumbers;

  public WaitingTable() {
  }

  public Integer getDate() {
    return date;
  }

  public void setDate(Integer date) {
    this.date = date;
  }

  public Integer getTable() {
    return table;
  }

  public void setTable(Integer table) {
    this.table = table;
  }

  public List<WaitingNumber> getWaitingNumbers() {
    return waitingNumbers;
  }

  public void setWaitingNumbers(List<WaitingNumber> waitingNumbers) {
    this.waitingNumbers = waitingNumbers;
  }

  public Integer getID() {
    return table;
  }

  public String getTableName() {
    return WaitingTable.class.getName();
  }
}
