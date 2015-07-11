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

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import de.tel.moccha.activities.R;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class MarkerMapFragment extends SupportMapFragment implements OnMapReadyCallback {
  public static final String ARG_Marker_LIST = "arg.marker.list";
  private static final String TAG_MARKER_LIST = "tag.marker.list";
  
  private Marking locs[];

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    
    if (savedInstanceState == null) {
      savedInstanceState = getArguments();
    }
    
    if (savedInstanceState != null) {
      locs = (Marking[]) savedInstanceState.getSerializable(TAG_MARKER_LIST);
      if (locs == null)
        locs = (Marking[]) savedInstanceState.getSerializable(ARG_Marker_LIST);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(TAG_MARKER_LIST, locs);
  }

  
  
  
  /**
   * impl OnMapReadyCallback interface
   * @param map
   */
  public void onMapReady(GoogleMap map) {
    if (locs != null && locs.length > 0) {
      for(Marking loc : locs) {
        map.addMarker(new MarkerOptions()
                            .position(loc.getMarkingPos())
                            .title(loc.getMarkingTitle()));
      }
      
      float zoom = Float.parseFloat(getString(R.string.canteen_map_zoom));
      CameraPosition cameraPosition = new CameraPosition.Builder()
              .target(new LatLng(52.5, 13.5))
              .zoom(zoom)
              .build();
      CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
      map.moveCamera(cameraUpdate);
    }
  }
  
  
  
}
