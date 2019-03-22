package com.example.arvth.letsgetfed;

import java.util.Date;

/**
 * This class models the behavior of a Food object.
 */

public class Food
{
    // Data
    String name;
    Date purchaseDate;
    int minExpirationTime;
    int maxExpirationTime;

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
}