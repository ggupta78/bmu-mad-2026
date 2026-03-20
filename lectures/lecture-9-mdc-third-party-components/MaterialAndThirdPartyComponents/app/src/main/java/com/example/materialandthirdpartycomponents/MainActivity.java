package com.example.materialandthirdpartycomponents;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
  BottomNavigationView bottomNavBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    bottomNavBar = findViewById(R.id.bottom_navigation);
    bottomNavBar.setSelectedItemId(R.id.home_menu);

    bottomNavBar.setOnItemSelectedListener(
      new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          if (item.getItemId() ==  R.id.settings_menu) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            //finish() is called to remove this activity from the Back Stack,
            // so that user does not come back to this current activity on clicking Back
            finish();
            return true;
          }

          return false;
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
}