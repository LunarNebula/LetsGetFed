package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Alert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        loadAlerts();
    }
    public static int ALERT_TIME_BUFFER = 5;
    public void loadAlerts() {
        TableLayout layout = findViewById(R.id.alert_list);
        int count = layout.getChildCount();
        for(int i = 0; i < count; i++) {
            View tableView = layout.getChildAt(i);
            if(tableView instanceof TableRow) ((TableRow) tableView).removeAllViews();
        }
        for(int s = 0; s < Pantry.shelves.size(); s++) {
            for(int p = 0; p < Pantry.shelves.get(s).getPopulation(); p++) {
                if(Pantry.shelves.get(s).getFood(p).isExpiring()) {
                    layout.addView(getAlertTitle(s, p));
                    layout.addView(getAlertTime(s, p));
                }
            }
        }
    }
    public TableRow getAlertTitle(int shelfID, int foodID) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        row.setLayoutParams(params);
        TextView title = new TextView(this);
        title.setText(Pantry.shelves.get(shelfID).getFood(foodID).getName());
        row.addView(title);
        return row;
    }
    public TableRow getAlertTime(int shelfID, int foodID) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 10);
        row.setLayoutParams(params);
        TextView title = new TextView(this);
        title.setText(Pantry.shelves.get(shelfID).getFood(foodID).minExpirationTime() + " days left!");
        row.addView(title);
        return row;
    }

    public void toPantryClickA(View view){
        startActivity(new Intent(Alert.this, Pantry.class));
    }

    public void toAlertsClickA(View view){
        startActivity(new Intent(Alert.this, Alert.class));
    }

    public void toSettingsClickA(View view){
        startActivity(new Intent(Alert.this, Settings.class));
    }
}