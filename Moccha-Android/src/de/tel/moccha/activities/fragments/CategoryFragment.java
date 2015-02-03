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
package de.tel.moccha.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import de.tel.moccha.entities.Category;
import de.tel.moccha.entities.Dish;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CategoryFragment extends EntityViewPagerFragment {

  private final HashMap<String, List<Dish>> dates = new HashMap<String, List<Dish>>();
  private final List<String> days = new ArrayList<String>();

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
    List<Dish> ds = ((Category) entity).getDishes();
    for (Dish d : ds) {
      if (dates.containsKey(d.getDate())) {
        dates.get(d.getDate()).add(d);
      } else {
        List<Dish> dateDishes = new ArrayList<Dish>();
        days.add(d.getDate());
        dateDishes.add(d);
        dates.put(d.getDate(), dateDishes);
      }
    }
    Collections.sort(days);
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
      return days.size();
    }

    //get Item basically calls the subFragments using the newInstance method
    @Override
    public Fragment getItem(int position) {
      Bundle args = new Bundle();
      List<Dish> dishes = dates.get(days.get(position));
      if (dishes != null)
        args.putSerializable(EntityListFragment.ARG_ENTITIES,
                              dishes.toArray(new Dish[dishes.size()]));
      Fragment frg = new DishListFragment();
      frg.setArguments(args);
      return frg;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return days.get(position);
    }
  }
}
