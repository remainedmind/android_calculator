package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView dataLayout;
    private final Button[] digitButtons = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLayout = findViewById(R.id.text_input);

        int[] buttonIds = {
                R.id.btn_digit_0, R.id.btn_digit_1, R.id.btn_digit_2, R.id.btn_digit_3, R.id.btn_digit_4,
                R.id.btn_digit_5, R.id.btn_digit_6, R.id.btn_digit_7, R.id.btn_digit_8, R.id.btn_digit_9
        };

        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i] = findViewById(buttonIds[i]);
            digitButtons[i].setOnClickListener(digitClickListener);
        }

        Button operationClearButton = findViewById(R.id.btn_operation_clear);
        operationClearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dataLayout.setText("0");
            }
        });
    };

    private final View.OnClickListener digitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            appendDigit(clickedButton.getText().toString());
        }
    };

    private void appendDigit(String digit) {
        String currentInput = dataLayout.getText().toString();
        if (currentInput.equals("0")) {
            currentInput = digit;
        } else {
            currentInput += digit;
        }
        dataLayout.setText(currentInput);
    };




}