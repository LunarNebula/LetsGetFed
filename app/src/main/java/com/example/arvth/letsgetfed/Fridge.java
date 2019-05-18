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
 * This class models the behavior of a fridge (type of shelf).
 */
public class Fridge extends AppCompatActivity {
    int shelfID;
    private String TAG = "Fridge Class";
    public static ArrayList<Food> listOfFoods = new ArrayList<>();
    private String clickedFoodName;
    private int clickedPositionInShelf;

    /**
     * This method builds fridge_shelf with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_shelf);
        shelfID = 1;
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
     * This method implements a RecyclerView in the fridge_shelf activity
     */
    public void RecyclerViewShelf() {
        RecyclerView recyclerView2 = findViewById(R.id.fridge_recyclerview);
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> shelfFood = aController.getShelfPopulation(1);
        FridgeAdapter adapter2 = new FridgeAdapter(
                shelfFood, this);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Fridge" screen/class
     * @param view the button being clicked
     */
    public void pantry(View view) {
        startActivity(new Intent(Fridge.this, Pantry.class));
    }

    /**
     * This method returns the user to the "AddFood" screen/class from the "Fridge" screen/class
     * @param view the button being clicked
     */
    public void toAddFoodFd(View view) {
        Intent intent = new Intent(Fridge.this, AddFood.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Fridge" screen/class
     * @param view the button being clicked
     */
    public void toPantryClickFd(View view){
        startActivity(new Intent(Fridge.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Fridge" screen/class
     * @param view the button being clicked
     */
    public void toAlertsClickFd(View view){
        startActivity(new Intent(Fridge.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Fridge" screen/class
     * @param view the button being clicked
     */
    public void toSettingsClickFd(View view){
        startActivity(new Intent(Fridge.this, Settings.class));
    }

    /**
     * This method allows a user to delete a food item
     * @param view the button being clicked
     */
    public void deleteFd(View view){
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

        startActivity(new Intent(Fridge.this, Fridge.class));
    }
}
