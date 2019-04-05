package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Pantry extends AppCompatActivity {
    public static Shelf[] shelves;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("hi test!");
    }
    public void add_shelf(View view) {
        //insert code for getting another Shelf item info
        Shelf shelf = new Shelf("test_" + shelves.length);
        Shelf[] shelves = new Shelf[this.shelves.length + 1];
        for(int i = 0; i < this.shelves.length; i++) {
            shelves[i] = this.shelves[i];
        }
        shelves[this.shelves.length] = shelf;
        this.shelves = shelves;
    }
    public void updateVisualList() {
        TableLayout shelf_list = findViewById(R.id.shelf_list);
        int shelfQuantity = shelf_list.getChildCount();
        for(int i = 0; i < shelfQuantity; i++) {
            View testRow = shelf_list.getChildAt(i);
            if(testRow instanceof TableRow) ((TableRow) testRow).removeAllViews();
        }

        for(int i = 0; i < shelves.length; i++) {
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
        button.setText(shelves[ID].getLabel());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                Intent intent = new Intent(Pantry.this, Shelf.class);
                intent.putExtra("id_" + button.getId(), button.getId());
                startActivity(intent);
            }
        });
        return button;
    }
}