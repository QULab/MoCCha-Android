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
 * Represents a person and contains the information of these corresponding
 * person like email, id, name etc.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Person implements Entity<Integer> {
  @JSONElement(name = "email")
  private String email;
  @JSONElement(name = "id")
  private Integer id;
  @JSONElement(name = "lastname")
  private String lastname;
  @JSONElement(name = "prename")
  private String prename;

  public Person() {
  }

  public Integer getID() {
    return getId();
  }

  public String getTableName() {
    return Person.class.getName();
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPrename() {
    return prename;
  }

  public void setPrename(String prename) {
    this.prename = prename;
  }
  
  
}
