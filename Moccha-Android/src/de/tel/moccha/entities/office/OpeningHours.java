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
 * Represents the office opening hours.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class OpeningHours implements Entity<Integer> {
  @JSONElement(name = "weekday")
  private int weekDay;
  @JSONElement(name = "begin")
  private int begin;
  @JSONElement(name = "end")
  private int end;

  public OpeningHours() {
  }

  public int getWeekDay() {
    return weekDay;
  }

  public void setWeekDay(int weekDay) {
    this.weekDay = weekDay;
  }

  public int getBegin() {
    return begin;
  }

  public void setBegin(int begin) {
    this.begin = begin;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public Integer getID() {
    return weekDay;
  }

  public String getTableName() {
    return OpeningHours.class.getName();
  }
}
