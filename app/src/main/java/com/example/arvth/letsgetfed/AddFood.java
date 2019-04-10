package com.example.arvth.letsgetfed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);
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
}
