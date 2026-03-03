package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView message;
        Button btn;

        Button resetBtn;
        message = findViewById(R.id.introtext);
        btn = findViewById(R.id.button);
        resetBtn = findViewById(R.id.resetButton);

        Button enterBtn;
        TextView displayMessage;
        enterBtn = findViewById(R.id.enterbutton);
        displayMessage = findViewById(R.id.displaytext);

        EditText textInput;

        textInput = findViewById(R.id.enterText);

        btn.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            String mssg;
            @Override
            public void onClick(View v) {
                int index = i + 1;
                mssg = Integer.toString(index);
                message.setText(mssg);
                i = index;

                if (i > 0){
                    resetBtn.setText("Reset");
                }

                if (i > 20){
                    message.setText("jules bayot");
                }

                resetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i = 0;
                        int num = i;
                        mssg = Integer.toString(num);
                        message.setText(mssg);
                    }
                });
            }
        });

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getMessage;

                getMessage = textInput.getText().toString();

                displayMessage.setText(getMessage);

            }
        });


    }
}