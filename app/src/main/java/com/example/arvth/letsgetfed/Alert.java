package com.example.arvth.letsgetfed;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Alert extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
    }

    public static void howManyDaysLeft() {
        Date currentTime = Calendar.getInstance().getTime();
        Log.d("currentDate", currentTime.toString());
    }
}
