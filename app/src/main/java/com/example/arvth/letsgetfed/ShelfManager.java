package com.example.arvth.letsgetfed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.sql.Date;
import java.util.Arrays;

public class ShelfManager extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        
    }

    public void pantry(View view) {
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }
    //store methods below
    public static void storeValues(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putInt("shelf_population", Pantry.shelves.length);
        for(int i = 0; i < Pantry.shelves.length; i++) {
            storeShelf(editor, i);
        }
    }
    public static void storeShelf(SharedPreferences.Editor editor, int ID) {
        editor.putString("shelf_" + ID + "_type", Arrays.asList(Shelf.labels).get(Pantry.shelves[ID].getType()));
        editor.putInt("shelf_" + ID + "_size", Pantry.shelves[ID].getPopulation());
        editor.putString("shelf_" + ID + "_label", Pantry.shelves[ID].getLabel());
        for(int i = 0; i < Pantry.shelves[ID].getPopulation(); i++) {
            storeFood(editor, ID, i);
        }
    }
    public static void storeFood(SharedPreferences.Editor editor, int shelfID, int foodID) {
        editor.putString("shelf_" + shelfID + "_" + foodID + "_label",
                Pantry.shelves[shelfID].getFood(foodID).getName());
        editor.putString("shelf_" + shelfID + "_" + foodID + "_date",
                Pantry.shelves[shelfID].getFood(foodID).getPurchaseDate().toString());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExp", 0);
                //Pantry.shelves[shelfID].getFood(foodID).getMinExpirationTime());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExp", 0);
                //Pantry.shelves[shelfID].getFood(foodID).getMaxExpirationTime());
    }
    //get methods below
    public static void pullDirectory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        int size = preferences.getInt("shelf_population", 0);
        Pantry.shelves = new Shelf[size];
        for(int i = 0; i < size; i++) {
            Pantry.shelves[i] = pullShelf(preferences, i);
        }
    }
    public static Shelf pullShelf(SharedPreferences preferences, int ID) {
        Shelf shelf = new Shelf(preferences.getString("shelf_" + ID + "_label", ""));
        int size = preferences.getInt("shelf_" + ID + "_size", 0);
        shelf.setType(preferences.getString("shelf_" + ID + "_type", ""));
        for(int i = 0; i < size; i++) {
            shelf.addFood(pullFood(preferences, ID, i));
        }
        return shelf;
    }
    public static Food pullFood(SharedPreferences preferences, int shelfID, int foodID) {
//        return new Food(
//                preferences.getString("shelf_" + shelfID + "_" + foodID + "_label", ""),
//                Date.valueOf(preferences.getString("shelf_" + shelfID + "_" + foodID + "_date", "")),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExp", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExp", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFridge", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFridge", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFreezer", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFreezer", 0)
//        );
        return new Food("", "", 0,0,0,0,0,0);
    } //this method was return errors - will consult with team for Food() constructor
}