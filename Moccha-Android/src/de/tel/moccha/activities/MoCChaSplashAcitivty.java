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
package de.tel.moccha.activities;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import de.zell.android.util.activities.SplashActivity;

/**
 * The splash screen of the application.
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class MoCChaSplashAcitivty extends SplashActivity {

  /**
   * The constant which contains the time for the splash screen.
   */
  private static final long SPLASH_TIME = 2500l;
  @Override
  protected Class getMainClass() {
    return MoCChaMainNavigationActivity.class;
  }

  @Override
  protected void startAsyncTasks() {
    //TODO database init
  }

  @Override
  protected Animation getAnimation() {
    return null;
  }

  @Override
  protected long getSplashTime() {
    return SPLASH_TIME;
  }

  @Override
  protected Drawable getDrawable() {
    return getResources().getDrawable(R.drawable.splash_moccha);
  }

}
