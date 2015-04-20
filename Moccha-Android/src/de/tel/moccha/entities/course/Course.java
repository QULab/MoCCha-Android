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
import java.util.List;

/**
 * Represents a course which contains the kind of the event, the title and id.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class Course implements Entity<Integer> {
  
  @JSONElement(name = "kindOfEvent")
  private String kindOfEvent;
  @JSONElement(name = "title")
  private String title;
  @JSONElement(name = "uri")
  private String url;
  @JSONElement(name = "id")
  private Integer id;
  
  //=========================================
  //=================DETAILS=================
  //=========================================
  @JSONElement(name = "comment")
  private String comment;
  @JSONElement(name = "content")
  private String content;
  @JSONElement(name = "hyperlink")
  private String hyperlink;
  @JSONElement(name = "rhythmus")
  private String rhythmus;
  @JSONElement(name = "semester")
  private String semester;
  @JSONElement(name = "language")
  private String language;
  @JSONElement(name = "sws")
  private Double semesterWochenStunden;
  @JSONElement(name = "eventNumber")
  private String eventNumber;
  @JSONElement(name = "responsibilities")
  private List<Responsibility> responsibilities;
  @JSONElement(name = "courseOfStudies")
  private List<CourseOfStudies> courseOfStudies;
  @JSONElement(name = "groupDates")
  private List<GroupDate> groupDates;

  public Course() {
  }

  public Integer getID() {
    return getId();
  }

  public String getTableName() {
    return Course.class.getName();
  }

  public String getKindOfEvent() {
    return kindOfEvent;
  }

  public void setKindOfEvent(String kindOfEvent) {
    this.kindOfEvent = kindOfEvent;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getHyperlink() {
    return hyperlink;
  }

  public void setHyperlink(String hyperlink) {
    this.hyperlink = hyperlink;
  }

  public String getRhythmus() {
    return rhythmus;
  }

  public void setRhythmus(String rhythmus) {
    this.rhythmus = rhythmus;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Double getSemesterWochenStunden() {
    return semesterWochenStunden;
  }

  public void setSemesterWochenStunden(Double semesterWochenStunden) {
    this.semesterWochenStunden = semesterWochenStunden;
  }

  public String getEventNumber() {
    return eventNumber;
  }

  public void setEventNumber(String eventNumber) {
    this.eventNumber = eventNumber;
  }

  public List<Responsibility> getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(List<Responsibility> responsibilities) {
    this.responsibilities = responsibilities;
  }

  public List<CourseOfStudies> getCourseOfStudies() {
    return courseOfStudies;
  }

  public void setCourseOfStudies(List<CourseOfStudies> courseOfStudies) {
    this.courseOfStudies = courseOfStudies;
  }

  public List<GroupDate> getGroupDates() {
    return groupDates;
  }

  public void setGroupDates(List<GroupDate> groupDates) {
    this.groupDates = groupDates;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Course other = (Course) obj;
    if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }
  
}
