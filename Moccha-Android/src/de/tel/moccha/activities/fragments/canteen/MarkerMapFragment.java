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

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import de.tel.moccha.activities.R;

/**
 * Represents the MarkerMapFragment which shows the given marker and the current 
 * location on the google map.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class MarkerMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMyLocationChangeListener {

  public static final String ARG_Marker_LIST = "arg.marker.list";
  private static final String TAG_MARKER_LIST = "tag.marker.list";

  private Marking locs[];

  /**
   * The first location of the user to move the camera.
   */
  private Location firstLoc = null;
  
  /**
   * The google map which is used to show the map to the user.
   */
  private GoogleMap map = null;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }

    if (savedInstanceState != null) {
      locs = (Marking[]) savedInstanceState.getSerializable(TAG_MARKER_LIST);
      if (locs == null) {
        locs = (Marking[]) savedInstanceState.getSerializable(ARG_Marker_LIST);
      }
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_MARKER_LIST, locs);
  }

  /**
   * Represents the implementation of the  OnMapReadyCallback interface.
   *
   * @param map the map which is used
   */
  public void onMapReady(GoogleMap map) {
    this.map = map;
    map.setOnMyLocationChangeListener(this);
    map.setMyLocationEnabled(true);
    map.setOnInfoWindowClickListener(this);
    if (locs != null && locs.length > 0) {
      for (Marking loc : locs) {
        map.addMarker(new MarkerOptions()
                .position(loc.getMarkingPos())
                .title(loc.getMarkingTitle()));
      }
      Location loc = map.getMyLocation();
      LatLng currentLatLong;
      if (loc != null) {
        currentLatLong = new LatLng(loc.getLatitude(), loc.getLongitude());
      } else {
        currentLatLong = readLatLongFromStringXML(R.string.map_default_lat,
                                                  R.string.map_default_long);
      }   
      setCameraPosition(map, currentLatLong);
    }
  }
  
  /**
   * Is called if the info window of the markers are clicked.
   * @TODO add connection to detail
   * @param marker marker which was clicked
   */
  public void onInfoWindowClick(Marker marker) {
    
  }

  /**
   * Is called if the location changes, sets the location and camera zoom
   * for the first time after that the camera is no more adjusted.
   * 
   * @param lctn the new location
   */
  public void onMyLocationChange(Location lctn) {
    if (firstLoc == null && map != null) {
      firstLoc = lctn;
      LatLng latLng = new LatLng(lctn.getLatitude(), lctn.getLongitude());
      setCameraPosition(map, latLng);
    }
  }

  /**
   * Sets the camera position to the given LatLng object which contains
   * the latitude and longitude.
   * @param map the map on which the camera is set
   * @param currentLatLong the current lat and long values
   */
  private void setCameraPosition(GoogleMap map, LatLng currentLatLong) {
      float zoom = Float.parseFloat(getString(R.string.map_default_zoom));
      CameraPosition cameraPosition = new CameraPosition.Builder()
              .target(currentLatLong)
              .zoom(zoom)
              .build();
      CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
      map.moveCamera(cameraUpdate);
  }
  
  
  /**
   * Reads from the string xml the lat and long value and returns
   * the corresponding LatLng object.
   * @param latID the string id of the latitude value
   * @param longID the string if of the longitude value
   * @return the corresponding latlng value
   */
  private LatLng readLatLongFromStringXML(int latID, int longID) {
    double lat = Double.parseDouble(getString(latID));
    double longitude = Double.parseDouble(getString(longID));
    return new LatLng(lat, longitude);
  }
}
