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
package de.tel.moccha.entities.job;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;
import java.util.List;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobCategory implements Entity<String> {

  @JSONElement(name = "id")
  private String id;
  
  @JSONElement(name = "name")
  private String name;
  
  @JSONElement(name = "description")
  private String description;
  
  @JSONElement(name = "jobs")
  private List<Job> jobs;

  public JobCategory() {
  }
  
  public String getID() {
    return id;
  }

  public String getTableName() {
    return JobCategory.class.getName();
  }

  public void setID(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }
  
}
