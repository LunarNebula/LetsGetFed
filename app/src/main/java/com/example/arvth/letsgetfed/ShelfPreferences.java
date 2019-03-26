package com.example.arvth.letsgetfed;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.Date;

public class ShelfPreferences {
    //store methods below
    public static void storeValues(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

    }
    public static void storeShelf(SharedPreferences.Editor editor, int ID) {


    }
    public static void storeFood(SharedPreferences.Editor editor, int shelfID, int foodID) {
        editor.putString("shelf_" + shelfID + "_" + foodID + "_label",
                Pantry.shelves[shelfID].getFood(foodID).getName());
        editor.putString("shelf_" + shelfID + "_" + foodID + "_date",
                Pantry.shelves[shelfID].getFood(foodID).getPurchaseDate().toString());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExp",
                Pantry.shelves[shelfID].getFood(foodID).getMinExpirationTime());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExp",
                Pantry.shelves[shelfID].getFood(foodID).getMaxExpirationTime());
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
        return new Food(
                preferences.getString("shelf_" + shelfID + "_" + foodID + "_label", ""),
                Date.valueOf(preferences.getString("shelf_" + shelfID + "_" + foodID + "_date", "")),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExp", 0),
                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExp", 0)
                );
    }
}