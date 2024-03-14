package com.example.skylink.objects.Implementations;

import com.example.skylink.objects.Interfaces.iBookingInfo;

public class BookingInfo implements iBookingInfo {
    private long id;
    private String bookingNumber;
    private String econBus;
    private String direction;
    public BookingInfo(long id, String flightID, String econBus, String direction) {
        this.id = id;
        this.bookingNumber = flightID;
        this.econBus = econBus;
        this.direction = direction;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @Override
    public String getEconBus() {
        return econBus;
    }

    @Override
    public void setEconBus(String econBus) {
        this.econBus = econBus;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public void setDirection(String direction) {
        this.direction = direction;
    }
}