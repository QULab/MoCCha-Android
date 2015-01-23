/*
 * Copyright 2014 Quality and Usability Lab, Telekom Innvation Laboratories, TU Berlin..
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

package de.tel.moccha.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import de.tel.moccha.activities.R;
import de.zell.android.util.PropertiesProvider;

/**
 * Represents the Fragment for the welcome page.
 * 
 * Created by deLaczkovich on 26-Aug-14.
 * @author deLaczkovich, Christopher Zell <zelldon91@googlemail.com>
 */
public class WelcomeFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
    
    PropertiesProvider.setPropertyFile("/../../../tel/moccha/activties/fragments/moccha.properties");
    PropertiesProvider propProvider = PropertiesProvider.getInstance();
    Toast.makeText(getActivity(),
                   propProvider.getProperty("canteens.url"),
                   Toast.LENGTH_LONG).show();
   
           
    return rootView;
  }
}
