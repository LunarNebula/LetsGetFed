package com.example.arvth.letsgetfed;

import android.app.Application;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Date;

/**
 * The Controller class handles two ArrayLists of data, the firebase food list as well as the user food list.
 * It stores and retrieves this data when the app is opened and closed, and makes this data available to
 * other classes in this app.
 */
public class Controller extends Application {

    private String TAG = "Controller Class";
    private ArrayList<Food> firebaseFoodList  = new ArrayList<>();
    private ArrayList<Food> userFoodList = new ArrayList<>();

    /**
     * This method opens the app and then reads from firebase to create a list of firebase foods that the
     * user can choose from to add to their pantry. The method also parses through a text file that is
     * written on the local device to populate an array list of user foods.
     */
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Before");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Log.d(TAG, "After");
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {

            /**
             * This method gets a data snapshot of the firebase database everytime the app is opened
             * @param dataSnapshot firebase data
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "Before Loop");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Food food = ds.getValue(Food.class);
                   // Log.d(TAG, "Value is: " + food);
                    firebaseFoodList.add(food);
                }

                Log.d(TAG, "Count = " + (dataSnapshot.getChildrenCount()));
            }

            /**
             * This method catches the previous method in case there is a firebase error
             * @param error error with the data snapshot
             */
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // .txt parser to populate the UserFoodList later
        FileInputStream inputStream = null;
        String input = "";
        try {
            inputStream = openFileInput("test.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                String[] fields = line.split(",");

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
        onStop();
    }

    /**
     * This method writes back to the .txt file when the app closes to store the user's food list
     * on the local device
     */
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

    /**
     * This method helps to print out array lists to the Logcat for debugging purposes
     * @param arr array list of food
     */
    public void printList (ArrayList<Food> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Log.d(TAG, "userList PRINTLIST: " + arr.get(i).toString2());
        }
    }

    /**
     * This method allows other classes to access the firebase food list (to populate spinners)
     * @return firebase food list
     */
    public ArrayList<Food> getFirebaseFoodList() {
        return firebaseFoodList;
    }

    /**
     * This method allows other classes to access the user food list
     * @return user food list
     */
    public ArrayList<Food> getUserFoodList() {
        return userFoodList.size() >= Preferences.getPreferencesFood().size() ? userFoodList : Preferences.getPreferencesFood();
    }

    /**
     * This method allows things to be added from other classes into the user food list (so that the user can
     * manage their data through the UI)
     *
     * @param food food object
     */
    public void addToUserList(Food food){
        userFoodList.add(food);
        Preferences.addPreferencesFood(food);
    }

    /**
     * This method allows other classes to delete from the user food list (so that the user can
     * manage their data through the UI)
     * @param i index of food object in the array list
     */
    public void deleteFromUserList (int i) {
        printList(userFoodList);
        Log.d(TAG, "Position of Removal: " + i);
        userFoodList.remove(i);
        printList(userFoodList);
    }

    /**
     * This method writes a data object as a string
     * @param date date object
     * @return string of date
     */
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

    /**
     * This method parses the a string to return a date object
     * @param s string of a date
     * @return date object
     */
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

    /**
     * This method returns all of the foods in a certain shelf
     * @param shelfID the ID of the shelf (0 = counter, 1 = fridge, 2 = freezer)
     * @return array list of all the foods in that shelf
     */
    public ArrayList<Food> getShelfPopulation(int shelfID) {
        ArrayList<Food> shelfPopulation = new ArrayList<>();

        for (int i = 0; i < userFoodList.size(); i++) {
            if (userFoodList.get(i).getLocation() == shelfID) {
                shelfPopulation.add(userFoodList.get(i));
            }
        }

        return shelfPopulation;
    }

    /**
     * This method returns only the names of all the food objects in a certain shelf
     * @param shelfID the ID of the shelf (0 = counter, 1 = fridge, 2 = freezer)
     * @return array list of strings of names of food objects
     */
    public ArrayList<String> getShelfFoodNames(int shelfID) {
        ArrayList<String> out = new ArrayList<>();
        ArrayList<Food> use = userFoodList.size() > Preferences.getPreferencesFood().size() ? userFoodList : Preferences.getPreferencesFood();
        for(int i = 0; i < use.size(); i++) {
            if(use.get(i).getLocation() == shelfID) {
                out.add(use.get(i).getName());
            }
        }
        return out;
    }
}

