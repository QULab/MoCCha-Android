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
package de.tel.moccha.activities.fragments.adapters.job;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.job.Job;
import de.tel.moccha.entities.job.JobCategory;
import de.tel.moccha.entities.job.JobTag;
import de.tel.moccha.util.JobCategoryComparator;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.adapters.EntityListAdapter;
import de.zell.android.util.db.Entity;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobCategoryListAdapter extends EntityListAdapter {

  public JobCategoryListAdapter(Context c) {
    super(c);
  }

  
  
  @Override
  protected EntityComparator getComparator() {
    return new JobCategoryComparator();
  }

  @Override
  protected void setEntityView(View row, int pos) {
    Job job = (Job) entities.get(pos);
    if (job != null) {
      TextView title = (TextView) row.findViewById(de.zell.android.util.R.id.entity_title);
      title.setText(job.getTitle());
      title.setVisibility(View.VISIBLE);
      
      TextView description = (TextView) row.findViewById(de.zell.android.util.R.id.entity_description);
      description.setText(createJobDescription(job));
      description.setVisibility(View.VISIBLE);
    }
  }

  private String createJobDescription(Job job) {
    StringBuilder builder = new StringBuilder(context.getString(R.string.job_list_tags));
    String sep = context.getString(R.string.job_list_tag_seperator);
    List<JobTag> tags = job.getTags();
    if (tags != null) {
      int len = tags.size();
      for (int i = 0; i < len; i++) {
        builder.append(tags.get(i).getTag());
        if (i+1 < len)
          builder.append(sep);
      }
    }
    return builder.toString();
  }
  
  @Override
  protected String getSection(Entity e) {
    return ((JobCategory) e).getName();
  }

  
  @Override
  public void setEntities(List<Entity> entities) {
    if (entities == null) {
      return;
    }

    Collections.sort(entities, getComparator());

    int count = 0;
    for (Entity e : entities) {
      String section = getSection(e);
      if (sections.indexOfValue(section) < 0) {
        sections.put(count, section);
        count = addJobsToEntityList((JobCategory) e, count);
      } else {
        count = addJobsToEntityList((JobCategory) e, count-1);
      }
      count++;
    }
  }

  private int addJobsToEntityList(JobCategory category, int currentCount) {
    List<Job> jobs = category.getJobs();
    for (Job job : jobs) {
      this.entities.put(++currentCount, job);
    }
    return currentCount;
  }
  
}