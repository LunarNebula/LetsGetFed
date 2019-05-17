package com.example.arvth.letsgetfed;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * This class fuctions as a backup data system for the controller
 */
public class Preferences {
    private static ArrayList<Food> allFood = new ArrayList<>();

    /**
     * This method returns an ArrayList of Food objects (with the same foods as the UserFoodList in the controller)
     * @return an ArrayList of Food objects (with the same foods as the UserFoodList in the controller)
     */
    public static ArrayList<Food> getPreferencesFood() {
        return allFood;
    }

    /**
     * This method adds a given food to an ArrayList of foods
     * @param food the given food being added to an ArrayList of foods
     */
    public static void addPreferencesFood(Food food) {
        allFood.add(food);
    }

    /**
     * This method stores the information from Settings and a given ArrayList of Food objects with a given context
     * @param context the given context
     * @param allFood the given ArrayList of Food objects being stored
     */
    public static void storeValues(Context context, ArrayList<Food> allFood) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putInt("ALERT_TIME_BUFFER", Alert.ALERT_TIME_BUFFER);
        editor.putInt("userPopulation", allFood.size());
        for(int i = 0; i < allFood.size(); i++) {
            editor.putString(i + "_name", allFood.get(i).getName());
            editor.putString(i + "_date", allFood.get(i).getPurchased().toString());
            editor.putInt(i + "_shelf", allFood.get(i).getLocation());
        }
        editor.apply();
    }

    /**
     * This method creates Food objects using data pulled from a SharedPreferences file and populates an ArrayList of Food objects
     * @param context the given context
     */
    public static void pullDirectory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        Alert.ALERT_TIME_BUFFER = preferences.getInt("ALERT_TIME_BUFFER", 3);
        int size = preferences.getInt("userPopulation", 0);
        allFood = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            String name = preferences.getString(i + "_name", "");
            Date purchaseDate = new Date(preferences.getString(i + "_date", (new Date()).toString()));
            int shelfID = preferences.getInt(i + "_shelf", 0);
            allFood.add(new Food(name, purchaseDate, shelfID));
        }
    }
}