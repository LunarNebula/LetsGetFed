package com.example.arvth.letsgetfed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FoodManager extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_temp);

        readFoodData();

    }
        //DATA
        ArrayList<Food> foodDatabaseList;

        //METHODS
        private void readFoodData() {
            foodDatabaseList = new ArrayList<Food>();
            InputStream is = getResources().openRawResource(R.raw.food_database);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    // Split by ","
                    String[] fields = line.split(",");

                    // Read the data
                    foodDatabaseList.add(new Food(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
                            Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]),
                            Integer.parseInt(fields[7])));

                }

            } catch (IOException e) {
                Log.wtf("MainActivity", "ERROR reading data on line: " + line);

            }

//            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//
//            for(Food oneFood : foodDatabaseList) {
//                rootRef.push().setValue(oneFood);
//            }
        }

}