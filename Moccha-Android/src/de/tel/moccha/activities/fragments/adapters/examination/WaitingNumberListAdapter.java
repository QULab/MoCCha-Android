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
package de.tel.moccha.activities.fragments.adapters.examination;

import android.content.Context;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.office.WaitingNumber;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import java.util.Date;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class WaitingNumberListAdapter extends EntityListAdapter {
  
  public WaitingNumberListAdapter(Context c) {
    super(c);
  }

  @Override
  protected CharSequence getEntityTitle(Entity e) {
    return e.getID().toString();
  }

  @Override
  protected CharSequence getEntityDescription(Entity e) {
    WaitingNumber number = (WaitingNumber) e;
    StringBuilder builder = new StringBuilder();
    long timestamp = ((long) number.getDate()) * 1000;
    String currNum = String.format(this.context.getString(R.string.examination_current_nr), 
                                    number.getCurrentNumber());
    String lastUpdate = String.format(this.context.getString(R.string.examination_last_update),
                                      new Date(timestamp).toString());
    builder.append(currNum)
           .append("\n")
           .append(lastUpdate);
    return builder.toString();
  }

  @Override
  protected String getSection(Entity e) {
    return null;
  }
}
