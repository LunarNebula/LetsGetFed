package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * This class models the behavior of a counter (type of shelf).
 */
public class Counter extends AppCompatActivity {
    int shelfID = 0;
    public static ArrayList<String> listOfFoods = new ArrayList<>();

    /**
     * This method builds counter_shelf with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_shelf);
        shelfID = 0;
        RecyclerViewShelf();
    }

    /**
     * This method implements a RecyclerView in the counter_shelf activity
     */
    private void RecyclerViewShelf() {
        RecyclerView recyclerView1 = findViewById(R.id.counter_recyclerview);
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> shelfFood = aController.getShelfPopulation(0);
        CounterAdapter adapter1 = new CounterAdapter(shelfFood, this);
        Log.d("length_", shelfFood.size() + " is leng");
        for(int i = 0; i < shelfFood.size(); i++) {
            Log.d("food_", shelfFood.get(i).getName());
        }
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Counter" screen/class
     * @param view the button being clicked
     */
    public void toPantryClickC(View view){
        startActivity(new Intent(Counter.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Counter" screen/class
     * @param view the button being clicked
     */
    public void toAlertsClickC(View view){
        startActivity(new Intent(Counter.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Counter" screen/class
     * @param view the button being clicked
     */
    public void toSettingsClickC(View view){
        startActivity(new Intent(Counter.this, Settings.class));
    }

    /**
     * This method allows a user to delete a food item
     * @param view the button being clicked
     */
    public void deleteC(View view){

    }

    /**
     * This method returns the user to the "AddFood" screen/class from the "Counter" screen/class
     * @param view the button being clicked
     */
    public void toAddFoodC(View view) {
        Intent intent = new Intent(Counter.this, AddFood.class);
        intent.putExtra("id", 0);
        startActivity(intent);
    }
}
