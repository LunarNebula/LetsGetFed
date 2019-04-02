package com.example.arvth.letsgetfed;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.Date;
import java.util.Arrays;

public class Shelf {
    private Food[] food;
    private String label;
    private int type;
    //get file variable
    public static final int FRIDGE = 0, FREEZER = 1, CUPBOARD = 2;
    public static final String[] labels = {"fridge", "freezer", "cupboard"};
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
        return this.food.length;
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