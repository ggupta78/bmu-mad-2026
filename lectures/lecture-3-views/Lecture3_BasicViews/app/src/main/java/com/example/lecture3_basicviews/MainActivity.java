package com.example.lecture3_basicviews;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

  TextView txtView;
  Button btnUpdate;
  EditText editText;
  ImageView imgView;
  ImageButton imgButton;
  LinearLayout linearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    txtView = findViewById(R.id.textView);
    btnUpdate = findViewById(R.id.btn_update);
    editText = findViewById(R.id.edt_txt_name);
    imgView = findViewById(R.id.img_view);
    imgButton = findViewById(R.id.img_button);
    linearLayout = findViewById(R.id.linear_layout);

    txtView.setText("Welcome to Android!");

    btnUpdate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = editText.getText().toString();
        txtView.setText(name);
        Toast.makeText(MainActivity.this, "Update button clicked!",
          Toast.LENGTH_SHORT).show();
      }
    });

    imgButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (linearLayout.getOrientation() == LinearLayout.HORIZONTAL) {
          linearLayout.setOrientation(LinearLayout.VERTICAL);
          Toast.makeText(MainActivity.this,
            "Linear Layout became Vertical", Toast.LENGTH_SHORT).show();
          Log.i("myTag", "LL became vertical");
        } else {
          linearLayout.setOrientation(LinearLayout.HORIZONTAL);
          Toast.makeText(MainActivity.this,
            "Linear Layout became Horizontal", Toast.LENGTH_SHORT).show();
          Log.i("myTag", "LL became horizontal");
        }

        Log.w("myTag", "Linear Layout Orientation Changed");
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