package com.example.arvth.letsgetfed;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Controller extends Application {

    private String TAG = "Controller Class";
    private ArrayList<Food> foodList  = new ArrayList<Food>();


    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Before");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Log.d(TAG, "After");
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "Before Loop");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Food food = ds.getValue(Food.class);
                    Log.d(TAG, "Value is: " + food);
                    foodList.add(food);
                }

                Log.d(TAG, "Count = "+ (dataSnapshot.getChildrenCount()));

                printList();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void printList() {
        for (int i = 0; i < foodList.size(); i++) {
            Log.d(TAG, foodList.get(i).toString());
        }
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }






}
