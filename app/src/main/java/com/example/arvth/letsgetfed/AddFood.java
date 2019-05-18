package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * This class models the behavior of a food being added to a shelf.
 */
public class AddFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    int shelfID;
    RadioGroup foodTypeRadio;
    RadioButton typeRadioButton;
    int typeRadioID;
    private String TAG = "AddFood Class";

    /**
     * This method builds activity_addfood with a given Bundle. It initializes the food and date spinners,
     * as well as gets an intent from the previous class to know what shelf the food should be added to.
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        Spinner foodDropDown = findViewById(R.id.food_dropdown_spinner);
        ArrayList<String> foodArray = new ArrayList<>();

        Intent intent = getIntent();
        shelfID = intent.getIntExtra("id", -1);
        Log.d(TAG, Integer.toString(shelfID));

        final Controller aController = (Controller) getApplicationContext();
        for(int i = 0; i < aController.getFirebaseFoodList().size(); i++) {
            foodArray.add(aController.getFirebaseFoodList().get(i).getName());
        }

        ArrayAdapter<String> foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodArray);
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodDropDown.setAdapter(foodAdapter);
        foodDropDown.setOnItemSelectedListener(this);


        Spinner monthDropDown = findViewById(R.id.date_month_spinner);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        ArrayList<String> monthArray = new ArrayList(Arrays.asList(months));
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthArray);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthDropDown.setAdapter(monthAdapter);
        monthDropDown.setOnItemSelectedListener(this);

        Spinner dayDropDown = findViewById(R.id.date_day_spinner);
        ArrayList<String> dayArray = new ArrayList<>();
        for(int i = 1; i <= 31; i++) {
            dayArray.add(i + "");
        }
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayArray);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayDropDown.setAdapter(dayAdapter);
        dayDropDown.setOnItemSelectedListener(this);

        Spinner yearDropDown = findViewById(R.id.date_year_spinner);
        ArrayList<String> yearArray = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            yearArray.add(((new Date()).getYear() - i + 1900) + "");
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearArray);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearDropDown.setAdapter(yearAdapter);
        yearDropDown.setOnItemSelectedListener(this);
    }

    /**
     * This method allows a user to check a radio button and sets a variable to the ID of the button.
     * @param view the button that is being clicked or selected
     */
    public void checkButton(View view)
    {
        typeRadioID = foodTypeRadio.getCheckedRadioButtonId();
        typeRadioButton = findViewById(typeRadioID);
    }

    /**
     * Override of the onItemSelected method (no function, needed to keep remove error from class)
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "AddFood" screen/class.
     * @param view the button that is being clicked
     */
    public void pantry(View view) {
        Intent intent = new Intent(AddFood.this, Pantry.class);
        intent.putExtra("id", shelfID);
        startActivity(intent);
    }

    /**
     * Override of the onNothingSelected method (no function, needed to keep remove error from class)
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "AddFood" screen/class
     * @param view the button that is being clicked
     */
    public void toPantryClickAF(View view){
        startActivity(new Intent(AddFood.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "AddFood" screen/class
     * @param view the button that is being clicked
     */
    public void toAlertsClickAF(View view){
        startActivity(new Intent(AddFood.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "AddFood" screen/class
     * @param view the button that is being clicked
     */
    public void toSettingsClickAF(View view){
        startActivity(new Intent(AddFood.this, Settings.class));
    }

    /**
     * This method adds a Food object to the database
     * @param view the button being clicked
     */
    public void addFoodToDatabase(View view) {
        Spinner spinner = findViewById(R.id.food_dropdown_spinner);
        String type = spinner.getSelectedItem().toString();
        Spinner yearSpinner = findViewById(R.id.date_year_spinner);
        int year = Integer.valueOf(yearSpinner.getSelectedItem().toString());
        Spinner monthSpinner = findViewById(R.id.date_month_spinner);
        int month = monthSpinner.getSelectedItemPosition();
        Spinner daySpinner = findViewById(R.id.date_day_spinner);
        int day = daySpinner.getSelectedItemPosition() + 1;
        Date purchaseDate = new Date(year, month, day);
        Log.d("add_food_results", type + " " + purchaseDate.toString());
        final Controller aController = (Controller) getApplicationContext();
        aController.addToUserList(new Food(type, purchaseDate, shelfID));
        // Preferences.storeValues(this, Preferences.getPreferencesFood());
        for(int j = 0; j < aController.getUserFoodList().size(); j++) {
            Log.d(TAG, aController.getUserFoodList().get(j).toString2());
        }

        startActivity(new Intent(AddFood.this, Pantry.class));
    }
}
