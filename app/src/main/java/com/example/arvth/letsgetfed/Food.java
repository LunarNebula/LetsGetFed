package com.example.arvth.letsgetfed;

import java.util.Date;

/**
 * This class models the behavior of a Food object.
 */

/**
 * Constructs a Food object with no parameters
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
    private long MSPD = 86400000;

    // Constructor
    /**
     * Constructs a Food object with a given name, type, and min and max expiration times for each temperature-based storage option.
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

    /**
     * Constructs a Food object with a given name, date purchased, and temperature-based storage location
     * @param foodName the given name of the Food object
     * @param datePurchased the given date that the Food object was purchased
     * @param shelfID the location where the Food object is stored
     */
    public Food(String foodName, Date datePurchased, int shelfID){
        name = foodName;
        purchased = datePurchased;
        location = shelfID;
    }

    /**
     * This method allows a user to set the name of a Food object
     * @param name the name the user is setting as the Food object's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method allows a user to set the type of a Food object
     * @param type the type the user is setting as the Food object's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method allows a user to set the date purchased of a Food object
     * @param purchased the date the user is setting as the Food object's date purchased
     */
    public void setPurchased(Date purchased) {
        this.purchased = purchased;
    }

    /**
     * This method allows a user to set the minimum number of days it takes the Food object to expire when stored on the counter
     * @param counterMinExp the minimum number of days to expire when stored on the counter
     */
    public void setCounterMinExp(int counterMinExp) {
        this.counterMinExp = counterMinExp;
    }

    /**
     * This method allows a user to set the maximum number of days it takes the Food object to expire when stored on the counter
     * @param counterMaxExp the maximum number of days to expire when stored on the counter
     */
    public void setCounterMaxExp(int counterMaxExp) {
        this.counterMaxExp = counterMaxExp;
    }

    /**
     * This method allows a user to set the minimum number of days it takes the Food object to expire when stored in the fridge
     * @param fridgeMinExp the minimum number of days to expire when stored in the fridge
     */
    public void setFridgeMinExp(int fridgeMinExp) {
        this.fridgeMinExp = fridgeMinExp;
    }

    /**
     * This method allows a user to set the maximum number of days it takes the Food object to expire when stored in the fridge
     * @param fridgeMaxExp the maximum number of days to expire when stored in the fridge
     */
    public void setFridgeMaxExp(int fridgeMaxExp) {
        this.fridgeMaxExp = fridgeMaxExp;
    }

    /**
     * This method allows a user to set the minimum number of days it takes the Food object to expire when stored in the freezer
     * @param freezerMinExp the minimum number of days to expire when stored in the freezer
     */
    public void setFreezerMinExp(int freezerMinExp) {
        this.freezerMinExp = freezerMinExp;
    }

    /**
     * This method allows a user to set the maximum number of days it takes the Food object to expire when stored in the freezer
     * @param freezerMaxExp the maximum number of days to expire when stored in the freezer
     */
    public void setFreezerMaxExp(int freezerMaxExp) {
        this.freezerMaxExp = freezerMaxExp;
    }

    /**
     * This method allows a user to set the minimum number of days it takes for the Food object to expire
     * @param minExpDate the minimum number of days it takes for the Food object to expire
     */
    public void setMinExpDate(int minExpDate) {
        this.minExpDate = minExpDate;
    }

    /**
     * This method allows a user to set the maximum number of days it takes for the Food object to expire
     * @param maxExpDate the maximum number of days it takes for the Food object to expire
     */
    public void setMaxExpDate(int maxExpDate) {
        this.maxExpDate = maxExpDate;
    }

    /**
     * Constructs a Food object with no parameters
     */
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
    /**
     * This method returns the name of the Food object
     * @return the name of the Food object
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the date the Food object was purchased
     * @return the date the Food object was purchased
     */
    public Date getPurchaseDate() {
        return this.purchased;
    }

    /**
     * This method returns the minimum number of days it takes for the Food object to expire when stored on the counter
     * @return the minimum number of days it takes for the Food object to expire when stored on the counter
     */
    public int getCounterMinExp() {
        return counterMinExp;
    }

    /**
     * This method returns the maximum number of days it takes for the Food object to expire when stored on the counter
     * @return the maximum number of days it takes for the Food object to expire when stored on the counter
     */
    public int getCounterMaxExp() {
        return counterMaxExp;
    }

    /**
     * This method returns the minimum number of days it takes for the Food object to expire when stored in the fridge
     * @return the minimum number of days it takes for the Food object to expire when stored in the fridge
     */
    public int getFridgeMinExp() {
        return fridgeMinExp;
    }

    /**
     * This method returns the maximum number of days it takes for the Food object to expire when stored in the fridge
     * @return the maximum number of days it takes for the Food object to expire when stored in the fridge
     */
    public int getFridgeMaxExp() {
        return fridgeMaxExp;
    }

    /**
     * This method returns the minimum number of days it takes for the Food object to expire when stored in the freezer
     * @return the minimum number of days it takes for the Food object to expire when stored in the freezer
     */
    public int getFreezerMinExp() {
        return freezerMinExp;
    }

    /**
     * This method returns the maximum number of days it takes for the Food object to expire when stored in the freezer
     * @return the maximum number of days it takes for the Food object to expire when stored in the freezer
     */
    public int getFreezerMaxExp() {
        return freezerMaxExp;
    }

    /**
     * This method returns the type of the Food object
     * @return the type of food
     */
    public String getType() {
        return type;
    }

    /**
     * This method returns the date the Food object was purchased
     * @return the date the Food object was purchased
     */
    public Date getPurchased() {
        return purchased;
    }

    /**
     * This method returns the minimum number of days it takes for the Food object to expire
     * @return the minimum number of days it takes for the Food object to expire
     */
    public int getMinExpDate() {
        return minExpDate;
    }

    /**
     * This method returns the maximum number of days it takes for the Food object to expire
     * @return the maximum number of days it takes for the Food object to expire
     */
    public int getMaxExpDate() {
        return maxExpDate;
    }

    /**
     * This method returns the shelf/location in which the Food object is stored
     * @return the shelf/location in which the Food object is stored
     */
    public int getLocation() {
        return this.location;
    }

    /**
     * This method allows a user to set the date that the Food object was purchased
     * @param date the date the Food object was purchased
     */
    public void setPurchaseDate(Date date) {
        this.purchased = date;
    }

    /**
     * This method checks to see if the Food object is expiring
     * @return true if the Food object is expiring, false if the food object is not expiring
     */
    public boolean isExpiring() {
        return minExpirationTime() < Alert.ALERT_TIME_BUFFER;
    }

    /**
     * This method returns the minimum time it takes for a Food object to expire based on its storage location
     * @return the minimum time it takes for a Food object to expire
     */
    public long minExpirationTime() {
        switch(location) {
            case 0:
                return counterMinExp;
            case 1:
                return fridgeMinExp;
            case 2:
                return freezerMinExp;
        }
        return 0;
    }

    /**
     * This method returns the maximum time it takes for a Food object to expire based on its storage location
     * @return the maximum time it takes for a Food object to expire
     */
    public long maxExpirationTime() {
        switch(location) {
            case 0:
                return counterMaxExp;
            case 1:
                return fridgeMaxExp;
            case 2:
                return freezerMaxExp;
        }
        return 0;
    }

    /**
     * This method allows a user to set the location (shelf) in which the Food object is stored
     * @param location the shelf in which the Food object is stored
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * This method returns the name, type, and maximum expiration dates for each storage location
     * @return the name, type, and maximum expiration dates for each storage location
     */
    @Override
    public String toString()  {
        return getName() + getType() + getCounterMaxExp() + getFreezerMaxExp() + getFridgeMaxExp();
    }

    /**
     * This method returns the name, purchase date, and location of the Food object
     * @return the name, purchase date, and location of the Food object
     */
    public String toString2() {
        return getName() + getPurchaseDate().toString() + Integer.toString(getLocation());
    }
}