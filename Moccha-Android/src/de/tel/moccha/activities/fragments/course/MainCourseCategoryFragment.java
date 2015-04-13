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
package de.tel.moccha.activities.fragments.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.course.CourseCategory;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.fragments.EntityListFragment;
import de.zell.android.util.fragments.EntityViewPagerFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import java.util.List;
import org.json.JSONObject;

/**
 * Represents the main course category fragment which shows for the
 * corresponding university the course of studies and courses.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class MainCourseCategoryFragment extends EntityViewPagerFragment {
  
  @Override
  protected void loadEntity() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
          entity = JSONUnmarshaller.unmarshall(response, CourseCategory.class);
          View root = getView();
          if (root != null) {
            ViewPager pager = (ViewPager) root.findViewById(R.id.tab_viewPager);
            if (pager != null)
              pager.getAdapter().notifyDataSetChanged();
          }
      }

      public void doExeptionHandling(Throwable t) {
        Log.e(UniversityListFragment.class.getName(), t.getMessage(), t);
      }

      public void handleNewEtag(String url, String newEtag) {
        //TODO
      }
    });
    request.showProgress(((MainNavigationActivity) getActivity()).getProgressBar());
    GetRequestInfo info = new GetRequestInfo(getURL(), null);
    request.execute(info);
  }

  @Override
  protected FragmentPagerAdapter getPageAdapter(FragmentManager mgr) {
    return new ContentPagerAdapter(mgr);
  }

  @Override
  protected void extractEntityInformation() {
  }
  
  
  /**
   * The content page adapter which shows either the course of studies or courses.
   */
  private class ContentPagerAdapter extends FragmentPagerAdapter {

    private final String[] titles;
    
    public ContentPagerAdapter(FragmentManager fm) {
      super(fm);
      titles = getResources().getStringArray(R.array.courses_content_pages);
    }
    
    @Override
    public int getCount() {
      int count = 0;
      if (entity != null) {
        CourseCategory content = (CourseCategory) entity;
        if (content.getCategories() != null) 
          count++;
        if (content.getCourses() != null)
          count++;
      }
      return count;
    }

    
    @Override
    public Fragment getItem(int i) {
      if (i > 0 && entity != null)
        return null;
      
      CourseCategory content = (CourseCategory) entity;
      Fragment frg = new CourseCategoryListFragment();
      List<CourseCategory> categories = content.getCategories();
      if (categories != null) {
        Bundle args = new Bundle();
        args.putSerializable(EntityListFragment.ARG_ENTITIES, categories.toArray(new CourseCategory[categories.size()]));
        frg.setArguments(args);
      }
      return frg; //if 0 categories - 1 courses
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return titles[position % titles.length];
    }
  }

  @Override
  protected void restoreInstance(Bundle values) {
    //do nothing
  }
}
