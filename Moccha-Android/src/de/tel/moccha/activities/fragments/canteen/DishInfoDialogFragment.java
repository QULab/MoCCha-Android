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
import de.tel.moccha.entities.canteen.Additive;
import de.tel.moccha.entities.canteen.Dish;
import java.util.List;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class DishInfoDialogFragment extends DialogFragment {

  /**
   * The argument key for the canteen as argument.
   */
  public static final String ARG_DISH_INFO = "arg.dish.info";

  /**
   * The tag key to save the canteen information.
   */
  private static final String TAG_DISH_INFO = "tag.dish.info";

  /**
   * The padding for the text views.
   */
  private static final int PADDING = 3;

  /**
   * The dish which contains the necessary informations.
   */
  private Dish dish;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {

    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }

    if (savedInstanceState != null) {
      dish = (Dish) savedInstanceState.getSerializable(TAG_DISH_INFO);
      if (dish == null) {
        dish = (Dish) savedInstanceState.getSerializable(ARG_DISH_INFO);
      }
    }

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View v = inflater.inflate(getDialogLayout(), null);
    TextView header = (TextView) v.findViewById(R.id.alert_dish_info_header);
    header.setText(dish.getName());

    TextView price = (TextView) v.findViewById(R.id.alert_dish_info_prices);
    price.setText(String.format(getString(R.string.alert_dish_info_price), dish.getPrice()));

    List<Additive> additeves = dish.getAdditives();
    if (additeves != null) {
      TableLayout table = (TableLayout) v.findViewById(R.id.alert_dish_info_additives);
      for (Additive a : additeves) {
        table.addView(createTableRowWithAdditiveContent(a));
      }
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    return builder.setView(v).create();
  }

  private TableRow createTableRowWithAdditiveContent(Additive content) {
    TableRow row = new TableRow(getActivity());
    String additiveID = String.format(getString(R.string.alert_dish_info_additive), content.getID());
    row.addView(createTextViewWithContent(additiveID));
    row.addView(createTextViewWithContent(content.getName()));
//    row.addView(createTextViewWithContent(content.getInformation()));
    return row;
  }
  
  private TextView createTextViewWithContent(String content) {
    TextView view = new TextView(getActivity());
    view.setText(content);
    return view;
  }

  protected int getDialogLayout() {
    return R.layout.dialog_dish_info;
  }
}
