package com.example.flightsmanagement.data;

public class CheckTicketDto {
    private String departureAirportName;

    private String arrivalAirportName;

    private String departureDate;

    public CheckTicketDto(String departureAirportName, String arrivalAirportName, String departureDate) {
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "CheckTicketDto{" +
                "departureAirportName='" + departureAirportName + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
