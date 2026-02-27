package com.example.todoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  EditText txtAdd;
  Button btnAdd;
  ListView listView;
  ArrayList<String> listItems;
  ArrayAdapter<String> arrayAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    txtAdd = findViewById(R.id.add_item_text);
    btnAdd = findViewById(R.id.add_btn);
    listView = findViewById(R.id.list_view);

//    listItems = new ArrayList<String>();
    listItems = FileHelper.readList(this);
    arrayAdapter = new ArrayAdapter<>(this,
      android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
    listView.setAdapter(arrayAdapter);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String item = txtAdd.getText().toString();
        txtAdd.setText("");
        listItems.add(item);
        FileHelper.writeList(listItems, getApplicationContext());
        arrayAdapter.notifyDataSetChanged();
      }
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position,
                              long id) {
        //Create Alert Dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Delete");
        alert.setMessage("Do you want to delete this item?");
        alert.setCancelable(false);

        //Cancel dialog if user selects No
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });

        //Delete item if user selects Yes
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            listItems.remove(position);
            arrayAdapter.notifyDataSetChanged();
            FileHelper.writeList(listItems, MainActivity.this);
          }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
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