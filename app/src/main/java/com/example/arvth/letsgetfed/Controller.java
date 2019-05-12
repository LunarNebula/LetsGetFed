package com.example.arvth.letsgetfed;

import android.app.Application;
import android.content.res.Resources;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller extends Application {

    private String TAG = "Controller Class";
    private ArrayList<Food> firebaseFoodList  = new ArrayList<>();
    private ArrayList<Food> userFoodList = new ArrayList<>();

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
                    firebaseFoodList.add(food);
                }

                Log.d(TAG, "Count = " + (dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Add the .txt parser in here to populate the UserFoodList later

        //onStop();

        FileInputStream inputStream = null;
        String input = "";
        try {
            inputStream = openFileInput("test.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                String[] fields = line.split(",");

//                userFoodList.add(new Food(fields[0], parseDate.(fields[1]), Integer.parseInt(fields[2])));
                userFoodList.add(new Food(fields[0],
                        parseDate(fields[1]),
                        Integer.parseInt(fields[2])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "File not found",
                    Toast.LENGTH_LONG);
            toast.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        printList(userFoodList);

    }

    public void onStop () {
        //super.onStop();

        Log.d(TAG, "Here!");
        //Use Ms. Taricco's code on how to write to the local device
        for (Food eachFood : userFoodList) {
            Log.d(TAG, eachFood.getName());
            Log.d(TAG, eachFood.getPurchaseDate().toString());
            Log.d(TAG, Integer.toString(eachFood.getLocation()));
        }

        //Print out the text file when the app closes
        FileOutputStream fileOutput = null;
        String outputFilename = "test.txt";
        try {
            fileOutput = openFileOutput(outputFilename, MODE_PRIVATE);
            for (Food eachFood : userFoodList) {
                fileOutput.write((eachFood.getName() + "," +
                       toString(eachFood.getPurchaseDate()) + "," +
                        Integer.toString(eachFood.getLocation()) + "\n").getBytes());
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        finally {
            if (fileOutput != null) {
                try {
                    fileOutput.close();
                }
                catch (IOException e) { e.printStackTrace(); }
            }
        }

    }

    private void printList (ArrayList<Food> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Log.d(TAG, "userList PRINTLIST: " + arr.get(i).toString2());
        }
    }

    public ArrayList<Food> getFirebaseFoodList() {
        return firebaseFoodList;
    }

    public ArrayList<Food> getUserFoodList() {
        return userFoodList;
    }

    public void addToUserList(Food food){
        userFoodList.add(food);
    }

    public void deleteFromUserList (Food food) {
        userFoodList.remove(food);
    }

    //We won't implement this in the MVP
//    public void addToFirebaseList(Food food) {
//        FirebaseFoodList.add(food);
//    }

    public static String toString(Date date) {
        String yyyy = Integer.toString(date.getYear());

        String mm = Integer.toString(date.getMonth());
        if (mm.length() < 2)
            mm = "0" + mm;

        String dd = Integer.toString(date.getDate());
        if (dd.length() < 2)
            dd = "0" + dd;

        return yyyy + mm + dd;
    }

    public static Date parseDate(String s) {
        int yyyy = Integer.parseInt(Character.toString(s.charAt(0)).concat(Character.toString(s.charAt(1)))
                .concat(Character.toString(s.charAt(2))).concat(Character.toString(s.charAt(3))));

        int mm = 0;
        int dd = 0;
        if (s.charAt(4) == '0') {
            mm = Integer.parseInt(Character.toString(s.charAt(5)));
        }

        else {
            mm = Integer.parseInt(Character.toString(s.charAt(4)).concat(Character.toString(s.charAt(5))));
        }

        if (s.charAt(6) == '0') {
            dd = Integer.parseInt(Character.toString(s.charAt(7)));
        }

        else {
            dd = Integer.parseInt(Character.toString(s.charAt(6)).concat(Character.toString(s.charAt(7))));
        }

        Date date = new Date (yyyy, mm, dd);
        return date;
    }

    public ArrayList<Food> getShelfPopulation(int shelfID) {
        ArrayList<Food> out = new ArrayList<>();
        for(int i = 0; i < userFoodList.size(); i++) {
            if(userFoodList.get(i).getLocation() == shelfID) {
                out.add(userFoodList.get(i));
            }
        }
        return out;
    }
    public ArrayList<String> getShelfFoodNames(int shelfID) {
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < userFoodList.size(); i++) {
            if(userFoodList.get(i).getLocation() == shelfID) {
                out.add(userFoodList.get(i).getName());
            }
        }
        return out;
    }
}

