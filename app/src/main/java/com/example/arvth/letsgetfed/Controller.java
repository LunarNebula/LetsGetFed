package com.example.arvth.letsgetfed;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Controller extends Application {

    private static String TAG = "Controller Class";
    private static ArrayList<Food> FirebaseFoodList  = new ArrayList<Food>();
    private static ArrayList<Food> UserFoodList = new ArrayList<>();

    public static ArrayList<Food> getFoodList() {
        return FirebaseFoodList;
    }


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
                    FirebaseFoodList.add(food);
                }

                Log.d(TAG, "Count = " + (dataSnapshot.getChildrenCount()));

                printList();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Add the .txt parser in here to populate the UserFoodList later
        Date date1 = new Date(2019, 8, 12);
        Food temp1 = new Food("banana", date1, 0);
        Food temp2 = new Food("apple", date1, 1);
        Food temp3 = new Food("grape", date1, 2);
        UserFoodList.add(temp1);
        UserFoodList.add(temp2);
        UserFoodList.add(temp3);
    }

    public void onClose () {
        //Use Ms. Taricco's code on how to write to the local device
        for (Food eachFood : UserFoodList) {
            Log.d(TAG, eachFood.getName());
            Log.d(TAG, eachFood.getPurchaseDate().toString());
            Log.d(TAG, Integer.toString(eachFood.getLocation()));
        }

        FileOutputStream fileOutput = null;
        String outputFilename = "test.txt";
        try {
            fileOutput = openFileOutput(outputFilename, MODE_PRIVATE);
            fileOutput.write("TEST STRING".getBytes());
        }

        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); };

//        finally {
//            if (fileOutput != null) {
//                try {
//                    fileOutput.close();
//                }
//                catch (IOException e) { e.printStackTrace(); }
//            }
//        }

    }

    private void printList() {
        for (int i = 0; i < FirebaseFoodList.size(); i++) {
            Log.d(TAG, FirebaseFoodList.get(i).toString());
        }
    }

    public ArrayList<Food> getFirebaseFoodList() {
        return FirebaseFoodList;
    }

    public ArrayList<Food> getUserFoodList() {
        return UserFoodList;
    }

    public void addToUserList(Food food){
        UserFoodList.add(food);
    }

    public void deleteFromUserList (Food food) {
        UserFoodList.remove(food);
    }

    //We won't implement this in the MVP
//    public void addToFirebaseList(Food food) {
//        FirebaseFoodList.add(food);
//    }

}

