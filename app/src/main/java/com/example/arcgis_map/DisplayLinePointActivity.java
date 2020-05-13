package com.example.arcgis_map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class DisplayLinePointActivity extends AppCompatActivity {

  // Private constructor to avoid client applications to use constructor
  public static void start(Context context) {
    Intent starter = new Intent(context, DisplayLinePointActivity.class);
    context.startActivity(starter);
  }


  private MapView mMapView;
  private GraphicsOverlay mGraphicsOverlay;

  private void setupMap() {
    if (mMapView != null) {
      Basemap.Type basemapType = Basemap.Type.IMAGERY_WITH_LABELS_VECTOR;
      double latitude = 34.09042;
      double longitude = -118.71511;
      int levelOfDetail = 12;
      ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
      mMapView.setMap(map);
    }
  }

  private void createGraphicsOverlay() {

    // Create graphics overlay and add it to map view
    mGraphicsOverlay = new GraphicsOverlay();
    mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
  }

  private void createPointGraphics() {

    // Create a point geometry
    Point point = new Point(-118.69333917997633, 34.032793670122885, SpatialReferences.getWgs84());

    // Create point symbol with outline
    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(226, 119, 40), 10.0f);
    pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2.0f));

    // Create point graphic with geometry & symbol
    Graphic pointGraphic = new Graphic(point, pointSymbol);

    // Add point graphic to graphic overlay
    mGraphicsOverlay.getGraphics().add(pointGraphic);
  }

  private void createPolylineGraphics() {

    // Create polyline geometry
    PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
    polylinePoints.add(new Point(-118.67999016098526, 34.035828839974684));
    polylinePoints.add(new Point(-118.65702911071331, 34.07649252525452));
    Polyline polyline = new Polyline(polylinePoints);

    // Create symbol for polyline
    SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 3.0f);

    // Create a polyline graphic with geometry and symbol
    Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);

    // Add polyline to graphics overlay
    mGraphicsOverlay.getGraphics().add(polylineGraphic);
  }

  private void createPolygonGraphics() {

    // Create polygon geometry
    PointCollection polygonPoints = new PointCollection(SpatialReferences.getWgs84());
    polygonPoints.add(new Point(-118.70372100524446, 34.03519536420519));
    polygonPoints.add(new Point(-118.71766916267414, 34.03505116445459));
    polygonPoints.add(new Point(-118.71923322580597, 34.04919407570509));
    polygonPoints.add(new Point(-118.71631129436038, 34.04915962906471));
    polygonPoints.add(new Point(-118.71526020370266, 34.059921300916244));
    polygonPoints.add(new Point(-118.71153226844807, 34.06035488360282));
    polygonPoints.add(new Point(-118.70803735010169, 34.05014385296186));
    polygonPoints.add(new Point(-118.69877903513455, 34.045182336992816));
    polygonPoints.add(new Point(-118.6979656552508, 34.040267760924316));
    polygonPoints.add(new Point(-118.70259112469694, 34.038800278306674));
    polygonPoints.add(new Point(-118.70372100524446, 34.03519536420519));
    Polygon polygon = new Polygon(polygonPoints);

    // Create symbol for polygon with outline
    SimpleFillSymbol polygonSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.rgb(226, 119, 40),
        new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2.0f));

    // Create polygon graphic with geometry and symbol
    Graphic polygonGraphic = new Graphic(polygon, polygonSymbol);

    // Add polygon graphic to graphics overlay
    mGraphicsOverlay.getGraphics().add(polygonGraphic);
  }

  private void createGraphics() {
    createGraphicsOverlay();
//    createPointGraphics();
    createPolylineGraphics();
//    createPolygonGraphics();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_line_and_polygon);
    mMapView = findViewById(R.id.mapView);
    setupMap();
    createGraphics();
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
