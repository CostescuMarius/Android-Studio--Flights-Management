package com.example.flightsmanagement;

import java.io.Serializable;
import java.sql.Date;

public class Ticket implements Serializable {
    private String departureAirport;
    private String departureTime;
    private String arrivalAirport;
    private String arrivalTime;
    private Date date;
    private double price;
    private String type;

    public Ticket() {
        this.departureAirport = "-";
        this.departureTime = "00:00";
        this.arrivalAirport = "-";
        this.arrivalTime = "00:00";
        this.date = new Date(0);
        this.price = 0.0f;
        this.type = "-";
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
