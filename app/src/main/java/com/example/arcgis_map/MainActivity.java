package com.example.arcgis_map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Button buttonMapBasic, buttonRoute, buttonSceneView, buttonTrailheadsLayer, buttonLayerFromAnItem,
      buttonDisplayLinePoint;


  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
    initAction();
  }

  private void initAction() {
    buttonMapBasic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MapBasic.start(context);
      }
    });

    buttonSceneView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SceneViewActivity.start(context);
      }
    });

    buttonTrailheadsLayer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        TrailheadsLayer.start(context);
      }
    });

    buttonLayerFromAnItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AddLayerFromAnItem.start(context);
      }
    });

    buttonRoute.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        RouteActivity.start(context);
      }
    });

    buttonDisplayLinePoint.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DisplayLinePointActivity.start(context);
      }
    });
  }

  private void init() {
    context = this;
    buttonMapBasic = findViewById(R.id.buttonMapBasic);
    buttonSceneView = findViewById(R.id.buttonSceneView);
    buttonTrailheadsLayer = findViewById(R.id.buttonTrailheadsLayer);
    buttonLayerFromAnItem = findViewById(R.id.buttonLayerFromAnItem);
    buttonRoute = findViewById(R.id.buttonRoute);
    buttonDisplayLinePoint= findViewById(R.id.buttonDisplayLinePoint);
  }
}
