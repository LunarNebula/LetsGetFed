package com.example.arvth.letsgetfed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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
    private String TAG = "Counter Class";
    public static ArrayList<String> listOfFoods = new ArrayList<>();
    //private TextView itemName;
    private String clickedFoodName;
    private int clickedPositionInShelf;
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


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("From FreezerAdapter"));

        final Controller aController = (Controller) getApplicationContext();
    }

    /**
     * This method gets local broadcasts from the counter adapter
     */
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            clickedFoodName = intent.getStringExtra("food name");
            clickedPositionInShelf = intent.getIntExtra("position in shelf", -1);
            Log.d(TAG, clickedFoodName + ", " + Integer.toString(clickedPositionInShelf));
        }
    };

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
        Log.d(TAG, "Clicked!");
        final Controller aController = (Controller) getApplicationContext();
        aController.printList(aController.getUserFoodList());

        for (int i = aController.getUserFoodList().size() - 1; i >= 0; i--) {
            if (aController.getUserFoodList().get(i).getName().equals(clickedFoodName) &&
                    aController.getUserFoodList().get(i).getLocation() == shelfID) {
                Log.d(TAG, "Passed IF");
                aController.deleteFromUserList(i);

                aController.printList(aController.getUserFoodList());
                break;
            }
        }

        startActivity(new Intent(Counter.this, Counter.class));
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
