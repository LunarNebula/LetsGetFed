package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * This class models the behavior of a shelf (storage location)
 */
public class ShelfManager extends AppCompatActivity {
    int shelfID;
    public static ArrayList<Food> listOfFoods = new ArrayList<>();

    /**
     * This method builds activity_shelf with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));
        RecyclerViewShelf();
        //load(shelfID);
        Preferences.storeValues(this, Preferences.getPreferencesFood());
        Preferences.pullDirectory(this);
    }

    /**
     * This method implements a RecyclerView
     */
    public void RecyclerViewShelf() {
        RecyclerView recyclerView2 = findViewById(R.id.shelf_recyclerView);
        RecyclerShelfAdapter adapter2 = new RecyclerShelfAdapter(
                Pantry.shelves.get(shelfID).getFoodPopulation(), this);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "ShelfManager" screen/class.
     * @param view the button being clicked
     */
    public void pantry(View view) {
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }

    /**
     * This method returns the user to the "AddFood" screen/class from the "ShelfManager" screen/class.
     * @param view the button being clicked
     */
    public void addfood(View view) {
        Intent intent = new Intent(ShelfManager.this, AddFood.class);
        //intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "ShelfManager" screen/class.
     * @param view the button being clicked
     */
    public void toPantryClickSM(View view){
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "ShelfManager" screen/class.
     * @param view the button being clicked
     */
    public void toAlertsClickSM(View view){
        startActivity(new Intent(ShelfManager.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "ShelfManager" screen/class.
     * @param view the button being clicked
     */
    public void toSettingsClickSM(View view){
        startActivity(new Intent(ShelfManager.this, Settings.class));
    }
}