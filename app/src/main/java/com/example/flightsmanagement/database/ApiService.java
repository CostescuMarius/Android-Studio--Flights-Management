package com.example.flightsmanagement.database;

import com.example.flightsmanagement.data.AirportListResponse;
import com.example.flightsmanagement.data.CheckTicketDto;
import com.example.flightsmanagement.data.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/api/airports/names")
    Call<AirportListResponse> getAllAirportNames();

    @POST("/api/tickets/check")
    Call<List<Ticket>> checkAvailableTickets(@Body CheckTicketDto checkTicketDto);
}
