package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        Button addFoodBut = findViewById(R.id.add_food_button);
        addFoodBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFoodToDatabase();
            }
        });


    }

    private void addFoodToDatabase() {
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

    }
    public void pantry(View view) {
        startActivity(new Intent(AddFood.this, Pantry.class));
    }
}