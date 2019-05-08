package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Fridge extends AppCompatActivity {
    int shelfID;
    public static ArrayList<Food> listOfFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_shelf);
        shelfID = 1;
        RecyclerViewShelf();
        //load(shelfID);
        Preferences.storeValues(this);
        Preferences.pullDirectory(this);

    }

    public void RecyclerViewShelf() {
        RecyclerView recyclerView2 = findViewById(R.id.fridge_recyclerview);
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> shelfFood = aController.getShelfPopulation(1);
        FridgeAdapter adapter2 = new FridgeAdapter(
                shelfFood, this);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }

    public void pantry(View view) {
        startActivity(new Intent(Fridge.this, Pantry.class));
    }

    public void addfood(View view) {
        Intent intent = new Intent(Fridge.this, AddFood.class);
        intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }

    public void addFridgeFood(View view){

    }

    public void toPantryClickFd(View view){
        startActivity(new Intent(Fridge.this, Pantry.class));
    }

    public void toAlertsClickFd(View view){
        startActivity(new Intent(Fridge.this, Alert.class));
    }

    public void toSettingsClickFd(View view){
        startActivity(new Intent(Fridge.this, Settings.class));
    }

    public void toEditFd (View view){
        startActivity(new Intent(Fridge.this, Edit.class));
    }

    public void addFood(View view) {
        Intent intent = new Intent(Fridge.this, AddFood.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }
}
