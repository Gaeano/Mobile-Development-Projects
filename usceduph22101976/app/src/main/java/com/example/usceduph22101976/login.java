package com.example.usceduph22101976;

import android.content.Intent;
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

public class login extends AppCompatActivity {


    EditText etUsername;
    EditText etPassword;
    Button loginBtn, clearBtn;
    TextView attemptRemaining, loginDiss;
    int attempt = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.loginBtn);
        clearBtn = findViewById(R.id.clearBtn);
        attemptRemaining = findViewById(R.id.attemptRemaining);
        loginDiss = findViewById(R.id.loginDiss);



        String attemptsText = "Attempts remaining : " + (attempt);
        attemptRemaining.setText(attemptsText);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();

                if (user.equals("admin") && pass.equals("admin")) {
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    user = "";
                    pass = "";

                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }  else {
                    attempt--;
                    attemptRemaining.setText("Attempts remaining : " + (attempt));

                    if (attempt <= 0){
                        loginBtn.setEnabled(false);
                        Toast.makeText(login.this, "Too many attempts. Login Disabled", Toast.LENGTH_SHORT).show();
                        loginDiss.setText("Login Disabled");

                    } else {
                        Toast.makeText(login.this, "Login Failed. Attempts remaining : " + (attempt), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.getText().clear();
                etPassword.getText().clear();

                etUsername.requestFocus();
            }
        });
    }
}