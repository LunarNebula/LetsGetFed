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

import static com.example.arvth.letsgetfed.Pantry.shelves;

public class AddShelf extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshelf);

        Spinner spinner2 = (Spinner) findViewById(R.id.shelf_dropdown);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.shelfTypes, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

//        Spinner shelfDropdown = findViewById(R.id.shelf_dropdown);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shelfTypes, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shelfDropdown.setAdapter(adapter);
//        shelfDropdown.setOnItemSelectedListener(this);
        //addShelfToList();
    }

    public void shelf_dropdown(View view) {
        String shelfName = ((EditText) findViewById(R.id.enter_shelf_name)).getText().toString();

        String shelfType = ((Spinner) findViewById(R.id.shelf_dropdown)).getSelectedItem().toString();

        int shelfNumForObject = -2;

        if (shelfType.equals("Fridge"))
            shelfNumForObject = 0;
        if (shelfType.equals("Freezer"))
            shelfNumForObject = 1;
        if (shelfType.equals("Cupboard"))
            shelfNumForObject = 2;

        Shelf addThisShelf = new Shelf (shelfName, shelfNumForObject);

        shelves.add(addThisShelf);

        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

    public void pantry(View view) {
        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
