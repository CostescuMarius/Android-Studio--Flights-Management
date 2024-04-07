package com.example.flightsmanagement.util;

import com.example.flightsmanagement.data.Ticket;

import java.util.Date;

public class TicketInfo {
    public static String generateTicketInfo(Ticket ticket) {
        Date departureDate = new Date(Long.parseLong(ticket.getDepartureTime()));
        Date arrivalDate = new Date(Long.parseLong(ticket.getArrivalTime()));

        return "From: " + ticket.getDepartureAirport() + "\n" +
                "To: " + ticket.getArrivalAirport() + "\n" +
                "Data: " + TimeFormat.onlyDateFormat.format(departureDate)  + "\n" +
                "Interval: " + TimeFormat.intervalFormat.format(departureDate) + " <-> " + TimeFormat.intervalFormat.format(arrivalDate) + "\n" +
                "Price: " + ticket.getPrice() + "\n" +
                "Type: " + ticket.getType();
    }
}
