/*
 * Copyright (C) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.glass.samples.level;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Activity used to retrieve sensor information and draw the level line.
 */
public class LevelActivity extends Activity implements SensorEventListener {

  private SensorManager mSensorManager;
  private LevelView mLevelView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_level);

    mLevelView = (LevelView) findViewById(R.id.level);
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    registerListeners();
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerListeners();
  }

  @Override
  protected void onPause() {
    unregisterListeners();
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterListeners();
  }

  /**
   * Register this activity as a listener for gravity sensor changes.
   */
  private void registerListeners() {
    mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
        SensorManager.SENSOR_DELAY_NORMAL);
  }

  /**
   * Unregister this activity as a listener.
   */
  private void unregisterListeners() {
    mSensorManager.unregisterListener(this);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
    // Nothing to do here.
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
      computeOrientation(event);
    }
  }

  /**
   * Compute the orientation angle.
   * 
   * @param event Gravity values.
   */
  private void computeOrientation(SensorEvent event) {
    float angle = (float) -Math.atan(event.values[0]
        / Math.sqrt(event.values[1] * event.values[1] + event.values[2] * event.values[2]));
    mLevelView.setAngle(angle);
  }

}
