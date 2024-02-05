package com.example.skylink;

public class Flight {

    private String originAirportCode;
    private String destAirportCode;
    private String takeoffTime;
    private String landingTime;
    private String timeTaken;
    private int econPrice;
    private int busnPrice;


    public Flight() {

    }

    public Flight(String orgCode, String destCode, String takeoffTime, String landingTime, String timeTaken,
                  int econPrice, int busnPrice) {
        originAirportCode = orgCode;
        destAirportCode = destCode;
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
        this.timeTaken = timeTaken;
        this.econPrice = econPrice;
        this.busnPrice = busnPrice;
    }


    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public void setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
    }

    public String getDestAirportCode() {
        return destAirportCode;
    }

    public void setDestAirportCode(String destAirportCode) {
        this.destAirportCode = destAirportCode;
    }

    public String getTakeoffTime() {
        return takeoffTime;
    }

    public void setTakeoffTime(String takeoffTime) {
        this.takeoffTime = takeoffTime;
    }

    public String getLandingTime() {
        return landingTime;
    }

    public int getEconPrice() {
        return econPrice;
    }

    public void setEconPrice(int econPrice) {
        this.econPrice = econPrice;
    }

    public int getBusnPrice() {
        return busnPrice;
    }

    public void setBusnPrice(int busnPrice) {
        this.busnPrice = busnPrice;
    }

    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

}
