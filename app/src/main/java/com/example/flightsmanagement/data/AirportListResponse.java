package com.example.flightsmanagement.data;

import java.util.List;

public class AirportListResponse {
    private List<String> airports;

    public AirportListResponse(List<String> airports) {
        this.airports = airports;
    }

    public List<String> getAirports() {
        return airports;
    }

    public void setAirports(List<String> airports) {
        this.airports = airports;
    }
}
