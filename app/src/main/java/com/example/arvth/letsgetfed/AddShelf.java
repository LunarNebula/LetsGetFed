package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.arvth.letsgetfed.Pantry.shelves;

public class AddShelf extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshelf);
    }

    public void addShelfToList() {
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


    }
    public void pantry(View view) {
        startActivity(new Intent(AddShelf.this, Pantry.class));
    }

}
