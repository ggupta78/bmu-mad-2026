package com.example.appactivitylifecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageManager;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  TextView textView;
  Button btnCounter, btnSecondActivity;

  int count = 0;

  private static final int REQUEST_CAMERA_PERMISSION = 200;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    if (ContextCompat.checkSelfPermission(this,
      Manifest.permission.CAMERA)
      != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.CAMERA},
        REQUEST_CAMERA_PERMISSION);
    } else {
      // Permission has already been granted
      useCamera();
    }



    Log.i("first-activity", "FirstActivity onCreate");

    textView = findViewById(R.id.textView);
    btnCounter = findViewById(R.id.btnCounter);
    btnSecondActivity = findViewById(R.id.btnSecondActivity);

    btnCounter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        count = count + 1;
        textView.setText(String.format(Locale.getDefault(), "%d", count));
      }
    });

    btnSecondActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        //  Start Second Activity
//        Intent i = new Intent(getApplicationContext(),
//        SecondActivity.class);
//        i.putExtra("count", count);
//        startActivity(i);

        //Implicit Intent 1
//        Intent intent = new Intent(Intent.ACTION_VIEW,
//          Uri.parse("https://www.slashdot.org"));
//        startActivity(intent);
//
        // Implicit Intent 2
        Uri location = Uri.parse(
          "geo:28.247907328509715,76.81364835378433?q=restaurants");
        Intent uriIntent = new Intent(Intent.ACTION_VIEW,
          location);
        startActivity(uriIntent);

      }
    });

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
      (v, insets) -> {
        Insets systemBars = insets.getInsets(
          WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right,
          systemBars.bottom);
        return insets;
      });
  }

  private void useCamera() {
    Toast.makeText(MainActivity.this, "Can Use Camera!",
      Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.i("first-activity", "FirstActivity onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.i("first-activity", "FirstActivity onResume");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.i("first-activity", "FirstActivity onRestart");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.i("first-activity", "FirstActivity onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.i("first-activity", "FirstActivity onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.i("first-activity", "FirstActivity onDestroy");
  }
}