package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Freezer extends AppCompatActivity {
    int shelfID;
    public static ArrayList<Food> listOfFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freezer_shelf);
        shelfID = 2;
        RecyclerViewShelf();
        //load(shelfID);
        Preferences.storeValues(this, Preferences.getPreferencesFood());
        Preferences.pullDirectory(this);
    }

    public void RecyclerViewShelf() {
        RecyclerView recyclerView3 = findViewById(R.id.freezer_recyclerview);
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> shelfFood = aController.getShelfPopulation(2);
        FreezerAdapter adapter3 = new FreezerAdapter(shelfFood, this);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
    }

    public void pantry(View view) {
        startActivity(new Intent(Freezer.this, Pantry.class));
    }

    public void toAddFoodFz(View view) {
        Intent intent = new Intent(Freezer.this, AddFood.class);
        intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }


    public void toPantryClickFr(View view){
        startActivity(new Intent(Freezer.this, Pantry.class));
    }

    public void toAlertsClickFr(View view){
        startActivity(new Intent(Freezer.this, Alert.class));
    }

    public void toSettingsClickFr(View view) {
        startActivity(new Intent(Freezer.this, Settings.class));
    }

    public void toEditFr (View view){
        startActivity(new Intent(Freezer.this, Edit.class));
    }

    public void toAddFoodFr(View view) {
        Intent intent = new Intent(Freezer.this, AddFood.class);
        intent.putExtra("id", 2);
        startActivity(intent);
    }
}
