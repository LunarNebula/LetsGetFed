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
    private int location;

    // Constructor

    /**
     * Constructs a Food object with a given name,
     */
    public Food(String foodName, String foodType, int counterMin, int counterMax, int fridgeMin,
                int fridgeMax, int freezerMin, int freezerMax)
    {
        this.name = foodName;
        this.type = foodType;
        this.counterMinExp = counterMin;
        this.counterMaxExp = counterMax;
        this.fridgeMinExp = fridgeMin;
        this.fridgeMaxExp = fridgeMax;
        this.freezerMinExp = freezerMin;
        this.freezerMaxExp = freezerMax;
    }
//
    public Food(String foodName, Date datePurchased, int shelfID){
        name = foodName;
        purchased = datePurchased;
        location = shelfID;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPurchased(Date purchased) {
        this.purchased = purchased;
    }

    public void setCounterMinExp(int counterMinExp) {
        this.counterMinExp = counterMinExp;
    }

    public void setCounterMaxExp(int counterMaxExp) {
        this.counterMaxExp = counterMaxExp;
    }

    public void setFridgeMinExp(int fridgeMinExp) {
        this.fridgeMinExp = fridgeMinExp;
    }

    public void setFridgeMaxExp(int fridgeMaxExp) {
        this.fridgeMaxExp = fridgeMaxExp;
    }

    public void setFreezerMinExp(int freezerMinExp) {
        this.freezerMinExp = freezerMinExp;
    }

    public void setFreezerMaxExp(int freezerMaxExp) {
        this.freezerMaxExp = freezerMaxExp;
    }

    public void setMinExpDate(int minExpDate) {
        this.minExpDate = minExpDate;
    }

    public void setMaxExpDate(int maxExpDate) {
        this.maxExpDate = maxExpDate;
    }

    public Food() {
        name = "";
        type = "";
        counterMinExp = 0;
        counterMaxExp = 0;
        fridgeMinExp = 0;
        fridgeMaxExp = 0;
        freezerMinExp = 0;
        freezerMaxExp = 0;
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

    public int getLocation() {
        return this.location;
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
    public void setPurchaseDate(Date date) {
        this.purchased = date;
    }
    public boolean isExpiring() {
        return expirationTime() < Alert.ALERT_TIME_BUFFER;
    }
    public long expirationTime() {
        switch(location) {
            case 0:
                return purchased.getTime() + counterMinExp - (new Date()).getTime();
            case 1:
                return purchased.getTime() + fridgeMinExp - (new Date()).getTime();
            case 2:
                return purchased.getTime() + freezerMinExp - (new Date()).getTime();
        }
        return 0;
    }
    public void setLocation(int location) {
        this.location = location;
    }
    @Override
    public String toString()  {
        return getName() + getType() + getCounterMaxExp() + getFreezerMaxExp() + getFridgeMaxExp();
    }
}