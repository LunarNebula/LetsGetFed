package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OpeningScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        // random comment - remove later
        initiate(); //set text for timing and info
    }

    public void initiate() {
        Toast.makeText(this, "Initiation reached.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(OpeningScreen.this, Pantry.class));
        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }
}