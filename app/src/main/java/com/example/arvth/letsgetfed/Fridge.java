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
        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));
        RecyclerViewShelf();
        //load(shelfID);
        Preferences.storeValues(this);
        Preferences.pullDirectory(this);
    }

    public void RecyclerViewShelf() {
        RecyclerView recyclerView2 = findViewById(R.id.fridge_recyclerview);
        FridgeAdapter adapter2 = new FridgeAdapter(
                Pantry.shelves.get(shelfID).getFoodPopulation(), this);
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

    public void toPantryClickFd(View view){
        startActivity(new Intent(Fridge.this, Pantry.class));
    }

    public void toAlertsClickFd(View view){
        startActivity(new Intent(Fridge.this, Alert.class));
    }

    public void toSettingsClickFd(View view){
        startActivity(new Intent(Fridge.this, Settings.class));
    }
}
