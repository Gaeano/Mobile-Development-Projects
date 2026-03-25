package com.usc.mutia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner fromSpinner;
    private Spinner toSpinner;

    private EditText departureDate, returnDate;
    private Button minusBtn, plusBtn, searchBtn;

    private ArrayList<String> airportLists = new ArrayList<>();

    private TextView displayNumPassengers;
    private int currentNumPassengers = 1;

    TicketModel ticketModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // View Binding
        fromSpinner = findViewById(R.id.spinner_from);
        toSpinner = findViewById(R.id.spinner_to);
        departureDate = findViewById(R.id.id_departure_date);
        returnDate = findViewById(R.id.id_return_date);
        minusBtn = findViewById(R.id.minus_passenger);
        plusBtn = findViewById(R.id.add_passenger);
        searchBtn = findViewById(R.id.search_btn);
        displayNumPassengers = findViewById(R.id.display_result);

        // Bottom Nav setup
        BottomNavigationView botNav = findViewById(R.id.bottom_navigation_layout);
        bottom_navigation.setupBottomNav(this, botNav, R.id.nav_home);

        // Initialize Model
        ticketModel = new TicketModel();

        // Setup Spinners
        populateAirports();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, airportLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Listeners
        setOnClickListenersToSpinner(fromSpinner, toSpinner);
        passengerCounter(plusBtn, minusBtn, displayNumPassengers);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depDate = departureDate.getText().toString().trim();
                String retDate = returnDate.getText().toString().trim();

                if (depDate.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a departure date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ticketModel.getOrigin().equals(ticketModel.getDestination())) {
                    Toast.makeText(MainActivity.this, "Origin and Destination cannot be the same", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save data to model
                ticketModel.setDepartureDate(depDate);
                ticketModel.setReturnDate(retDate);
                ticketModel.setNumPassengers(currentNumPassengers);

                // Start Next Activity
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                intent.putExtra("ticketModel", ticketModel);
                startActivity(intent);

                Log.d("SearchHome", "Searching: " + ticketModel.getOrigin() + " to " + ticketModel.getDestination());
            }
        });
    }

    private void populateAirports(){
        airportLists.add("Cebu (CEB) - Mactan-Cebu Int'l");
        airportLists.add("Manila (MNL) - Ninoy Aquino Int'l");
        airportLists.add("Davao (DVO) - Francisco Bangoy Int'l");
        airportLists.add("Bohol/Panglao (TAG) - Bohol-Panglao Int'l");
        airportLists.add("Boracay/Caticlan (MPH) - Godofredo P. Ramos");
        airportLists.add("Iloilo (ILO) - Iloilo Int'l");
    }

    private void setOnClickListenersToSpinner(Spinner fromSpinner, Spinner toSpinner){
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ticketModel.setOrigin(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ticketModel.setDestination(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void passengerCounter(Button addBtn, Button minusBtn, TextView displayNumPassengers) {
        // Set initial display to 1
        displayNumPassengers.setText(String.valueOf(currentNumPassengers));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumPassengers++;
                displayNumPassengers.setText(String.valueOf(currentNumPassengers));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BUG FIX: Cannot have less than 1 passenger
                if (currentNumPassengers > 1){
                    currentNumPassengers--;
                    displayNumPassengers.setText(String.valueOf(currentNumPassengers));
                } else {
                    Toast.makeText(MainActivity.this, "Minimum 1 passenger", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}