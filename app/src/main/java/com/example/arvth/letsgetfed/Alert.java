package com.example.arvth.letsgetfed;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class models the behavior of an alert being sent out to a user.
 */
public class Alert extends AppCompatActivity {
    private final long MSPD = 86400000;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        loadAlerts();
    }

    public static int ALERT_TIME_BUFFER = 3;

    /**
     * This method populates the TableLayout with alerts.
     */
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
        //Toast.makeText(this, userFood.size() + "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, firebaseFood.size() + " nugget", Toast.LENGTH_SHORT).show();
        boolean notify = true;
        for(int i = 0; i < userFood.size(); i++) {
            int getLocation = 0;
            while(!(firebaseFood.get(getLocation).getName().equals(userFood.get(i).getName()))) {
                getLocation++;
            };
            Date date = new Date(userFood.get(i).getPurchased().getTime() + (firebaseFood.get(getLocation).minExpirationTime() - ALERT_TIME_BUFFER) * MSPD);
            Date current = new Date();
            current.setYear(current.getYear() + 1900);
            Log.d("bought", userFood.get(i).getPurchased().toString());
            Log.d("expirationWarning", date.toString());
            if(current.after(date)) {
                layout.addView(getAlertTitle(userFood.get(i)));
                //layout.addView(getAlertTime(userFood.get(i)));
                if(notify) notification();
                notify = false;
            }
        }
    }

    /**
     * This method returns a TableRow with a TextView with a given food name.
     * @param food the given Food item from which the food name is extracted
     * @return the TableRow with a Textview with a given food name
     */
    public TableRow getAlertTitle(Food food) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(15, 10, 15, 0);
        row.setLayoutParams(params);
        TextView title = new TextView(this);
        title.setText(food.getName());
        row.addView(title);
        return row;
    }

    /**
     * This method returns a TableRow with a TextView with the amount of time it takes for a given food item to expire
     * @param food the given Food item from which the date purchased and the minimum expiration date is extracted
     * @return the amount of time remaining until the given food expires
     */
    public TableRow getAlertTime(Food food) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 10);
        row.setLayoutParams(params);
        TextView title = new TextView(this);
        Date date = food.getPurchased();
        date.setYear(date.getYear() - 1900);
        food.setPurchased(date);
        title.setText(((food.getPurchased().getTime() + food.minExpirationTime() * MSPD - (new Date()).getTime()) / MSPD) + "");
        row.addView(title);
        return row;
    }

    /**
     * This method writes and notification about a food item or food items that will be expiring soon
     */
    public void notification() {

        NotificationCompat.Builder build = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alert_icon)
                .setContentTitle("One or more food items is expiring soon!")
                .setContentText("")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent = new Intent(this, Pantry.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        build.setContentIntent(pIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, build.build());
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Alert" screen/class
     * @param view the button that is being clicked
     */
    public void toPantryClickA(View view){
        startActivity(new Intent(Alert.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Alert" screen/class
     * @param view the button that is being clicked
     */
    public void toAlertsClickA(View view){
        startActivity(new Intent(Alert.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Alert" screen/class
     * @param view the button that is being clicked
     */
    public void toSettingsClickA(View view){
        startActivity(new Intent(Alert.this, Settings.class));
    }
}