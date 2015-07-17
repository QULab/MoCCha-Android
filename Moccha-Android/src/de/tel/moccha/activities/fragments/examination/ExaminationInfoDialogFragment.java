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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.office.ExaminationOffice;
import de.tel.moccha.entities.office.OpeningHours;
import java.util.List;

/**
 * Represents the examination office info dialog which shows the opening hours.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class ExaminationInfoDialogFragment extends DialogFragment {

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
      if (office == null) {
        office = (ExaminationOffice) savedInstanceState.getSerializable(ARG_OFFICE_INFO);
      }
    }

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View v = inflater.inflate(getDialogLayout(), null);
    setDialogContent(v);

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
    return R.layout.dialog_opening_info;
  }

  /**
   * Places the content into the root view.
   *
   * @param root the root view
   */
  protected void setDialogContent(View root) {
    if (office != null) {
      List<OpeningHours> openings = office.getOpenings();
      TableLayout openingsLayout = (TableLayout) root.findViewById(R.id.opening_hours);
      for (OpeningHours hours : openings) {
        openingsLayout.addView(createTableRowForOpening(hours));
      }
    }
  }

  private TableRow createTableRowForOpening(OpeningHours hours) {
    TableRow row = new TableRow(getActivity());
    row.addView(createTextViewWithContent(weekDays[hours.getWeekDay() - 1]));
    if (isClosed(hours)) 
      row.addView(createTextViewWithContent(getString(R.string.examination_office_closed)));
    else {
      row.addView(createTextViewWithContent(getIntTimeAsString(hours.getBegin())));
      row.addView(createTextViewWithContent(getIntTimeAsString(hours.getEnd())));
    }
    return row;
  }

  protected TextView createTextViewWithContent(String content) {
    TextView view = new TextView(getActivity());
    view.setText(content);
    return view;
  }

  private boolean isClosed(OpeningHours hour) {
    return (hour.getBegin() == null && hour.getEnd() == null)
            || (hour.getBegin() == 0 && hour.getEnd() == 0);
  }

  private String getIntTimeAsString(Integer time) {
    if (time == null) {
      return null;
    }

    
    int hour = time / 100;
    int min = time - hour * 100;
    return String.format(getString(R.string.opening_string_format), hour, min);
  }

}
