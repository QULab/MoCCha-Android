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

import static android.R.attr.fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import de.tel.moccha.activities.R;
import de.tel.moccha.entities.canteen.Canteen;
import de.tel.moccha.util.StringFormatter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import de.zell.android.util.fragments.FragmentReplacer;

/**
 * Represents the class for a info dialog fragment.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CanteenInfoDialogFragment extends DialogFragment implements OnMapReadyCallback {

  /**
   * The argument key for the canteen as argument.
   */
  public static final String ARG_CANTEEN_INFO = "canteen.info";

  /**
   * The tag key to save the canteen information.
   */
  private static final String TAG_INFO_CANTEEN = "tag.info";

  /**
   * The canteen which contains the information which should be shown.
   */
  private Canteen canteen;

  /**
   * The formatter which will be used to format the strings given by id.
   */
  private StringFormatter formatter;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    formatter = new StringFormatter(getActivity());

    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }

    if (savedInstanceState != null) {
      canteen = (Canteen) savedInstanceState.getSerializable(ARG_CANTEEN_INFO);
    }

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View v = inflater.inflate(getDialogLayout(), null);
    setDialogContent(v);
    setOnPositiveClickListener(v);
    setOnNegativeClickListener(v);

    SupportMapFragment mapFragment
            = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.canteen_dialog_map);
    mapFragment.getMapAsync(this);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    return builder.setView(v).create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    removeMapFragment();
  }
  
  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_INFO_CANTEEN, canteen);
  }

  /**
   * Returns the dialog layout id which should be shown by the dialog fragment.
   *
   * @return the id of the layout
   */
  protected int getDialogLayout() {
    return R.layout.canteen_info_dialog;
  }

  /**
   * Places the content into the root view.
   *
   * @param root the root view
   */
  protected void setDialogContent(View root) {
    if (canteen != null) {
      getTextView(root, R.id.canteen_address)
              .setText(formatter.getFormatedHTMLString(R.string.canteen_address, canteen.getAddress()));
      getTextView(root, R.id.canteen_fax)
              .setText(formatter.getFormatedHTMLString(R.string.canteen_fax, canteen.getFax()));
      getTextView(root, R.id.canteen_phone)
              .setText(formatter.getFormatedHTMLString(R.string.canteen_phone, canteen.getPhone()));
      getTextView(root, R.id.canteen_opening_hours)
              .setText(formatter.getFormatedHTMLString(R.string.canteen_opening_hours, canteen.getOpeningHours()));

      TextView header = (TextView) root.findViewById(R.id.alert_info_header);
      header.setText(canteen.getTitle());
    }
  }

  /**
   * Sets the positive click listener for the dialog fragment.
   *
   * @param root the root view
   */
  protected void setOnPositiveClickListener(View root) {
    Button okButton = (Button) root.findViewById(R.id.alert_info_ok);
    okButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View arg0) {
        CanteenInfoDialogFragment.this.dismiss();
      }
    });
  }

  /**
   * Removes the current map fragment via the supported fragment manager.
   * The method is called after the dialog is detached, because otherwise
   * the application will crash. If the dialog is showed again the map is new created
   * and without the removing a fragment already exits with the same id.
   */
  private void removeMapFragment() {
    SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
            .findFragmentById(R.id.canteen_dialog_map);
    if (mapFragment != null) {
      getActivity().getSupportFragmentManager().beginTransaction().remove(mapFragment).commit();
    }
  }

  /**
   * Sets the negative click listener for the dialog fragment.
   *
   * @param root the root view
   */
  protected void setOnNegativeClickListener(View root) {

  }

  /**
   * Returns the text view for the given id from the root view.
   *
   * @param root the root view
   * @param id the id which identifies the view
   * @return the corresponding view
   */
  private TextView getTextView(View root, int id) {
    return ((TextView) root.findViewById(id));
  }

  @Override
  public void onMapReady(GoogleMap map) {
    if (canteen != null) {
      LatLng latlng = new LatLng(canteen.getLatitude(), canteen.getLongitude());
      map.addMarker(new MarkerOptions()
              .position(latlng)
              .title(canteen.getTitle()));

      float zoom = Float.parseFloat(getString(R.string.canteen_map_zoom));
      CameraPosition cameraPosition = new CameraPosition.Builder()
              .target(latlng)
              .zoom(zoom)
              .build();
      CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
      map.moveCamera(cameraUpdate);
    }
  }
}
