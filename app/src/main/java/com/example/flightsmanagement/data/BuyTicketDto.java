package com.example.flightsmanagement.data;

public class BuyTicketDto {
    private String userEmail;

    private int ticketId;

    private String name;

    public BuyTicketDto(String userEmail, int ticketId, String name) {
        this.userEmail = userEmail;
        this.ticketId = ticketId;
        this.name = name;
    }
}
