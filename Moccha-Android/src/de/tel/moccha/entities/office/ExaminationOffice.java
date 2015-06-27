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
package de.tel.moccha.entities.office;

import de.zell.android.util.db.Entity;
import de.zell.android.util.json.JSONElement;
import java.util.List;

/**
 * Represents the examination office with opening hours
 * and the waiting table which shows the current numbers.
 * 
 * @see OpeningHours
 * @see WaitingNumber
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ExaminationOffice implements Entity<Integer> {
  
  public static int EXAMINATION_ID = 0;
  @JSONElement(name = "openings")
  List<OpeningHours> openings;
  @JSONElement(name = "entries")
  List<WaitingTable> waitingRooms;

  public ExaminationOffice() {
  }

  public List<OpeningHours> getOpenings() {
    return openings;
  }

  public void setOpenings(List<OpeningHours> openings) {
    this.openings = openings;
  }

  public List<WaitingTable> getWaitingRooms() {
    return waitingRooms;
  }

  public void setWaitingRooms(List<WaitingTable> waitingRooms) {
    this.waitingRooms = waitingRooms;
  }

  public Integer getID() {
    return EXAMINATION_ID;
  }

  public String getTableName() {
    return ExaminationOffice.class.getName();
  }
}
