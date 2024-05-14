package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView dataLayout;
    private final Button[] digitButtons = new Button[10];
    private double firstNumber = 0;
    private String currentOperator = "";

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
        operationClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        Button operationAdditionButton = findViewById(R.id.btn_operation_addition);
        operationAdditionButton.setOnClickListener(operationClickListener);

        Button operationSubtractionButton = findViewById(R.id.btn_operation_subtraction);
        operationSubtractionButton.setOnClickListener(operationClickListener);

        Button operationEqualButton = findViewById(R.id.btn_equal);
        operationEqualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
            }
        });
    }

    private View.OnClickListener digitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            appendDigit(clickedButton.getText().toString());
        }
    };

    private View.OnClickListener operationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            setOperator(clickedButton.getText().toString());
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
    }

    private void setOperator(String operator) {
        firstNumber = Double.parseDouble(dataLayout.getText().toString());
        currentOperator = operator;
        clearData();
    }

    private void clearData() {
        dataLayout.setText("0");
    }

    private void performOperation() {
        double secondNumber = Double.parseDouble(dataLayout.getText().toString());
        double result = 0;

        switch (currentOperator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            // Add more cases for other operations if needed

        }
        dataLayout.setText(String.valueOf(result));
        firstNumber = 0;
        currentOperator = "";
    }
}
