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

public class Edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String TAG = "Edit Class";
    int shelfID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Spinner foodDropDown = findViewById(R.id.food_dropdown_spinner);
        ArrayList<String> foodArray = new ArrayList<>();
        shelfID = 0;

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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void toPantryClickE(View view){
        startActivity(new Intent(Edit.this, Pantry.class));
    }

    public void toAlertsClickE(View view){
        startActivity(new Intent(Edit.this, Alert.class));
    }

    public void toSettingsClickE(View view){
        startActivity(new Intent(Edit.this, Settings.class));
    }
    public void addFoodToDatabase(View view) {
        Spinner spinner = findViewById(R.id.food_dropdown_spinner);
        String type = spinner.getSelectedItem().toString();
        Spinner yearSpinner = findViewById(R.id.date_year_spinner);
        int year = Integer.valueOf(yearSpinner.getSelectedItem().toString()) - 1900;
        Spinner monthSpinner = findViewById(R.id.date_month_spinner);
        int month = monthSpinner.getSelectedItemPosition() + 1;
        Spinner daySpinner = findViewById(R.id.date_day_spinner);
        int day = daySpinner.getSelectedItemPosition() + 1;
        Date purchaseDate = new Date(year, month, day);
        Log.d("add_food_results", type + " " + purchaseDate.toString());
        final Controller aController = (Controller) getApplicationContext();
        aController.addToUserList(new Food(type, purchaseDate, shelfID));
        aController.onStop();
        startActivity(new Intent(Edit.this, Pantry.class));
    }
}
