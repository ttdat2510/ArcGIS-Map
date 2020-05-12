package com.example.arcgis_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MapBasic extends AppCompatActivity {
  // Private constructor to avoid client applications to use constructor
  public static void start(Context context) {
      Intent starter = new Intent(context, MapBasic.class);
      context.startActivity(starter);
  }

  private MapView mMapView;

  private void setupMap() {
    if (mMapView != null) {
      // *** ADD ***
      ArcGISRuntimeEnvironment.setLicense(getResources().getString(R.string.arcgis_license_key));
      Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
      double latitude = 10.789848;
      double longitude = 106.687704;
      int levelOfDetail = 13;
      ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
      mMapView.setMap(map);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_view_basic);
    // *** ADD ***
    mMapView = findViewById(R.id.mapView);
    setupMap();
  }

  @Override
  protected void onPause() {
    if (mMapView != null) {
      mMapView.pause();
    }
    super.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mMapView != null) {
      mMapView.resume();
    }
  }

  @Override
  protected void onDestroy() {
    if (mMapView != null) {
      mMapView.dispose();
    }
    super.onDestroy();
  }
}