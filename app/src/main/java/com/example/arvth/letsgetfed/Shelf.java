package com.example.arvth.letsgetfed;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class models the behavior of a shelf.
 */
public class Shelf {
    private Food[] food;
    private String label;
    private int type;
    //get file variable
    public static final int COUNTER = 0, FRIDGE = 1, FREEZER = 2;
    public static final String[] labels = {"counter", "fridge", "freezer"};
    public Shelf(String label, int type) {
        this.food = new Food[0];
        this.label = label;
        this.type = type;
    }

    /**
     * Constructs a Shelf object with a given name
     * @param string the given name of the shelf
     */
    public Shelf(String string) {
        this.food = new Food[0];
        this.label = "";
        this.type = -1;
    }

    /**
     * This method gets the Food object at a given index in an Array
     * @param index the location of the Food object in the Array
     * @return the Food object at the given index
     */
    public Food getFood(int index) {
        return this.food[index];
    }

    /**
     * This method returns the String name/label of the shelf
     * @return the String name/label of the shelf
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * This method allows a user to set the type of the shelf with a given type input
     * @param type the given input that the shelf type is being set to
     */
    public void setType(String type) {
        this.type = Arrays.asList(this.labels).indexOf(type);
        /*
         * Add file getter variable - switch cases will set variable to different files
         * */
        switch(this.type) {

            case COUNTER:
                break;
            case FRIDGE:
                break;
            case FREEZER:
                break;
        }
    }

    /**
     * This method returns the type of the shelf
     * @return the type of the shelf
     */
    public int getType() {
        return this.type;
    }

    /**
     * This method gets the total number of Food objects/items in the Array
     * @return the total number of Food objects/items in the Array
     */
    public int getPopulation() {
        return this.food.length;
    }

    /**
     *
     * @param food the given Food object that is being added
     */
    public void addFood(Food food) {
        Food[] proxy_variable = new Food[this.food.length + 1];
        for(int i = 0; i < this.food.length; i++) {
            proxy_variable[i] = this.getFood(i);
        }
        proxy_variable[this.food.length] = food;
        this.food = proxy_variable;
    }

    /**
     * This method returns the total number of Food objects/items in the ArrayList
     * @return the total number of Food objects/items in the ArrayList
     */
    public ArrayList<Food> getFoodPopulation() {
        return new ArrayList(Arrays.asList(this.food));
    }


}