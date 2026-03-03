package com.example.studentloginsystem;

import android.content.Intent;
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


    TextView tvResult, passwordResult, shoesResult, funshineResult, wingstopResult, monitorResult;
    Button logoutBtn, backBtn;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        passwordResult = findViewById(R.id.passwordResult);
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtn = findViewById(R.id.backToShop);

        shoesResult = findViewById(R.id.shoesResult);
        funshineResult = findViewById(R.id.funshineResult);
        wingstopResult = findViewById(R.id.wingstopResult);
        monitorResult = findViewById(R.id.monitorResult);


        getIntent();
        String user = getIntent().getStringExtra("user");
        String pass = getIntent().getStringExtra("pass");
        int shoe = getIntent().getIntExtra("shoe", 0);
        int funshine = getIntent().getIntExtra("funshine", 0);
        int wingstop = getIntent().getIntExtra("wingstop", 0);
        int monitor = getIntent().getIntExtra("monitor", 0);

        String shoeString = getIntent().getStringExtra("shoeTitle");
        String funshineString = getIntent().getStringExtra("funshineTitle");
        String wingstopString = getIntent().getStringExtra("wingstopTitle");
        String monitorString = getIntent().getStringExtra("monitorTitle");



        tvResult.setText("Welcome " + user);
        passwordResult.setText("Your password is " + pass);

        shoesResult.setText(shoeString +": " + shoe);
        funshineResult.setText(funshineString +": " + funshine);
        wingstopResult.setText(wingstopString +": " + wingstop);
        monitorResult.setText(monitorString +": " + monitor);




        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, productActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pass", pass);
                intent.putExtra("shoe", shoe);
                intent.putExtra("funshine", funshine);
                intent.putExtra("wingstop", wingstop);
                intent.putExtra("monitor", monitor);
                startActivity(intent);
            }
        });
    }

}