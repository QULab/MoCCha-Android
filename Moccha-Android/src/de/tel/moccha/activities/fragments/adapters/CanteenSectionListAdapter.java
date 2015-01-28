/*
 * Copyright 2014 Quality and Usability Lab, Telekom Innvation Laboratories, TU Berlin..
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
package de.tel.moccha.activities.fragments.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.tel.moccha.entities.Canteen;
import de.tel.moccha.util.CanteenComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.EntitySectionListAdapter;
import de.zell.android.util.R;
import de.zell.android.util.db.Entity;

/**
 * The ItemAdapter to display each single result of the search in the ListView.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CanteenSectionListAdapter extends EntitySectionListAdapter {

  public CanteenSectionListAdapter(Context c) {
    super(c);
  }

  @Override
  protected EntityComparator getComparator() {
    return new CanteenComparator();
  }

  @Override
  protected String getSection(Entity e) {
    return ((Canteen) e).getUniversity();
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Canteen c = (Canteen) entities.get(pos);
    if (c != null) {
      TextView title = (TextView) row.findViewById(R.id.entity_title);
      title.setText(c.getTitle());
      title.setVisibility(View.VISIBLE);
      TextView desc = (TextView) row.findViewById(R.id.entity_description);
      desc.setText(c.getUri());
      desc.setVisibility(View.VISIBLE);
    }

  }
}