package com.example.skylink.objects.Implementations;

import com.example.skylink.objects.Interfaces.iBookingInfo;

public class BookingInfo implements iBookingInfo {
    private long id;
    private String bookingNumber;
    private String econBus;
    private String direction;
    private int bagCount, petCount, wifiOption, wheelchairOption;

    public BookingInfo(long id, String flightID, String econBus, String direction, int bagCount, int petCount, int wifiOption, int wheelchairOption) {
        this.id = id;
        this.bookingNumber = flightID;
        this.econBus = econBus;
        this.direction = direction;
        this.bagCount = bagCount;
        this.petCount = petCount;
        this.wifiOption = wifiOption;
        this.wheelchairOption = wheelchairOption;
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

    public int getBagCount() {
        return bagCount;
    }

    public void setBagCount(int bagCount) {
        this.bagCount = bagCount;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }

    public int getWifiOption() {
        return wifiOption;
    }

    public void setWifiOption(int wifiOption) {
        this.wifiOption = wifiOption;
    }

    public int getWheelchairOption() {
        return wheelchairOption;
    }

    public void setWheelchairOption(int wheelchairOption) {
        this.wheelchairOption = wheelchairOption;
    }
}