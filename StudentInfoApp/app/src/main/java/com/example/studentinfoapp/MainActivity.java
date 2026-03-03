package com.example.studentinfoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCourse, etAddress, etYear;
    Button btnDisplay;
    TextView tvResult, resultTitle;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        etAddress = findViewById(R.id.etAddress);
        btnDisplay = findViewById(R.id.btnDisplay);
        tvResult = findViewById(R.id.tvResult);
        etYear = findViewById(R.id.etYear);
        resultTitle = findViewById(R.id.resultTitle);




        btnDisplay.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {



                String name = etName.getText().toString();
                String course = etCourse.getText().toString();
                String year = etYear.getText().toString();
                String address = etAddress.getText().toString();



                if (name.isEmpty() || course.isEmpty() || year.isEmpty() || address.isEmpty()) {
                    tvResult.setText("");
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                } else {

                    String result = "Name: " + name + "\n"
                            + "Course: " + course + "\n"
                            + "Year: " + year + "\n"
                            + "Address: " + address;

                    etName.getText().clear();
                    etCourse.getText().clear();
                    etYear.getText().clear();
                    etAddress.getText().clear();
                    resultTitle.setText("Student Information");
                    tvResult.setText(result);


                    Toast.makeText(MainActivity.this, "Information has been reflected", Toast.LENGTH_LONG).show();
                    etName.requestFocus();
                }



            }
        });

    }
}