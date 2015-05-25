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
public class Job implements Entity<Integer> {

  @JSONElement(name = "id")
  private Integer id;
  
  @JSONElement(name = "title")
  private String title;
  
  @JSONElement(name = "img")
  private String img;
  
  @JSONElement(name = "created")
  private Long created;
  
  @JSONElement(name = "tags")
  private List<String> tags;

  public Job() {
  }
  
  public Integer getID() {
    return id;
  }

  public String getTableName() {
    return Job.class.getName();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public Long getCreated() {
    return created;
  }

  public void setCreated(Long created) {
    this.created = created;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
