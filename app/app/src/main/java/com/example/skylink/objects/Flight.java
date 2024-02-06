package com.example.skylink.objects;

import java.io.Serializable;

public class Flight implements Serializable {
    private String flightNumber;
    private String departure_icao;
    private String arrival_icao;
    private String flight_dept_date_time;
    private String flight_arr_date_time;
    private String airCraft_Type;
    private String depature_Gate;
    private String arr_Gate;
    private int econPrice;
    private int busnPrice;

    public Flight (String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate){
        this.flightNumber = flightNumber;
        this.departure_icao =  departure_icao;
        this.arrival_icao = arrival_icao;
        this.flight_dept_date_time = flight_dept_date_time;
        this.flight_arr_date_time = flight_arr_date_time;
        this.airCraft_Type = airCraft_Type;
        this.depature_Gate = depature_Gate;
        this.arr_Gate = arr_Gate;

    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture_icao() {
        return departure_icao;
    }

    public String getArrival_icao() {
        return arrival_icao;
    }

    public String getFlight_dept_date_time() {
        return flight_dept_date_time;
    }

    public String getFlight_arr_date_time() {
        return flight_arr_date_time;
    }

    public String getAirCraft_Type() {
        return airCraft_Type;
    }

    public String getDepature_Gate() {
        return depature_Gate;
    }

    public String getArr_Gate() {
        return arr_Gate;
    }

    public int getEconPrice() {
        econPrice = 300;        // TODO
        return econPrice;
    }

    public int getBusnPrice() {
        busnPrice = 400;        // TODO
        return busnPrice;
    }

    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departure_icao='" + departure_icao + '\'' +
                ", arrival_icao='" + arrival_icao + '\'' +
                ", flight_dept_date_time='" + flight_dept_date_time + '\'' +
                ", flight_arr_date_time='" + flight_arr_date_time + '\'' +
                ", airCraft_Type='" + airCraft_Type + '\'' +
                ", depature_Gate='" + depature_Gate + '\'' +
                ", arr_Gate='" + arr_Gate + '\'' +
                '}';
    }
}
