package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Answer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Double answer = getIntent().getExtras().getDouble("answer");
        ((TextView) findViewById(R.id.answer_text)).setText("The answer is " + answer + ".");
    }

    public void answer_to_main(View view) {
        startActivity(new Intent(Answer.this, TestCalc.class));
    }
}