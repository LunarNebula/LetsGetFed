package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

/**
 * This class models the behavior of a pantry (collection of shelves).
 */
public class Pantry extends AppCompatActivity {
    private static final String TAG = "Pantry";

    // variables
    public static ArrayList<Shelf> shelves = new ArrayList<>();
    public static ArrayList<Food> expiring = new ArrayList<>();

    /**
     * This method builds activity_pantry with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        Log.d(TAG, "onCreate: started");
        expiring = new ArrayList<>();
        Drawable[] expirationList = new Drawable[3];

        if(shelves.size() != 3) {
            shelves.add(new Shelf("counter", 0));
            shelves.add(new Shelf("fridge", 1));
            shelves.add(new Shelf("freezer", 2));
        }
        Preferences.pullDirectory(this);
        if(Preferences.getPreferencesFood().size() > 0) {
            Preferences.storeValues(this, Preferences.getPreferencesFood());
        }
        final Controller aController = (Controller) getApplicationContext();
    }

    /**
     * This method returns the user to the "Counter" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toCounterClick(View view){
        startActivity(new Intent(Pantry.this, Counter.class));
    }

    /**
     * This method returns the user to the "Fridge" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toFridgeClick(View view){
        startActivity(new Intent(Pantry.this, Fridge.class));
    }

    /**
     * This method returns the user to the "Freezer" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toFreezerClick(View view){startActivity(new Intent(Pantry.this, Freezer.class));}

    /**
     * This method returns the user to the "Pantry" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toPantryClick(View view){
        startActivity(new Intent(Pantry.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toAlertsClick(View view){
        startActivity(new Intent(Pantry.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Pantry" screen/class
     * @param view the button being clicked
     */
    public void toSettingsClick(View view){startActivity(new Intent(Pantry.this, Settings.class));}

    /**
     * This method allows the Controller onStop() method to run after the app closes
     */
    @Override
    public void onStop() {
        final Controller aController = (Controller) getApplicationContext();
        aController.onStop();
        super.onStop();
    }
}