package com.example.arvth.letsgetfed;
import java.util.Arrays;

public class Shelf {
    private Food[] food;
    private String label;
    private int type;
    public static final int DEFAULT = -1, FRIDGE = 0, FREEZER = 1, CUPBOARD = 2, COUNTER = 3;
    private final String[] labels = {"default", "fridge", "freezer", "cupboard", "counter"};
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
    }
    public void addFood(Food food) {
        Food[] proxy_variable = new Food[this.food.length + 1];
        for(int i = 0; i < this.food.length; i++) {
            proxy_variable[i] = this.getFood(i);
        }
        proxy_variable[this.food.length] = food;
        this.food = proxy_variable;
    }
}