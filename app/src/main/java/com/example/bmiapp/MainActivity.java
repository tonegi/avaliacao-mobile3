package com.example.bmiapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private EditText edKg, edMeter;
    private CardView cardBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListener();
    }

    private void initializeViews() {
        edMeter = findViewById(R.id.edMeter);
        edKg = findViewById(R.id.edKg);
        cardBtn = findViewById(R.id.cardBtn);
        textView = findViewById(R.id.textView);
    }

    private void setupClickListener() {
        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String kgText = edKg.getText().toString();
        String meterText = edMeter.getText().toString();

        if (isValidInput(kgText, meterText)) {
            float weight = Float.parseFloat(kgText);
            float height = Float.parseFloat(meterText);
            float bmi = calculateBMIIndex(weight, height);
            displayBMIResult(bmi);
        } else {
            textView.setText("Por favor, insira valores válidos.");
        }
    }

    private boolean isValidInput(String kg, String meter) {
        return !kg.isEmpty() && !meter.isEmpty();
    }

    private float calculateBMIIndex(float weight, float height) {
        return weight / (height * height);
    }

    private void displayBMIResult(float bmi) {
        String category;

        if (bmi < 16) {
            category = "Magreza grave (IMC < 16)";
        } else if (bmi < 17) {
            category = "Magreza moderada (IMC 16 a 16.9)";
        } else if (bmi < 18.5) {
            category = "Magreza leve (IMC 17 a 18.4)";
        } else if (bmi < 25) {
            category = "Peso normal (IMC 18.5 a 24.9)";
        } else if (bmi < 30) {
            category = "Sobrepeso (IMC 25 a 29.9)";
        } else if (bmi < 35) {
            category = "Obesidade Grau I (IMC 30 a 34.9)";
        } else if (bmi < 40) {
            category = "Obesidade Grau II (IMC 35 a 39.9)";
        } else {
            category = "Obesidade Grau III (IMC >= 40)";
        }

        textView.setText(category);
    }

}
