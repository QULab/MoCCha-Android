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
package de.tel.moccha.activities.fragments.canteen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.canteen.Canteen;
import de.tel.moccha.entities.canteen.CanteenCategory;
import de.tel.moccha.entities.canteen.Dish;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the pager for the week days, to show the diets for each day for a
 * given canteen.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class WeekCanteenPagerFragment extends EntityViewPagerFragment {

  private List<CanteenCategory> categories;
  private HashMap<String, List<CanteenCategory>> dailyCategories;
  private String[] weekDays = new String[7];

  @Override
  protected void loadEntity() {
    //nothing todo
  }

  @Override
  protected FragmentPagerAdapter getPageAdapter(FragmentManager mgr) {
    return new DayCategoryPager(mgr);
  }

  @Override
  protected void extractEntityInformation() {
    Canteen canteen = (Canteen) entity;
    categories = canteen.getCategories();
    weekDays = getResources().getStringArray(R.array.week_days);

    final Calendar cal = Calendar.getInstance();
    final SimpleDateFormat format = new SimpleDateFormat(getResources().getString(R.string.time_pattern));

    if (categories != null) {
      dailyCategories = new HashMap<String, List<CanteenCategory>>();
      for (int i = 0; i < weekDays.length; i++) {
        String day = weekDays[i];
        List<CanteenCategory> cs = new ArrayList<CanteenCategory>();
        for (CanteenCategory c : categories) {
          List<Dish> dishes = c.getDishes();
          CanteenCategory dayICategory = new CanteenCategory();
          dayICategory.setName(c.getName());
          for (Dish d : dishes) {
            try {
              cal.setTime(format.parse(d.getDate()));
            } catch (ParseException ex) {
              Log.e(WeekCanteenPagerFragment.class.getName(), ex.getMessage(), ex);
            }
            if (i == (cal.get(Calendar.DAY_OF_WEEK) - 2)) {
              dayICategory.addDish(d);
            }
          }
          if (dayICategory.getDishes() != null)
            cs.add(dayICategory);
        }
        dailyCategories.put(day, cs);
      }
    }
  }

  /**
   * The FragmentPagerAdapter for the days of a category.
   */
  public class DayCategoryPager extends FragmentPagerAdapter {

    public DayCategoryPager(FragmentManager fm) {
      super(fm);
    }

    @Override
    public int getCount() {
      return dailyCategories.size();
    }

    //get Item basically calls the subFragments using the newInstance method
    @Override
    public Fragment getItem(int position) {
      Bundle args = new Bundle();
      List<CanteenCategory> cs = dailyCategories.get(weekDays[position]);
      if (dailyCategories != null) {
        args.putSerializable(EntityListFragment.ARG_ENTITIES,
                cs.toArray(new CanteenCategory[cs.size()]));
      }
      Fragment frg = new CategoryListFragment();
      frg.setArguments(args);
      return frg;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return weekDays[position];
    }
  }

  @Override
  protected void restoreInstance(Bundle values) {
    // do nothing
  }
  
}
