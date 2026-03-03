package com.example.doityourselftextvieweditbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etName, etDept, etStudentId;
    Button btnDisplay;
    TextView tvResult, resultTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.etName);
        etDept = findViewById(R.id.etDept);
        etStudentId = findViewById(R.id.etStudentId);
        btnDisplay = findViewById(R.id.btnDisplay);
        tvResult = findViewById(R.id.tvResult);
        resultTitle = findViewById(R.id.resultTitle);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String dept = etDept.getText().toString();
                String id = etStudentId.getText().toString();

                int studID = Integer.parseInt(id);

                Student currentStudent = new Student(name, dept, studID);


                if (currentStudent.name.isEmpty() || currentStudent.department.isEmpty() || currentStudent.studId == 0){
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    String result = "Name: " + currentStudent.name + "\n" +
                            "Department: " + currentStudent.department + "\n" +
                            "Student ID: " + currentStudent.studId;

                    resultTitle.setText("Current Student:\n");
                    tvResult.setText(result);
                    etName.getText().clear();;
                    etDept.getText().clear();
                    etStudentId.getText().clear();

                    etName.requestFocus();

                    Toast.makeText(MainActivity.this, "Student Information Displayed", Toast.LENGTH_SHORT).show();


                }






            }
        });







    }
}