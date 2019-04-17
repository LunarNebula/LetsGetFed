package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int shelfID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);
        //shelfID = Integer.valueOf(getIntent().getStringExtra("id"));

        Spinner spinner = (Spinner) findViewById(R.id.food_type_dropdown);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.foodtypes, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appearss
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void addFoodToDatabase(View view) {
        String foodName = ((EditText) findViewById(R.id.food_name_user_fill)).getText().toString();

        String foodType = ((Spinner) findViewById(R.id.food_type_dropdown)).getSelectedItem().toString() ;

        Integer counterMin = Integer.parseInt(((EditText) findViewById(R.id.counter_min_fill)).getText().toString());

        Integer counterMax = Integer.parseInt(((EditText) findViewById(R.id.counter_max_fill)).getText().toString());

        Integer fridgeMin = (Integer.parseInt(((EditText) findViewById(R.id.fridge_min_fill)).getText().toString()));

        Integer fridgeMax = Integer.parseInt(((EditText) findViewById(R.id.fridge_max_fill)).getText().toString());

        Integer freezerMin = Integer.parseInt(((EditText) findViewById(R.id.freezer_min_fill)).getText().toString());

        Integer freezerMax = Integer.parseInt(((EditText) findViewById(R.id.freezer_max_fill)).getText().toString());

        Food addThisFood = new Food(foodName, foodType, counterMin, counterMax, fridgeMin, fridgeMax, freezerMin, freezerMax);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.push().setValue(addThisFood);

        Spinner foodTypeDropdown = findViewById(R.id.food_type_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodtypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodTypeDropdown.setAdapter(adapter);
        foodTypeDropdown.setOnItemSelectedListener(this);

        Pantry.shelves.get(shelfID).addFood(addThisFood);

//        Spinner foodDropdown = findViewById(R.id. //blank );
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.foodsList, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        foodDropdown.setAdapter(adapter2);
//        foodDropdown.setOnItemSelectedListener(this);
        startActivity(new Intent(AddFood.this, Pantry.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }
    public void pantry(View view) {
        Intent intent = new Intent(AddFood.this, Pantry.class);
        intent.putExtra("id", shelfID);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
