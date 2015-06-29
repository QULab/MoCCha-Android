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
package de.tel.moccha.activities.fragments.adapters.event;

import android.content.Context;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.event.Event;
import de.tel.moccha.util.EventComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class EventListAdapter extends EntityListAdapter {

  public EventListAdapter(Context c) {
    super(c);
  }

  @Override
  protected EntityComparator getComparator() {
    return new EventComparator();
  }

  @Override
  protected CharSequence getEntityDescription(Entity e) {
    String timePattern = this.context.getString(R.string.time_pattern);
    SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
    Date d = new Date();
    try {
      d = sdf.parse(((Event) e).getStart());
    } catch (ParseException ex) {
      Logger.getLogger(EventListAdapter.class.getName()).log(Level.SEVERE, null, ex);
    }
    String datePattern = this.context.getString(R.string.date_pattern);
    SimpleDateFormat ddf = new SimpleDateFormat(datePattern);
    return ddf.format(d);
  }

  @Override
  protected CharSequence getEntityTitle(Entity e) {
    return ((Event) e).getTitle();
  }

  @Override
  protected String getSection(Entity e) {
    return this.context.getString(R.string.events);
  }
  
  
}
