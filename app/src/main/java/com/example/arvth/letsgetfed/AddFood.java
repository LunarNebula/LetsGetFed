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

        Spinner foodDropDown = (Spinner) findViewById(R.id.food_dropdown_spinner);
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("rice pilaf");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodDropDown.setAdapter(adapterSpinner);
        foodDropDown.setOnItemSelectedListener(this);

//        foodTypeRadio = findViewById(R.id.food_types_radiogroup);
//        Button addFoodButton = findViewById(R.id.add_food_button);
//        addFoodButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        //shelfID = Integer.valueOf(getIntent().getStringExtra("id"));

//        Spinner spinner = (Spinner) findViewById(R.id.food_type_dropdown);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.foodtypes, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

//        Food test = new Food("pencil", "writing", 1,1,1,1,1,1);
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        rootRef.push().setValue(test);
    }

    public void checkButton(View view)
    {
        typeRadioID = foodTypeRadio.getCheckedRadioButtonId();
        typeRadioButton = findViewById(typeRadioID);
    }

    public void selectFood()
    {

    }
//    public void addFoodToDatabase(View view) {
//        try {
//            Pantry.shelves.get(shelfID).addFood(getNewFood());
//        } catch(Exception e) {
//            Pantry.shelves.get(shelfID).addFood(new Food("", "fridge", 0,0,0,0,0,0));
//        } catch(Error e) {
//            Pantry.shelves.get(shelfID).addFood(new Food("", "fridge", 0,0,0,0,0,0));
//        }
//
//        startActivity(new Intent(AddFood.this, Pantry.class));
//    }

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
}
