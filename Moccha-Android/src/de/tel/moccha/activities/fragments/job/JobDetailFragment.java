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
package de.tel.moccha.activities.fragments.job;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.job.Job;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobDetailFragment extends EntityFragment {

  public static final String JSON_JOB_DETAIL_KEY = "job";
  private static final int TABLE_COLUMNS_PADDING = 3;
  
  @Override
  protected void restoreInstance(Bundle values) {
    //nothing to do
  }

  @Override
  protected void loadEntity() {
    AsyncGETRequester request = new AsyncGETRequester(new AsyncGETRequester.PostExecuteJob() {
      public void doJob(JSONObject response) {
        try {
          response = response.getJSONObject(JSON_JOB_DETAIL_KEY);
        } catch (JSONException ex) {
          Log.e(JobDetailFragment.class.getName(), ex.getMessage(), ex);
        }
        entity = JSONUnmarshaller.unmarshall(response, Job.class);
        showJobDetails((Job) entity);
      }

      public void doExeptionHandling(Throwable t) {
        Log.e(JobDetailFragment.class.getName(), t.getMessage(), t);
      }

      public void handleNewEtag(String url, String newEtag) {
        //TODO
      }
    });
    request.showProgress(((MainNavigationActivity) getActivity()).getProgressBar());
    GetRequestInfo info = new GetRequestInfo(getURL(), null);
    request.execute(info);
  }

  private void showJobDetails(Job job) {
    View root = this.getView();
    if (root == null || job == null) {
      return;
    }

    getTextView(root, R.id.job_title)
            .setText(job.getTitle());
    getTextView(root, R.id.job_description)
            .setText(Html.fromHtml(job.getDescrp()));
    
    TableLayout table = (TableLayout) root.findViewById(R.id.job_detail_table);
    table.addView(createTableRow(R.string.job_row_contact, job.getContact()));
    table.addView(createTableRow(R.string.job_row_hits, job.getHits()));
    table.addView(createTableRow(R.string.job_row_tags, job.getTagsAsString(getActivity())));
    table.addView(createTableRow(R.string.job_row_created, getDateFromString(job.getCreated())));
    String edited = job.getEdited();
    if (edited != null)
      table.addView(createTableRow(R.string.job_row_edited, getDateFromString(edited)));
  }
  
  private String getDateFromString(String dateString) {
    Long timestamp = Long.parseLong(dateString) * 1000;
    Date date = new Date(timestamp);
    return date.toString();
  }
  
  private TableRow createTableRow(int rowHeadStringID, String rowContentString) {
    Context ctx = getActivity();
    TableRow row = new TableRow(ctx);
    
    TextView columnHead = new TextView(ctx);
    columnHead.setText(ctx.getString(rowHeadStringID));
    columnHead.setPadding(TABLE_COLUMNS_PADDING, TABLE_COLUMNS_PADDING,
                          TABLE_COLUMNS_PADDING, TABLE_COLUMNS_PADDING);
    row.addView(columnHead);
    
    TextView columnContent = new TextView(ctx);
    columnContent.setGravity(Gravity.RIGHT);
    columnContent.setText(rowContentString);
    columnContent.setPadding(TABLE_COLUMNS_PADDING, TABLE_COLUMNS_PADDING,
                          TABLE_COLUMNS_PADDING, TABLE_COLUMNS_PADDING);
    row.addView(columnContent);
    return row;
  }

  @Override
  protected void postRestore() {
    //nothing to do
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_job, container, false);
    return root;
  }

}
