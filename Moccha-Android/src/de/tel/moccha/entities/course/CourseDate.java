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

import de.zell.android.util.json.JSONElement;

/**
 * Represents the date for a course and his iteration.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CourseDate {
  
  @JSONElement(name = "day")
  private String day;
  @JSONElement(name = "iteration")
  private String iteration;
  @JSONElement(name = "timeDay")
  private String from;
  @JSONElement(name = "timeUntil")
  private String until;

  public CourseDate() {
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
  
}
