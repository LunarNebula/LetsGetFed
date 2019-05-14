package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
//import android.widget.Spinner;
import android.widget.Toast;

import static com.example.arvth.letsgetfed.Pantry.shelves;

/**
 * This class models the behavior of a shelf being added to a pantry.
 */
public class AddShelf extends AppCompatActivity {

    RadioGroup shelfRadio;
    RadioButton radioButton;
    int radioID;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshelf);

        shelfRadio = findViewById(R.id.shelf_type_radiogroup);

//        Spinner spinner2 = (Spinner) findViewById(R.id.shelf_dropdown);
////// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
//                R.array.shelfTypes, android.R.layout.simple_spinner_item);
////// Specify the layout to use when the list of choices appears
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////// Apply the adapter to the spinner
//        spinner2.setAdapter(adapter2);

//        Spinner shelfDropdown = findViewById(R.id.shelf_dropdown);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shelfTypes, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shelfDropdown.setAdapter(adapter);
//        shelfDropdown.setOnItemSelectedListener(this);
        //addShelfToList();
    }

    /**
     * This method allows a user to check a radio button and sets a variable to the ID of the button.
     * @param view the button that is being clicked or selected
     */
    public void checkButton(View view)
    {
        radioID = shelfRadio.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    /**
     *
     * @param view
     */
    public void addShelfToList(View view) {
        String shelfName = ((EditText) findViewById(R.id.enter_shelf_name)).getText().toString();

      //  String shelfType = ((Spinner) findViewById(R.id.shelf_dropdown)).getSelectedItem().toString();

       int shelfNumForObject = radioID;
//
//        if (shelfType.equals("Fridge"))
//            shelfNumForObject = 0;
//        if (shelfType.equals("Freezer"))
//            shelfNumForObject = 1;
//        if (shelfType.equals("Cupboard"))
//            shelfNumForObject = 2;

        Shelf addThisShelf = new Shelf (shelfName, shelfNumForObject);

        Pantry.shelves.add(addThisShelf);
        Controller aController = (Controller) getApplicationContext();
        Preferences.storeValues(this, Preferences.getPreferencesFood());
        Preferences.pullDirectory(this);
        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "AddShelf" screen/class.
     * @param view the button being clicked
     */
    public void pantry(View view) {

        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "AddShelf" screen/class
     * @param view the button being clicked
     */
    public void toPantryClickAS(View view){
        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "AddShelf" screen/class
     * @param view the button being clicked
     */
    public void toAlertsClickAS(View view){
        startActivity(new Intent(AddShelf.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "AddShelf" screen/class
     * @param view the button being clicked
     */
    public void toSettingsClickAS(View view){
        startActivity(new Intent(AddShelf.this, Settings.class));
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

}
