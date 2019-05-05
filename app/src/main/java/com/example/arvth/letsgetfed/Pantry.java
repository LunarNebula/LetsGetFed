package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class Pantry extends AppCompatActivity {
    private static final String TAG = "Pantry";

    // variables
    public static ArrayList<Shelf> shelves = new ArrayList<>();
    public static ArrayList<Food> expiring = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        Log.d(TAG, "onCreate: started");
        //RecyclerViewPantry();
        expiring = new ArrayList<>();
        Drawable[] expirationList = new Drawable[3];


        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("hi test!");
        updateVisualList();
    }

    // thanks to CodingWithMitch for the Recycler View tutorial on YouTube
//    public void RecyclerViewPantry() {
//        Log.d(TAG, "RecyclerViewPantry: got here");
//
//        RecyclerView recyclerView1 = findViewById(R.id.pantry_recyclerview);
//        recyclerView1.setAdapter(adapter1);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
//    }
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
//    public void add_shelf(View view) {
//        startActivity(new Intent(Pantry.this, AddShelf.class));
//    }
//    public void updateVisualList() {
//        TableLayout shelf_list = findViewById(R.id.shelf_list);
//        int shelfQuantity = shelf_list.getChildCount();
//        for(int i = 0; i < shelfQuantity; i++) {
//            View testRow = shelf_list.getChildAt(i);
//            if(testRow instanceof TableRow) ((TableRow) testRow).removeAllViews();
//        }
//
//        for(int i = 0; i < shelves.size(); i++) {
//            TableRow shelf_row = new TableRow(this);
//            shelf_row.addView(getButton(i));
//            shelf_list.addView(shelf_row);
//        }
//    }
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

    public void toPantryClick(View view){
        startActivity(new Intent(Pantry.this, Pantry.class));
    }

    public void toAlertsClick(View view){
        startActivity(new Intent(Pantry.this, Alert.class));
    }

    public void toSettingsClick(View view){
        startActivity(new Intent(Pantry.this, Settings.class));
    }
//    public void addfood(View view) {
//        Intent intent = new Intent(Pantry.this, AddFood.class);
//        intent.putExtra("id", 0 + "");
//        startActivity(intent);
//    }
}