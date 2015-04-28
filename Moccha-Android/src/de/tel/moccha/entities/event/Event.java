/*
 * Copyright 2015 Christopher Zell <zelldon91@googlemail.com>.
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
package de.tel.moccha.entities.event;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Event implements Entity<Integer>{
    
  @JSONElement(name="id")
  private Integer ID;    
  @JSONElement(name="title")
  private String title;    
  @JSONElement(name="location")
  private String location;    
  @JSONElement(name = "description")
  private String description;    
  @JSONElement(name = "startDate")
  private String start;
  @JSONElement(name = "endDate")
  private String end;
  @JSONElement(name = "website")
  private String website;
  @JSONElement(name = "Organizer")
  private String organizer;

  public Event() {
  }
  
  public Integer getID() {
    return getID();
  }

  public String getTableName() {
    return Event.class.getName();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getOrganizer() {
    return organizer;
  }

  public void setOrganizer(String organizer) {
    this.organizer = organizer;
  }
  
  
    
    
}
