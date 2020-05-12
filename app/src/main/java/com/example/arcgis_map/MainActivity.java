package com.example.arcgis_map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Button buttonMapBasic;
  private Button buttonSceneView;
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
  }

  private void init() {
    context = this;
    buttonMapBasic = findViewById(R.id.buttonMapBasic);
    buttonSceneView = findViewById(R.id.buttonSceneView);
  }
}
