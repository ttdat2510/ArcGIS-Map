package com.example.arcgis_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.SceneView;

public class TrailheadsLayer extends AppCompatActivity {
  public static void start(Context context) {
    Intent starter = new Intent(context, TrailheadsLayer.class);
    context.startActivity(starter);
  }

  private MapView mMapView;


  private void addTrailheadsLayer() {
    String url = "https://sampleserver6.arcgisonline.com/arcgis/rest/services/World_Street_Map/MapServer";
    ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(url);
    FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);
    ArcGISMap map = mMapView.getMap();
    map.getOperationalLayers().add(featureLayer);
  }

  private void setupMap() {
    if (mMapView != null) {
      // *** ADD ***
      ArcGISRuntimeEnvironment.setLicense(getResources().getString(R.string.arcgis_license_key));
      Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
      double latitude = 34.0270;
      double longitude = -118.8050;
      int levelOfDetail = 13;
      ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
      mMapView.setMap(map);
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trailheads_layer);
    mMapView = findViewById(R.id.mapView);
    setupMap();
    // *** ADD ***
    addTrailheadsLayer();
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
