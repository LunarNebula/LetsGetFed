package com.example.arvth.letsgetfed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

import static com.example.arvth.letsgetfed.Pantry.shelves;

public class AddShelf extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshelf);
        Button button = (Button) findViewById(R.id.add_shelf_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addShelfToList();
            }
        });
    }

    public void addShelfToList() {
        String shelfName = ((EditText) findViewById(R.id.enter_shelf_name)).getText().toString();

        String shelfType = ((Spinner) findViewById(R.id.shelf_dropdown)).getSelectedItem().toString();

        int shelfNumForObject = -1;

        if (shelfType.equals("Fridge"))
            shelfNumForObject = 0;
        if (shelfType.equals("Freezer"))
            shelfNumForObject = 1;
        if (shelfType.equals("Cupboard"))
            shelfNumForObject = 2;

        Shelf addThisShelf = new Shelf(shelfName, shelfNumForObject);

        shelves.add(addThisShelf);

        for (int i = 0; i < shelves.size(); i++) {
            Log.d("Shelf Name: ", shelves.get(i).getLabel());
            Log.d("Shelf Type: ", shelves.get(i).getType() + "");
        }

    }

}
