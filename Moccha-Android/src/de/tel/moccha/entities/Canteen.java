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
package de.tel.moccha.entities;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Canteen implements Entity<Integer> {

  @JSONElement(name="id")
  private Integer ID;
  @JSONElement(name="title")
  private String title;
  @JSONElement(name="uri")
  private String uri;
  @JSONElement(name="university")
  private String university;
  @JSONElement(name="longitude")
  private Double longitude;
  @JSONElement(name="latitude")
  private Double latitude;
  @JSONElement(name="canteens")
  private String canteensUri;
  @JSONElement(name="address")
  private String address;
  @JSONElement(name="fax")
  private String fax;
  @JSONElement(name="openingHours")
  private String openingHours;
  @JSONElement(name="phone")
  private String phone;
  @JSONElement(name="categories")
  private List<Category> categories;

  public Canteen() {
  }
  
  public String getTableName() {
    return Canteen.class.getName();
  }

  public Integer getID() {
    return ID;
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

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public String getCanteensUri() {
    return canteensUri;
  }

  public void setCanteensUri(String canteensUri) {
    this.canteensUri = canteensUri;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getOpeningHours() {
    return openingHours;
  }

  public void setOpeningHours(String openingHours) {
    this.openingHours = openingHours;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
  
  
  public void addCategory(Category c) {
    if (categories == null)
      categories = new ArrayList<Category>();
    
    categories.add(c);
  }
  
  
}
