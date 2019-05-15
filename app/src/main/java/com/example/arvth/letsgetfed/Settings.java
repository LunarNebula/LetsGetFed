package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * This class models the behavior of a setting being changed.
 */
public class Settings extends AppCompatActivity {

//    RadioGroup alertsRadio;
//    RadioButton alertsRadioButton;
//    int alertRadioID;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       // alertsRadio = findViewById(R.id.alert_radiogroup);
        Button apply = findViewById(R.id.set_button);
        apply.setOnClickListener(new View.OnClickListener() {

            /**
             *
             * @param view
             */
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

    /**
     * This method allows a user to set how many days in advance they would like to be notified about food expiring.
     * @param view the button being clicked
     */
    public void setSettings(View view)
    {
        Alert.ALERT_TIME_BUFFER = Integer.valueOf(findViewById(R.id.days_in_advance_fill).toString());
        Preferences.storeValues(this, Preferences.getPreferencesFood());
        Preferences.pullDirectory(this);
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toPantryClickS(View view){
        startActivity(new Intent(Settings.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toAlertsClickS(View view){
        startActivity(new Intent(Settings.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toSettingsClickS(View view){
        startActivity(new Intent(Settings.this, Settings.class));
    }
}