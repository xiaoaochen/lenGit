package com.wbhz.entity;

import java.util.Date;

public class FlightTable {
    private int id;
    private String flightNumber;
    private Date takeOffTime;
    private int flyingTime;
    private String startPlace;
    private String endPlace;
    private int tickets;
    private Double price;
    private int alltickets;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public int getFlyingTime() {
        return flyingTime;
    }

    public void setFlyingTime(int flyingTime) {
        this.flyingTime = flyingTime;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAlltickets() {
        return alltickets;
    }

    public void setAlltickets(int alltickets) {
        this. alltickets = alltickets;
    }

    @Override
    public String toString() {
        return "FlightTable{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", takeOffTime=" + takeOffTime +
                ", flyingTime=" + flyingTime +
                ", startPlace='" + startPlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", tickets=" + tickets +
                ", price=" + price +
                ", alltickets=" + alltickets +
                ", state='" + state + '\'' +
                '}';
    }
}
