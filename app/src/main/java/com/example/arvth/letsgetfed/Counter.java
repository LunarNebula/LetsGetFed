package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Counter extends AppCompatActivity {
    int shelfID;
    public static ArrayList<String> listOfFoods = new ArrayList<>();
    //private TextView itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_shelf);
        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));
        //itemName = (TextView) findViewById(R.id.foodText);
        RecyclerViewShelf();

        Preferences.storeValues(this);
        Preferences.pullDirectory(this);
    }

    public void RecyclerViewShelf() {
        RecyclerView recyclerView1 = findViewById(R.id.counter_recyclerview);
        CounterAdapter adapter1 = new CounterAdapter(
                Pantry.shelves.get(shelfID).getFoodPopulation(), this);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    public void toPantryClickC(View view){
        startActivity(new Intent(Counter.this, Pantry.class));
    }

    public void toAlertsClickC(View view){
        startActivity(new Intent(Counter.this, Alert.class));
    }

    public void toSettingsClickC(View view){
        startActivity(new Intent(Counter.this, Settings.class));
    }
}
