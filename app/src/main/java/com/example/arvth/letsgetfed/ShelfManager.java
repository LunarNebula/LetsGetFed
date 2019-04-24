package com.example.arvth.letsgetfed;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ShelfManager extends AppCompatActivity {
    int shelfID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));
        //load(shelfID);
        load(shelfID);
//        ShelfManager.storeValues(this);
//        ShelfManager.pullDirectory(this);
    }

    public void pantry(View view) {
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }

    public void load(int ID) {
//        TableLayout layout = findViewById(R.id.food_list);
//        int count = layout.getChildCount();
//        for(int i = 0; i < count; i++) {
//            if(layout.getChildAt(i) instanceof TableRow) {
//                ((ViewGroup) layout.getChildAt(i)).removeAllViews();
//            }
//        }
//        Shelf shelf = Pantry.shelves.get(ID);
//        for(int i = 0; i < shelf.getPopulation(); i++) {
//            TableRow row = new TableRow(this);
//            row.addView(getView(shelf, i));
//            layout.addView(row);
//        }
    }
    public TextView getView(Shelf shelf, int ID) {
        TextView view = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setText(shelf.getLabel());
        view.setId(ID);
        return view;
    }
    public void addfood(View view) {
        Intent intent = new Intent(ShelfManager.this, AddFood.class);
        intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }
    //store methods below
    public static void storeValues(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putInt("shelf_population", Pantry.shelves.size());
        for(int i = 0; i < Pantry.shelves.size(); i++) {
            storeShelf(editor, i);
        }
    }
    public static void storeShelf(SharedPreferences.Editor editor, int ID) {
        editor.putInt("shelf_" + ID + "_type", Pantry.shelves.get(ID).getType());
        editor.putInt("shelf_" + ID + "_size", Pantry.shelves.get(ID).getPopulation());
        editor.putString("shelf_" + ID + "_label", Pantry.shelves.get(ID).getLabel());
        for(int i = 0; i < Pantry.shelves.get(ID).getPopulation(); i++) {
            storeFood(editor, ID, i);
        }
    }
    public static void storeFood(SharedPreferences.Editor editor, int shelfID, int foodID) {
        editor.putString("shelf_" + shelfID + "_" + foodID + "_label",
                Pantry.shelves.get(shelfID).getFood(foodID).getName());
        editor.putString("shelf_" + shelfID + "_" + foodID + "_type",
                Pantry.shelves.get(shelfID).getFood(foodID).getType());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExpCounter",
                Pantry.shelves.get(shelfID).getFood(foodID).getCounterMinExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExpCounter",
                Pantry.shelves.get(shelfID).getFood(foodID).getCounterMaxExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExpFridge",
                Pantry.shelves.get(shelfID).getFood(foodID).getFridgeMinExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExpFridge",
                Pantry.shelves.get(shelfID).getFood(foodID).getFridgeMaxExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExpFreezer",
                Pantry.shelves.get(shelfID).getFood(foodID).getFreezerMinExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExpFreezer",
                Pantry.shelves.get(shelfID).getFood(foodID).getFreezerMaxExp());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_location",
                Pantry.shelves.get(shelfID).getFood(foodID).getLocation());

    }
    //get methods below
    public static void pullDirectory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        int size = preferences.getInt("shelf_population", 0);
        Pantry.shelves = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Pantry.shelves.add(pullShelf(preferences, i));
        }
    }
    public static Shelf pullShelf(SharedPreferences preferences, int ID) {
        Shelf shelf = new Shelf(preferences.getString("shelf_" + ID + "_label", ""),
                preferences.getInt("shelf_" + ID + "_type", 0));
        int size = preferences.getInt("shelf_" + ID + "_size", 0);

        for(int i = 0; i < size; i++) {
            shelf.addFood(pullFood(preferences, ID, i));
        }
        return shelf;
    }
    public static Food pullFood(SharedPreferences preferences, int shelfID, int foodID) {
        Food food = new Food(
                preferences.getString("shelf_" + shelfID + "_" + foodID + "_label", ""),
                preferences.getString("shelf_" + shelfID + "_" + foodID + "_type", ""),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpCounter", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpCounter", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFridge", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFridge", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFreezer", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFreezer", 0)
        );
        food.setLocation( preferences.getInt("shelf_" + shelfID + "_" + foodID + "_location", 0));
        return food;
    }
}