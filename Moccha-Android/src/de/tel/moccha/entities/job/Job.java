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
public class Job implements Entity<String> {

  @JSONElement(name = "id")
  private String id;
  
  @JSONElement(name = "title")
  private String title;
  
  @JSONElement(name = "img")
  private String img;
  
  @JSONElement(name = "created")
  private String created;
  
  @JSONElement(name = "tags")
  private List<String> tags;
  
  //DETAIL
  @JSONElement(name = "description")
  private String descrp;
  
  @JSONElement(name = "user")
  private JobCreator jobCreator;
  
  @JSONElement(name = "pdfs")
  private List<String> pdfs;
  
  
  @JSONElement(name = "edited")
  private String edited;
  
  @JSONElement(name = "contact")
  private String contact;

  public Job() {
  }

  public String getID() {
    return id;
  }

  public String getTableName() {
    return Job.class.getName();
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

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getDescrp() {
    return descrp;
  }

  public void setDescrp(String descrp) {
    this.descrp = descrp;
  }

  public JobCreator getJobCreator() {
    return jobCreator;
  }

  public void setJobCreator(JobCreator jobCreator) {
    this.jobCreator = jobCreator;
  }

  public List<String> getPdfs() {
    return pdfs;
  }

  public void setPdfs(List<String> pdfs) {
    this.pdfs = pdfs;
  }

  public String getEdited() {
    return edited;
  }

  public void setEdited(String edited) {
    this.edited = edited;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }
}
