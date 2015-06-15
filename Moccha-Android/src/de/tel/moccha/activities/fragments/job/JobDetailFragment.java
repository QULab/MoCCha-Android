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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import de.tel.moccha.activities.MoCChaMainNavigationActivity;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.job.Job;
import de.zell.android.util.PropertiesProvider;
import de.zell.android.util.activities.MainNavigationActivity;
import de.zell.android.util.async.AsyncGETRequester;
import de.zell.android.util.async.GetRequestInfo;
import de.zell.android.util.db.Entity;
import de.zell.android.util.fragments.EntityFragment;
import de.zell.android.util.fragments.FragmentReplacer;
import de.zell.android.util.fragments.WebviewFragment;
import de.zell.android.util.json.JSONUnmarshaller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the job detail fragment.
 * Contains all necessary details about the job for the user.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobDetailFragment extends EntityFragment {

  /**
   * The UTF-8 encoding key.
   */
  public static final String UTF_8_ENCODING = "UTF-8";
  
  /**
   * The JSON key for the job details.
   */
  public static final String JSON_JOB_DETAIL_KEY = "job";
  
  /**
   * The padding for the job detail table layout.
   */
  private static final int TABLE_COLUMNS_PADDING = 3;
  
  /**
   * The info menu item.
   */
  protected MenuItem item;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.job_pdf_detail, menu);
    item = menu.findItem(R.id.action_job_pdf_detail);
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().invalidateOptionsMenu();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem favItem) {
    if (favItem.getItemId() == R.id.action_job_pdf_detail) {
      Job job = (Job) entity;
      List<String> pdfs = job.getPdfs();
      final Bundle args = new Bundle();
      args.putString(WebviewFragment.ARG_WEBVIEW_FRAGMENT_URL, createPDFDetaillURL(pdfs != null ? pdfs.get(0) : null));
      args.putString(WebviewFragment.ARG_WEBVIEW_FRAGMENT_TITLE, job.getTitle());
      WebviewFragment pdfView = new WebviewFragment();
      pdfView.setArguments(args);
      FragmentReplacer.replace(getActivity().getSupportFragmentManager(), pdfView, FragmentReplacer.MAIN_CONTENT);
      return true;
    }
    return super.onOptionsItemSelected(favItem);
  }

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
        showEntity(entity);
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

  
  @Override
  protected void showEntity(Entity entity) {
    Job job = (Job) entity;
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
    if (edited != null) {
      table.addView(createTableRow(R.string.job_row_edited, getDateFromString(edited)));
    }
  }

  /**
   * Creates for the given PDF URL a correct URI which points to the PDF
   * of the current job.
   * 
   * @param pdfUrl the PDF URL which contains the necessary informations
   * @return the correct PDF URL
   */
  private String createPDFDetaillURL(String pdfUrl) {
    if (pdfUrl == null)
      return null;
    
    PropertiesProvider propProv = MoCChaMainNavigationActivity.propProvider;
    
    String pdfPrefix = propProv.getProperty(MoCChaMainNavigationActivity.PROP_KEY_JOBS_PDF_PREFIX);
    pdfUrl = String.format(pdfPrefix, pdfUrl);
    String encodedURL = null;
    try {
      encodedURL = URLEncoder.encode(pdfUrl, UTF_8_ENCODING);
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(JobDetailFragment.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String googlePDFViewerPrefix = propProv.getProperty(MoCChaMainNavigationActivity.PROP_KEY_GOOGLE_PDF_VIEWER_URL);
    return String.format(googlePDFViewerPrefix, encodedURL);
  }

  /**
   * Converts a timestamp as String to a valid date string.
   * @param dateString the timestamp
   * @return the valid date string
   */
  private String getDateFromString(String dateString) {
    Long timestamp = Long.parseLong(dateString) * 1000;
    Date date = new Date(timestamp);
    return date.toString();
  }

  /**
   * Creates a table row with the given head string which is identified with the
   * given id and the row content string.
   * 
   * @param rowHeadStringID the id which identifies the head string
   * @param rowContentString the row content string
   * @return the created table row
   */
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
    // do nothing
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_job, container, false);
    return root;
  }

}
