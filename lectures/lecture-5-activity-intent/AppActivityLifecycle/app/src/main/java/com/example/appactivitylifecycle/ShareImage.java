package com.example.appactivitylifecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShareImage extends AppCompatActivity {

  ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_share_image);

    Intent intent = getIntent();
    String action = intent.getAction();
    String type = intent.getType();

    // 1. Check if the intent action is SEND and the type is an image
    if (Intent.ACTION_SEND.equals(action) && type != null) {
      if (type.startsWith("image/")) {
        handleSendImage(intent);
      }
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
      (v, insets) -> {
        Insets systemBars = insets.getInsets(
          WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right,
          systemBars.bottom);
        return insets;
      });
  }

  void handleSendImage(Intent intent) {
    // 2. Extract the URI of the shared image
    Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);

    if (imageUri != null) {
      // 3. Update your UI (assuming you have an ImageView with ID 'imageView')
      ImageView imageView = findViewById(R.id.imageView);
      imageView.setImageURI(imageUri);
    }
  }
}