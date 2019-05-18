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
 * This class models the behavior of a freezer (type of shelf).
 */
public class Freezer extends AppCompatActivity {
    int shelfID;
    private String TAG = "Freezer Class";
    public static ArrayList<Food> listOfFoods = new ArrayList<>();
    private String clickedFoodName;
    private int clickedPositionInShelf;


    /**
     * This method builds freezer_shelf with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freezer_shelf);
        shelfID = 2;
        RecyclerViewShelf();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("From FreezerAdapter"));

        final Controller aController = (Controller) getApplicationContext();
    }

    /**
     * This method gets local broadcasts from the freezer adapter
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
     * This method implements a RecyclerView in the freezer_shelf activity
     */
    public void RecyclerViewShelf() {
        RecyclerView recyclerView3 = findViewById(R.id.freezer_recyclerview);
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> shelfFood = aController.getShelfPopulation(2);
        FreezerAdapter adapter3 = new FreezerAdapter(shelfFood, this);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Freezer" screen/class
     * @param view the button being clicked
     */
    public void pantry(View view) {
        startActivity(new Intent(Freezer.this, Pantry.class));
    }


    public void toAddFoodFz(View view) {
        Intent intent = new Intent(Freezer.this, AddFood.class);
        intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Freezer" screen/class
     * @param view the button being clicked
     */
    public void toPantryClickFr(View view) {
        startActivity(new Intent(Freezer.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Freezer" screen/class
     * @param view the button being clicked
     */
    public void toAlertsClickFr(View view) {
        startActivity(new Intent(Freezer.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Freezer" screen/class
     * @param view the button being clicked
     */
    public void toSettingsClickFr(View view) {
        startActivity(new Intent(Freezer.this, Settings.class));
    }

    /**
     * This method returns the user to the "AddFood" screen/class from the "Freezer" screen/class
     * @param view the button being clicked
     */
    public void toAddFoodFr(View view) {
        Intent intent = new Intent(Freezer.this, AddFood.class);
        intent.putExtra("id", 2);
        startActivity(intent);
    }

    public void deleteFr(View view) {
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

        startActivity(new Intent(Freezer.this, Freezer.class));
    }
}
