package com.example.studentloginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.AlarmClock;
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

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;


public class login extends AppCompatActivity {


    EditText etUsername;
    EditText etPassword;
    Button loginBtn, clearBtn;
    TextView attemptRemaining, loginDiss, loginTimestamp;
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
        loginTimestamp = findViewById(R.id.loginAttemptTimestamp);



        String attemptsText = "Attempts remaining : " + (attempt);
        attemptRemaining.setText(attemptsText);





        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();


                if (user.equals("admin") && pass.equals("admin")) {
                    Snackbar.make(v, "Login Successful", Snackbar.LENGTH_LONG).show();

                    Intent intent = new Intent(login.this, Profile.class);
                    intent.putExtra("user", user);
                    intent.putExtra("pass", pass);
                    user = "";
                    pass = "";
                    startActivity(intent);
                }  else {
                    attempt--;
                    attemptRemaining.setText("Attempts remaining : " + (attempt));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String date = formatter.format(new java.util.Date());

                    loginTimestamp.setText("Last Attempt: " + date);


                    if (attempt <= 0){
                        loginBtn.setEnabled(false);
                        Snackbar.make(v, "Login Disabled", Snackbar.LENGTH_SHORT).show();


                        new CountDownTimer(30000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                loginDiss.setText("Login Disabled for " + millisUntilFinished / 1000 + " seconds");
                            }

                            public void onFinish() {
                                loginDiss.setText("");
                                loginBtn.setEnabled(true);
                                attempt = 3;
                                loginDiss.setText("");
                                attemptRemaining.setText("Attempts remaining : " + (attempt));
                                Snackbar.make(v, "Login Enabled", Snackbar.LENGTH_SHORT).show();
                            }
                        }.start();


                    } else {
                        Snackbar.make(v, "Login Failed. Attempts remaining: " + (attempt), Snackbar.LENGTH_SHORT).show();
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