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

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.office.ExaminationOffice;
import de.tel.moccha.entities.office.OpeningHours;
import java.util.List;

/**
 * Represents the examination office info dialog
 * which shows the opening hours.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ExaminationInfoDialogFragment extends DialogFragment{
  /**
   * The argument key for the office as argument.
   */
  public static final String ARG_OFFICE_INFO = "office.examination.info";
  
  /**
   * The tag key to save the office information.
   */
  private static final String TAG_INFO_OFFICE = "tag.office.info";
  
  /**
   * The office which contains the information which should be shown.
   */
  private ExaminationOffice office;
  
  /**
   * Contains the week days.
   */
  private String weekDays[];
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    weekDays = getResources().getStringArray(R.array.week_days);
    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }
    
    if (savedInstanceState != null) {
      office = (ExaminationOffice) savedInstanceState.getSerializable(TAG_INFO_OFFICE);
      if (office == null)
        office = (ExaminationOffice) savedInstanceState.getSerializable(ARG_OFFICE_INFO);
    }

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View v = inflater.inflate(getDialogLayout(), null);
    setDialogContent(v);
    setOnPositiveClickListener(v);
    setOnNegativeClickListener(v);
    
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    return builder.setView(v).create();
  }
  
  
  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_INFO_OFFICE, office);
  }
  
  /**
   * Returns the dialog layout id which should be shown by the dialog fragment.
   * 
   * @return the id of the layout
   */
  protected int getDialogLayout() {
    return R.layout.opening_info_dialog;
  }
  
  /**
   * Places the content into the root view.
   * 
   * @param root the root view
   */
  protected void setDialogContent(View root) {
    if (office != null) {
      List<OpeningHours> openings = office.getOpenings();
      LinearLayout openingsLayout = (LinearLayout) root.findViewById(R.id.opening_hours);
      for (OpeningHours hours : openings) {
        TextView textView = new TextView(getActivity());
        textView.setText(getOpeningHour(hours));
        openingsLayout.addView(textView);
      }
      
      TextView header = (TextView) root.findViewById(R.id.alert_info_header);
      header.setText(getString(R.string.examination_office_opening_header));
    }
  }
  
  protected String getOpeningHour(OpeningHours hour) {
    if (weekDays != null)
      return String.format(getString(R.string.opening_string_format),
                            weekDays[hour.getWeekDay()-1],
                            hour.getBegin(), hour.getEnd());
    return null;
  }
  
  /**
   * Sets the positive click listener for the dialog fragment.
   * @param root the root view
   */
  protected void setOnPositiveClickListener(View root) {
    Button okButton = (Button) root.findViewById(R.id.alert_info_ok);
    okButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View arg0) {
        ExaminationInfoDialogFragment.this.dismiss();
      }
    });
  }
  
  /**
   * Sets the negative click listener for the dialog fragment.
   * @param root the root view
   */
  protected void setOnNegativeClickListener(View root) {
    
  }
}
