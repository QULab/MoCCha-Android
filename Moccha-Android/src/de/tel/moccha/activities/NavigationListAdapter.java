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
package de.tel.moccha.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class NavigationListAdapter extends BaseAdapter {

  private final String[] itemTexts;
  private final int[] itemIcons;
  private final Context ctx;

  public NavigationListAdapter(String[] itemTexts, int[] itemIcons, Context ctx) {
    this.itemTexts = itemTexts;
    this.itemIcons = itemIcons;
    this.ctx = ctx;
  }
  
  public int getCount() {
    return itemTexts.length;
  }

  public Object getItem(int arg0) {
    return itemTexts[arg0 % itemTexts.length];
  }

  public long getItemId(int arg0) {
    return arg0;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View row = inflater.inflate(R.layout.navigation_drawer_item, parent, false);
    
    if (itemIcons != null && itemIcons.length > 0) {
      ImageView image = (ImageView) row.findViewById(R.id.drawer_icon);
      image.setImageDrawable(ctx.getResources().getDrawable(itemIcons[position % itemIcons.length]));
    }
    
    TextView text = (TextView) row.findViewById(R.id.drawer_text);
    text.setText(itemTexts[position]);
    
    return row;
  }
  
}
