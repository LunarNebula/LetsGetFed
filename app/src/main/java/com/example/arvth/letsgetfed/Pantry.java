package com.example.arvth.letsgetfed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Pantry extends AppCompatActivity {
    public static Shelf[] shelves;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
    }

    public void add_shelf(View view) {
        //insert code for getting another Shelf item info
        Shelf shelf = new Shelf("test_" + shelves.length);


    }
    public void updateVisualList() {
        TableLayout shelf_list = findViewById(R.id.shelf_list);
        int shelfQuantity = shelf_list.getChildCount();
        for(int i = 0; i < shelfQuantity; i++) {
            View testRow = shelf_list.getChildAt(i);
            if(testRow instanceof TableRow) ((TableRow) testRow).removeAllViews();
        }

        for(int i = 0; i < shelves.length; i++) {
            TableRow shelf_row = new TableRow(this);
            shelf_row.addView(getButton(i));
            shelf_list.addView(shelf_row);
        }
    }
    public Button getButton(int ID) {
        Button button = new Button(this);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(parameters);
        button.setId(ID);
        button.setText(shelves[ID].getLabel());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return button;
    }
}