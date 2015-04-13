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

/**
 * Represents the date for a course and his iteration.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseDate implements Entity<String> {
  
  @JSONElement(name = "day")
  private String day;
  @JSONElement(name = "iteration")
  private String iteration;
  @JSONElement(name = "timeFrom")
  private String timeFrom;
  @JSONElement(name = "timeUntil")
  private String timeUntil;

  
  @JSONElement(name = "from")
  private String from;
  @JSONElement(name = "until")
  private String until;
  @JSONElement(name = "maxMembers")
  private Integer maxMembers;

  public CourseDate() {
  }

  public String getID() {
    return from + " " +  until;
  }

  public String getTableName() {
    return CourseDate.class.getName();
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getIteration() {
    return iteration;
  }

  public void setIteration(String iteration) {
    this.iteration = iteration;
  }

  public String getTimeFrom() {
    return timeFrom;
  }

  public void setTimeFrom(String timeFrom) {
    this.timeFrom = timeFrom;
  }

  public String getTimeUntil() {
    return timeUntil;
  }

  public void setTimeUntil(String timeUntil) {
    this.timeUntil = timeUntil;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getUntil() {
    return until;
  }

  public void setUntil(String until) {
    this.until = until;
  }

  public Integer getMaxMembers() {
    return maxMembers;
  }

  public void setMaxMembers(Integer maxMembers) {
    this.maxMembers = maxMembers;
  }
}
