package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * This class models the behavior of a fridge (type of shelf).
 */
public class Fridge extends AppCompatActivity {
    int shelfID;
    public static ArrayList<Food> listOfFoods = new ArrayList<>();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_shelf);
        shelfID = 1;
        RecyclerViewShelf();
        //load(shelfID);
        Preferences.storeValues(this, Preferences.getPreferencesFood());
        Preferences.pullDirectory(this);
    }

    /**
     *
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
     *
     * @param view the button being clicked
     */
    public void deleteFd(View view){

    }
}
