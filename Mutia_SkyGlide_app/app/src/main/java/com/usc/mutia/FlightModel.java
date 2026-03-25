package com.usc.mutia;

import java.io.Serializable;

public class FlightModel implements Serializable {
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String gate;
    private String terminal;
    private String seat;
    private float basePrice;

    public FlightModel(String airline, String departureTime, String arrivalTime, String gate, String terminal, String seat, float basePrice) {
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.gate = gate;
        this.terminal = terminal;
        this.seat = seat;
        this.basePrice = basePrice;
    }

    public String getAirline() { return airline; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getGate() { return gate; }
    public String getTerminal() { return terminal; }
    public String getSeat() { return seat; }
    public float getBasePrice() { return basePrice; }
}