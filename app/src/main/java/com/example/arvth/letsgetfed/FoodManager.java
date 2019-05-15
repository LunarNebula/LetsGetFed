package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class FoodManager extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        readFoodData();
    }
        //DATA
        ArrayList<Food> foodDatabaseList = new ArrayList<Food>();

        //METHODS

    /**
     *
     */
    private void readFoodData() {
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

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

            for(Food oneFood : foodDatabaseList) {
                rootRef.push().setValue(oneFood);
            }
        }

    /**
     *
     * @return
     */
    public Food getFood() {
            return getFood();
        }

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

//    DatabaseReference foodRef = rootRef.child("foodDatabaseList");
//    ValueEventListener eventListener = new ValueEventListener() {
////        @Override
////        public void onDataChange(DataSnapshot dataSnapshot) {
////            ArrayList<Food> foodDatabaseReturn = new ArrayList<>();
////            for(DataSnapshot ds : dataSnapshot.getChildren()) {
////                Food oneFood = new Food (ds.getKey());
////                foodDatabaseReturn.add(oneFood);
////            }
////            Log.d("TAG", foodDatabaseReturn);
////        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {}
//    };
//foodRef.addListenerForSingleValueEvent(eventListener);

    /**
     * This method returns the user to the "Pantry" screen/class from the "FoodManager" screen/class.
     * @param view the button being clicked
     */
    public void pantry(View view) {
        startActivity(new Intent(FoodManager.this, Pantry.class));
    }

    /**
     *
     * @param length
     * @return
     */
    public static ArrayList<Food> getAlertList(int length) {
        return null;//fix later
    }
}