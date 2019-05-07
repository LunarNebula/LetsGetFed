package com.example.arvth.letsgetfed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

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
        final Controller aController = (Controller) getApplicationContext();
        ArrayList<Food> userFood = aController.getUserFoodList();
        ArrayList<Food> firebaseFood = aController.getFirebaseFoodList();
        for(int i = 0; i < userFood.size(); i++) {
            int getLocation = firebaseFood.indexOf(userFood.get(i));
            long timeBuffer = -1;
            switch(userFood.get(i).getLocation()) {
                case 0:
                    timeBuffer = firebaseFood.get(getLocation).getCounterMinExp();
                    break;
                case 1:
                    timeBuffer = firebaseFood.get(getLocation).getFridgeMinExp();
                    break;
                case 2:
                    timeBuffer = firebaseFood.get(getLocation).getFreezerMinExp();
            }
            long spd = 86400000;
            Date date = new Date(firebaseFood.get(getLocation).getPurchaseDate().getTime()
                    + spd * timeBuffer - ALERT_TIME_BUFFER);
            if(date.before(new Date())) {
                layout.addView(getAlertTitle(userFood.get(i)));
            }
        }
        //notification();
    }
    public TableRow getAlertTitle(Food food) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        row.setLayoutParams(params);
        TextView title = new TextView(this);
        title.setText(food.getName());
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
    public void notification() {
        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder build = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alert_icon)
                .setContentTitle("One or more food items is expiring soon!")
                .setContentText("")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, build.build());
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