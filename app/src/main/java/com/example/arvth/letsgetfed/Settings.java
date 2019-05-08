package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    RadioGroup alertsRadio;
    RadioButton alertsRadioButton;
    int alertRadioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       // alertsRadio = findViewById(R.id.alert_radiogroup);
        Button apply = findViewById(R.id.set_button);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSettings(view);
            }
        });

    }

//    public void checkButton(View view)
//    {
//        alertRadioID = alertsRadio.getCheckedRadioButtonId();
//        alertsRadioButton = findViewById(alertRadioID);
//    }

    public void setSettings(View view)
    {

    }

    public void toPantryClickS(View view){
        startActivity(new Intent(Settings.this, Pantry.class));
    }

    public void toAlertsClickS(View view){
        startActivity(new Intent(Settings.this, Alert.class));
    }

    public void toSettingsClickS(View view){
        startActivity(new Intent(Settings.this, Settings.class));
    }
}