package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

public class Pantry extends AppCompatActivity {
    public static ArrayList<Shelf> shelves = new ArrayList<>();
    public static ArrayList<Food> expiring = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        expiring = new ArrayList<>();
        Drawable[] expirationList = new Drawable[3];
        Toast.makeText(this, (new Date(118, 12, 16)).toString(), Toast.LENGTH_SHORT).show();


        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("hi test!");
        updateVisualList();
    }

    public void add_shelf(View view) {
        startActivity(new Intent(Pantry.this, AddShelf.class));
    }
    public void updateVisualList() {
        TableLayout shelf_list = findViewById(R.id.shelf_list);
        int shelfQuantity = shelf_list.getChildCount();
        for(int i = 0; i < shelfQuantity; i++) {
            View testRow = shelf_list.getChildAt(i);
            if(testRow instanceof TableRow) ((TableRow) testRow).removeAllViews();
        }

        for(int i = 0; i < shelves.size(); i++) {
            TableRow shelf_row = new TableRow(this);
            shelf_row.addView(getButton(i));
            shelf_list.addView(shelf_row);
        }
    }
    public Button getButton(int ID) {
        Button button = new Button(this);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(parameters);
        button.setId(ID);
        button.setText(shelves.get(ID).getLabel());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                Intent intent = new Intent(Pantry.this, ShelfManager.class);
                intent.putExtra("id", button.getId() + "");
                startActivity(intent);
            }
        });
        return button;
    }
    public static void deleteShelf(int ID) {
        Pantry.shelves.remove(ID);
    }
    public static void deleteAllShelves() {
        Pantry.shelves = new ArrayList<>();
    }

    public void addfood(View view) {
        Intent intent = new Intent(Pantry.this, AddFood.class);
        intent.putExtra("id", 0 + "");
        startActivity(intent);
    }
}