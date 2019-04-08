package com.example.arvth.letsgetfed;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * This class models the behavior of a Food object.
 */

public class Food
{
    // Data
    private String name;
    private String type;
    private Date purchased;
    private int counterMinExp;
    private int counterMaxExp;
    private int fridgeMinExp;
    private int fridgeMaxExp;
    private int freezerMinExp;
    private int freezerMaxExp;
    private int minExpDate;
    private int maxExpDate;

    // Constructor

    /**
     * Constructs a Food object with a given name,
     */
    public Food(String foodName, String foodType, int counterMin, int counterMax, int fridgeMin,
                int fridgeMax, int freezerMin, int freezerMax)
    {
        name = foodName;
        type = foodType;
        counterMinExp = counterMin;
        counterMaxExp = counterMax;
        fridgeMinExp = fridgeMin;
        fridgeMaxExp = fridgeMax;
        freezerMinExp = freezerMin;
        freezerMaxExp = freezerMax;
    }

    public Food(String foodName, Date datePurchased, int minExp, int maxExp) {
        name = foodName;
        purchased = datePurchased;
        minExpDate = minExp;
        maxExpDate= maxExp;

    }

    // Methods
    // parse text to find food (match the name)
    // we will do this after we decide what's going on with the database of foods
    public String getName() {
        return this.name;
    }
    public Date getPurchaseDate() {
        return this.purchased;
    }

    public int getCounterMinExp() {
        return counterMinExp;
    }

    public int getCounterMaxExp() {
        return counterMaxExp;
    }

    public int getFridgeMinExp() {
        return fridgeMinExp;
    }

    public int getFridgeMaxExp() {
        return fridgeMaxExp;
    }

    public int getFreezerMinExp() {
        return freezerMinExp;
    }

    public int getFreezerMaxExp() {
        return freezerMaxExp;
    }

    public String getType() {
        return type;
    }

    public Date getPurchased() {
        return purchased;
    }

    public int getMinExpDate() {
        return minExpDate;
    }

    public int getMaxExpDate() {
        return maxExpDate;
    }

    // The following code needs to be fixed based on user input. Essentially, we want to get the information
    // about where they store the information, then get the specific expiration # and add that to the current
    // date.

//    public Date getMinExpirationDate() {
//        Date date = this.purchaseDate;
//        date.setDate(date.getDate() + this.minExpirationTime);
//        return date;
//    }
//    public Date getMaxExpirationDate() {
//        Date date = this.purchaseDate;
//        date.setDate(date.getDate() + this.maxExpirationTime);
//        return date;
//    }
//    public static void readFood() {
//        //
//    }
}