package com.example.flightsmanagement.data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Ticket implements Serializable {
    private int ticketId;
    private String name;
    private String departureAirportName;

    private String departureLocation;

    private String arrivalAirportName;

    private String arrivalLocation;

    private Timestamp departureDate;

    private Timestamp arrivalDate;

    private String type;

    private double price;

    private int stock;

    public Ticket(int ticketId, String departureAirportName, String departureLocation,
                  String arrivalAirportName, String arrivalLocation,
                  Timestamp departureDate, Timestamp arrivalDate,
                  String type, double price, int stock) {
        this.ticketId = ticketId;
        this.departureAirportName = departureAirportName;
        this.departureLocation = departureLocation;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public Ticket(int ticketId, String name, String departureAirportName, String departureLocation,
                  String arrivalAirportName, String arrivalLocation,
                  Timestamp departureDate, Timestamp arrivalDate,
                  String type, double price, int stock) {
        this.ticketId = ticketId;
        this.name = name;
        this.departureAirportName = departureAirportName;
        this.departureLocation = departureLocation;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public String getDepartureAirport() {
        return departureAirportName;
    }

    public String getDepartureTime() {
        return "" + departureDate.getTime();
    }

    public String getArrivalAirport() {
        return arrivalAirportName;
    }

    public String getArrivalTime() {
        return "" + arrivalDate.getTime();
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketId() {
        return ticketId;
    }
}
