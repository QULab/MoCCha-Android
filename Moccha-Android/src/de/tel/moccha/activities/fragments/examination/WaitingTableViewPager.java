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
package de.tel.moccha.activities.fragments.examination;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.office.ExaminationOffice;
import de.tel.moccha.entities.office.WaitingNumber;
import de.tel.moccha.entities.office.WaitingTable;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import java.util.List;

/**
 * Represents the waiting table view pager.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class WaitingTableViewPager extends EntityViewPagerFragment {

  
  
  private List<WaitingTable> waitinTable;


  @Override
  protected FragmentPagerAdapter getPageAdapter(FragmentManager mgr) {
    return new TablePageAdapter(mgr);
  }

  
  @Override
  protected void extractEntityInformation() {
    waitinTable = ((ExaminationOffice) entity).getWaitingRooms();
  }

  @Override
  protected void restoreInstance(Bundle values) {
    // do nothing
  }

  @Override
  protected void loadEntity() {
    // do nothing
  }
  
  /**
   * The FragmentPagerAdapter for the tables of the examination office.
   */
  public class TablePageAdapter extends FragmentPagerAdapter {

    public TablePageAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public int getCount() {
      return waitinTable.size();
    }

    //get Item basically calls the subFragments using the newInstance method
    @Override
    public Fragment getItem(int position) {
      Bundle args = new Bundle();
      List<WaitingNumber> numbers = waitinTable.get(position).getWaitingNumbers();
      if (numbers != null) {
        args.putSerializable(EntityListFragment.ARG_ENTITIES,
                numbers.toArray(new WaitingNumber[numbers.size()]));
      }
      Fragment frg = new WaitingNumberListFragment();
      frg.setArguments(args);
      return frg;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return getTableHeader(waitinTable.get(position).getTable());
    }
    
    private String getTableHeader(int number) {
      return String.format(getString(R.string.examination_office_table_pager_header), number);
    }
  }
  
}
