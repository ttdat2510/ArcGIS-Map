package com.example.arcgis_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;

public class AddLayerFromAnItem extends AppCompatActivity {
  public static void start(Context context) {
    Intent starter = new Intent(context, AddLayerFromAnItem.class);
    context.startActivity(starter);
  }

  private MapView mMapView;

  private void setupMap() {
    if (mMapView != null) {
      Basemap.Type basemapType = Basemap.Type.TOPOGRAPHIC;
      double latitude = 34.09042;
      double longitude = -118.71511;
      int levelOfDetail = 11;
      ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
      mMapView.setMap(map);
//      addLayer(map, getString(R.string.Parks_And_Open_Space_Layer));
//      addLayer(map, getString(R.string.Trails_Styled_Layer));
      addLayer(map, getString(R.string.Trailheads_Styled_Layer));
    }
  }

  private void addLayer(final ArcGISMap map, String itemID) {
    Portal portal = new Portal("http://www.arcgis.com");
    final PortalItem portalItem = new PortalItem(portal, itemID);
    FeatureLayer featureLayer = new FeatureLayer(portalItem,0);
    featureLayer.addDoneLoadingListener(() -> {
      if (featureLayer.getLoadStatus() == LoadStatus.LOADED) {
        map.getOperationalLayers().add(featureLayer);
      }
    });
    featureLayer.loadAsync();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_layout_trailheads_layer);
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
