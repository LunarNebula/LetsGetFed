package com.example.arvth.letsgetfed;

import java.util.Date;

/**
 * This class models the behavior of a Food object.
 */

public class Food
{
    // Data
    private String name;
    private Date purchaseDate;
    private int minExpirationTime;
    private int maxExpirationTime;

    // Constructor

    /**
     * Constructs a Food object with a given name,
     */
    public Food(String foodName, Date purchased, int minExpiration, int maxExpiration)
    {
        name = foodName;
        purchaseDate = purchased;
        minExpirationTime = minExpiration;
        maxExpirationTime = maxExpiration;
    }

    // Methods
    // parse text to find food (match the name)
    // we will do this after we decide what's going on with the database of foods
    public String getName() {
        return this.name;
    }
    public Date getPurchaseDate() {
        return this.purchaseDate;
    }
    public int getMinExpirationTime() {
        return this.minExpirationTime;
    }
    public int getMaxExpirationTime() {
        return this.maxExpirationTime;
    }
    public Date getMinExpirationDate() {
        Date date = this.purchaseDate;
        date.setDate(date.getDate() + this.minExpirationTime);
        return date;
    }
    public Date getMaxExpirationDate() {
        Date date = this.purchaseDate;
        date.setDate(date.getDate() + this.maxExpirationTime);
        return date;
    }
    public static void readFood() {
        //
    }
}