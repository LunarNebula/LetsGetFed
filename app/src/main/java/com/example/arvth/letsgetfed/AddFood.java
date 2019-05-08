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

public class AddFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    int shelfID;
    RadioGroup foodTypeRadio;
    RadioButton typeRadioButton;
    int typeRadioID;
    private String TAG = "AddFood Class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        Spinner foodDropDown = findViewById(R.id.food_dropdown_spinner);
        ArrayList<String> foodArray = new ArrayList<>();

        Intent intent = getIntent();
        int shelfID = intent.getIntExtra("id", -1);

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


//        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));

//        Spinner spinner = (Spinner) findViewById(R.id.food_type_dropdown);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.foodtypes, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
    }

    public void checkButton(View view)
    {
        typeRadioID = foodTypeRadio.getCheckedRadioButtonId();
        typeRadioButton = findViewById(typeRadioID);
    }

    public void selectFood()
    {

    }

    //public void addFoodToDatabase(View view) {
        //String text = ((Spinner)view).getItemAtPosition().toString();
//        try {
//            Pantry.shelves.get(shelfID).addFood(getNewFood());
//        } catch(Exception e) {
//            Pantry.shelves.get(shelfID).addFood(new Food("", "fridge", 0,0,0,0,0,0));
//        } catch(Error e) {
//            Pantry.shelves.get(shelfID).addFood(new Food("", "fridge", 0,0,0,0,0,0));
//        }
//
//        startActivity(new Intent(AddFood.this, Pantry.class));
    //}

//        Spinner foodTypeDropdown = findViewById(R.id.food_type_dropdown);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodtypes, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        foodTypeDropdown.setAdapter(adapter);
//        foodTypeDropdown.setOnItemSelectedListener(this);
//        Preferences.storeValues(this);
//        Preferences.pullDirectory(this);
//        return addThisFood;

//        Spinner foodDropdown = findViewById(R.id. //blank );
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.foodsList, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        foodDropdown.setAdapter(adapter2);
//        foodDropdown.setOnItemSelectedListener(this);




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }
    public void pantry(View view) {
        Intent intent = new Intent(AddFood.this, Pantry.class);
        intent.putExtra("id", shelfID);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void toPantryClickAF(View view){
        startActivity(new Intent(AddFood.this, Pantry.class));
    }

    public void toAlertsClickAF(View view){
        startActivity(new Intent(AddFood.this, Alert.class));
    }

    public void toSettingsClickAF(View view){
        startActivity(new Intent(AddFood.this, Settings.class));
    }
    public void addFoodToDatabase(View view) {
        Spinner spinner = findViewById(R.id.food_dropdown_spinner);
        String type = spinner.getSelectedItem().toString();
        Spinner yearSpinner = findViewById(R.id.date_year_spinner);
        int year = Integer.valueOf(yearSpinner.getSelectedItem().toString());
        Spinner monthSpinner = findViewById(R.id.date_month_spinner);
        int month = monthSpinner.getSelectedItemPosition();
        Spinner daySpinner = findViewById(R.id.date_day_spinner);
        int day = daySpinner.getSelectedItemPosition();
        Date purchaseDate = new Date(year, month, day);
        Log.d("add_food_results", type + " " + purchaseDate.toString());
        final Controller aController = (Controller) getApplicationContext();
        aController.addToUserList(new Food(type, purchaseDate, shelfID));
        aController.onStop(); //changed this to onStop from the pulled code
        //onStop will get working tomorrow in class
        //onStop methods just have to be added to the bottom of every class
        startActivity(new Intent(AddFood.this, Pantry.class));


    }
//    public void addFoodToDatabase(View view) {
//        Spinner spinner = findViewById(R.id.food_dropdown_spinner);
//        String type = "";
//        try{
//            type = spinner.getSelectedItem().toString();
//        } catch(Exception e) {
//            type = "ligma";
//        }
//        spinner = findViewById(R.id.date_year_spinner);
//        int year = Integer.valueOf(spinner.getSelectedItem().toString()) - 1900;
//        spinner = findViewById(R.id.date_month_spinner);
//        int month = Integer.valueOf(spinner.getSelectedItem().toString());
//        spinner = findViewById(R.id.date_day_spinner);
//        int day = Integer.valueOf(spinner.getSelectedItem().toString());
//        Date purchaseDate = new Date(year, month, day);
//        final Controller aController = (Controller) getApplicationContext();
//        aController.addToUserList(new Food(type, purchaseDate, shelfID));
//        startActivity(new Intent(AddFood.this, Pantry.class));
//    }


}
