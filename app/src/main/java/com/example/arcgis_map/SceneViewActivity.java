package com.example.arcgis_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISScene;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Camera;
import com.esri.arcgisruntime.mapping.view.SceneView;

public class SceneViewActivity extends AppCompatActivity {
  public static void start(Context context) {
      Intent starter = new Intent(context, SceneViewActivity.class);
      context.startActivity(starter);
  }
  
  private SceneView mSceneView;

  private void setupMapSceneView() {
      if (mSceneView != null) {
      ArcGISRuntimeEnvironment.setLicense(getResources().getString(R.string.arcgis_license_key));
        double latitude = 34.0270;
        double longitude = -118.8050;
      double altitude = 25000.0;
      double heading = 0.1;
      double pitch = 45.0;
      double roll = 0.0;

      ArcGISScene scene = new ArcGISScene();
      scene.setBasemap(Basemap.createStreets());
      mSceneView.setScene(scene);
      Camera camera = new Camera(latitude, longitude, altitude, heading, pitch, roll);
      mSceneView.setViewpointCamera(camera);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scene_view_main);
    // *** UPDATE ***
    mSceneView = findViewById(R.id.sceneView);
    setupMapSceneView();
  }

  @Override
  protected void onPause() {
    if (mSceneView != null) {
      mSceneView.pause();
    }
    super.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mSceneView != null) {
      mSceneView.resume();
    }
  }

  @Override
  protected void onDestroy() {
    if (mSceneView != null) {
      mSceneView.dispose();
    }
    super.onDestroy();
  }
}
