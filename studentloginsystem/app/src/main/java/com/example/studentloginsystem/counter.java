package com.example.studentloginsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class counter extends AppCompatActivity {

    Button btnAdd, btnMinus;
    TextView result;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_counter);
        btnAdd = findViewById(R.id.btnadd);
        btnMinus = findViewById(R.id.btnminus);
        result = findViewById(R.id.textView2);


        result.setText("0");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String countText = Integer.toString(count);
                result.setText(countText);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    result.setText("0");
                } else if (count > 0) {
                    count--;
                    String countText = Integer.toString(count);
                    result.setText(countText);
                }

            }
        });

    }
}
