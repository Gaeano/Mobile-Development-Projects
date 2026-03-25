package com.usc.mutia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements FlightAdapter.OnFlightClickListener {

    private TicketModel currentSearch;
    private List<FlightModel> availableFlights;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        BottomNavigationView botNav = findViewById(R.id.bottom_navigation_layout);
        bottom_navigation.setupBottomNav(this, botNav, R.id.nav_search);
        currentSearch = (TicketModel) getIntent().getSerializableExtra("ticketModel");

        TextView headerRoute = findViewById(R.id.header_route);
        TextView headerDate = findViewById(R.id.header_date);

        String route = currentSearch.getOrigin() + " to " + currentSearch.getDestination();
        headerRoute.setText(route);
        headerDate.setText("Date: " + currentSearch.getDepartureDate() + " | Passengers: " + currentSearch.getNumPassengers());

        recyclerView = findViewById(R.id.recycler_flights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        availableFlights = generateDummyFlights();

        FlightAdapter adapter = new FlightAdapter(availableFlights, route, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFlightClick(int position) {
        FlightModel selectedFlight = availableFlights.get(position);

        Intent intent = new Intent(ScheduleActivity.this, SummaryActivity.class);
        intent.putExtra("ticketModel", currentSearch);
        intent.putExtra("flightModel", selectedFlight);
        startActivity(intent);
    }

    private List<FlightModel> generateDummyFlights() {
        List<FlightModel> flights = new ArrayList<>();
        String[] airlines = {"Cebu Pacific", "Philippine Airlines", "AirAsia"};

        for (int i = 1; i <= 15; i++) {
            flights.add(new FlightModel(
                    airlines[i % 3],
                    (8 + (i % 12)) + ":00 AM",
                    (9 + (i % 12)) + ":30 AM",
                    "G" + (i + 5),
                    "T" + ((i % 3) + 1),
                    i + "A",
                    1500f + (i * 150)
            ));
        }
        return flights;
    }
}