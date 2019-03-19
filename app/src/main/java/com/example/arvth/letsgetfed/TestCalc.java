package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class TestCalc extends AppCompatActivity {
    private final int ADD = 0, SUBTRACT = 1, MULTIPLY = 2, DIVIDE = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcalc);
        ((TextView) findViewById(R.id.answer)).setText("");
    }
    public void add(View view) {
        checkPoint(ADD);
    }
    public void subtract(View view) {
        checkPoint(SUBTRACT);
    }
    public void multiply(View view) {
        checkPoint(MULTIPLY);
    }
    public void divide(View view) {
        checkPoint(DIVIDE);
    }
    public void checkPoint(int calculation) {
        EditText first = findViewById(R.id.firstNum), second = findViewById(R.id.secondNum);
        if(checkInput(first.getText().toString()) && checkInput(second.getText().toString())) execute(calculation);
        else ((TextView) findViewById(R.id.answer)).setText("Invalid input.");;
    }
    public void execute(int calculation) {
        EditText first = findViewById(R.id.firstNum), second = findViewById(R.id.secondNum);
        double firstNum = Double.valueOf(first.getText().toString()),
                secondNum = Double.valueOf(second.getText().toString()), answer = 0;
        switch(calculation) {
            case ADD:
                answer = firstNum + secondNum;
                break;
            case SUBTRACT:
                answer = firstNum - secondNum;
                break;
            case MULTIPLY:
                answer = firstNum * secondNum;
                break;
            case DIVIDE:
                answer = firstNum / secondNum;
                break;
        }
        Intent intent = new Intent(TestCalc.this, Answer.class);
        intent.putExtra("answer", answer);
        startActivity(intent);
    }

    public boolean checkInput(String input) {
        boolean period = false;
        for(int i = 0; i < input.length(); i++) {
            try {
                Integer.valueOf(input.charAt(i));
            } catch(Exception e) {
                if(i != 0 || input.charAt(i) != '-') return false;
            } //check code
        }
        return true;
    }
}