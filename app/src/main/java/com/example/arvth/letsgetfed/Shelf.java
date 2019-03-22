package com.example.arvth.letsgetfed;

public class Shelf {
    private Food[] food;
    private String label;
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

}