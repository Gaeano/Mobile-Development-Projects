package com.usc.mutia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SummaryActivity extends AppCompatActivity {

    private TicketModel searchData;
    private FlightModel flightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        BottomNavigationView botNav = findViewById(R.id.bottom_navigation_layout);
        bottom_navigation.setupBottomNav(this, botNav, R.id.nav_library);

        searchData = (TicketModel) getIntent().getSerializableExtra("ticketModel");
        flightData = (FlightModel) getIntent().getSerializableExtra("flightModel");

        if (searchData != null && flightData != null) {
            TextView route = findViewById(R.id.bp_route);
            TextView seat = findViewById(R.id.bp_seat);
            TextView terminal = findViewById(R.id.bp_terminal);
            TextView gate = findViewById(R.id.bp_gate);
            TextView depTime = findViewById(R.id.bp_dep_time);
            TextView arrTime = findViewById(R.id.bp_arr_time);
            TextView cost = findViewById(R.id.bp_cost);
            Button btnDownload = findViewById(R.id.btn_download);

            route.setText(searchData.getOrigin() + " ➔ " + searchData.getDestination());
            seat.setText(flightData.getSeat());
            terminal.setText(flightData.getTerminal());
            gate.setText(flightData.getGate());
            depTime.setText(flightData.getDepartureTime());
            arrTime.setText(flightData.getArrivalTime());

            float finalPrice = flightData.getBasePrice() * searchData.getNumPassengers();
            cost.setText("Final Cost: PHP " + finalPrice);

            btnDownload.setOnClickListener(v -> {
                String message = "Ticket for " + searchData.getOrigin() + " to " + searchData.getDestination() + " saved!";
                Toast.makeText(SummaryActivity.this, message, Toast.LENGTH_LONG).show();
            });
        }
    }
}