package com.example.fb26sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

  EditText editText;
  Button btnAdd;
  TextView textView;

  FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference referenceEmployees = database.getReference().child("employees");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    editText = findViewById(R.id.edit_text);
    btnAdd = findViewById(R.id.btn_add);
    textView = findViewById(R.id.textView);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = editText.getText().toString();
        referenceEmployees.child("100").child("username").setValue(username);
      }
    });

    referenceEmployees.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        String updatedUsername = (String) snapshot.child("100").child("name").getValue();
        textView.setText(updatedUsername);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

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