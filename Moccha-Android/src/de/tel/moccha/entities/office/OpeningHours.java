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
  private Integer weekDay;
  @JSONElement(name = "begin")
  private Integer begin;
  @JSONElement(name = "end")
  private Integer end;

  public OpeningHours() {
  }

  public Integer getWeekDay() {
    return weekDay;
  }

  public void setWeekDay(Integer weekDay) {
    this.weekDay = weekDay;
  }

  public Integer getBegin() {
    return begin;
  }

  public void setBegin(Integer begin) {
    this.begin = begin;
  }

  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  public Integer getID() {
    return weekDay;
  }

  public String getTableName() {
    return OpeningHours.class.getName();
  }
}
