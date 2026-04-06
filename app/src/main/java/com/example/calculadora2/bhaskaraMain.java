package com.example.calculadora2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bhaskaraMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bhaskara_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }

    public void returnMain(View v) {
        finish();
    }

    EditText inputA = findViewById(R.id.valA);
    EditText inputB = findViewById(R.id.valB);
    EditText inputC = findViewById(R.id.valC);

    TextView textDelta = findViewById(R.id.delta);
    TextView textRoot1 = findViewById(R.id.root1);
    TextView textRoot2 = findViewById(R.id.root2);
    public void calcBh(View v) {
        String strA = inputA.getText().toString();
        String strB = inputB.getText().toString();
        String strC = inputC.getText().toString();

        if (strA.isEmpty() || strB.isEmpty() || strC.isEmpty()) {
            textDelta.setText("Preencha todos os campos!");
            return;
        }

        double valA = Double.parseDouble(strA);
        double valB = Double.parseDouble(strB);
        double valC = Double.parseDouble(strC);


    }
}