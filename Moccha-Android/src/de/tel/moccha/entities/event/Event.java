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
public class Event implements Entity<Integer> {
  @JSONElement(name="id")
  private Integer ID;    
  @JSONElement(name="title")
  private String title;    
  @JSONElement(name = "uri")
  private String uri;
  @JSONElement(name = "description")
  private String description;    
  @JSONElement(name = "startDate")
  private String start;
  @JSONElement(name = "period")
  private String period;
  @JSONElement(name = "organizer")
  private String organizer;

  public Event() {
  }
  
  public Integer getID() {
    return ID;
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

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
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

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }
  
  public String getOrganizer() {
    return organizer;
  }

  public void setOrganizer(String organizer) {
    this.organizer = organizer;
  }
}
