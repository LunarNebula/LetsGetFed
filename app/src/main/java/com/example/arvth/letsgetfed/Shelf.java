package com.example.arvth.letsgetfed;
import java.util.ArrayList;
import java.util.Arrays;

public class Shelf {
    private ArrayList<Food> food;
    private String label;
    private int type;
    //get file variable
    public static final int FRIDGE = 0, FREEZER = 1, CUPBOARD = 2;
    public static final String[] labels = {"fridge", "freezer", "cupboard"};
    public Shelf(String label) {
        this.food = new ArrayList<>();
        this.label = label;
    }
    public Food getFood(int index) {
        return this.food.get(index);
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
        return this.food.size();
    }
    public void addFood(Food food) {
        this.food.add(food);
    }

}