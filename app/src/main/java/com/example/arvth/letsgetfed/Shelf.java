package com.example.arvth.letsgetfed;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.Date;
import java.util.Arrays;

public class Shelf extends AppCompatActivity {
    //screen methods
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
    }
    //dynamic array code below

    //
    private Food[] food;
    private String label;
    private int type;
    //get file variable
    public static final int FRIDGE = 0, FREEZER = 1, CUPBOARD = 2;
    public static final String[] labels = {"fridge", "freezer", "cupboard"};
    public Shelf(String label) {
        this.food = new Food[0];
        this.label = label;
    }
    public Food getFood(int index) {
        return this.food[index];
    }
    public String getLabel() {
        return this.label;
    }
    public void setType(String type) {
        this.type = Arrays.asList(this.labels).indexOf(type);
        /*
         * Add file getter variable - switch cases will set variable to different files
         * */
        switch(this.type) {

            case FRIDGE:
                break;
            case FREEZER:
                break;
            case CUPBOARD:
                break;
        }
        /*
        * Pull data from the chosen file
        * */
    }
    public int getType() {
        return this.type;
    }
    public int getPopulation() {
        return this.food.length;
    }
    public void addFood(Food food) {
        Food[] proxy_variable = new Food[this.food.length + 1];
        for(int i = 0; i < this.food.length; i++) {
            proxy_variable[i] = this.getFood(i);
        }
        proxy_variable[this.food.length] = food;
        this.food = proxy_variable;
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