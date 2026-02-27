package com.example.todoapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
  public static final String FILENAME = "list-data.dat";

  public static void writeList(ArrayList<String> itemList,
                               Context context) {
    try {
      File file = new File(context.getFilesDir(), FILENAME);
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oas = new ObjectOutputStream(fos);
      oas.writeObject(itemList);
      oas.close();

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public static ArrayList<String> readList(Context context) {
    ArrayList<String> itemList = null;

    try {
      File file = new File(context.getFilesDir(), FILENAME);
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);
      itemList = (ArrayList<String>) ois.readObject();
      fis.close();
    } catch (FileNotFoundException e) {
      itemList = new ArrayList<String>(); // The first time the file will not exist
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return itemList;
  }
}
