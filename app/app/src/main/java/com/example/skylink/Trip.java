package com.example.skylink;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Trip implements Parcelable {

    private ArrayList<Flight> outbound;
    private ArrayList<Flight> inbound;

    private String flyingFrom;
    private String flyingTo;
    private String departureDate;
    private String returnDate;

    private int passengerCount;

    private boolean isOneway;

    public String getFlyingFrom() {
        return flyingFrom;
    }

    public void setFlyingFrom(String flyingFrom) {
        this.flyingFrom = flyingFrom;
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public void setFlyingTo(String flyingTo) {
        this.flyingTo = flyingTo;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public boolean isOneway() {
        return isOneway;
    }

    public void setOneway(boolean oneway) {
        isOneway = oneway;
    }

    public Trip () {

    }

    public Trip (ArrayList<Flight> outbound, ArrayList<Flight> inbound) {
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public Trip (String flyingFrom, String flyingTo, String departureDate, String returnDate, int passengerCount, boolean isOneway) {
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.passengerCount = passengerCount;
        this.isOneway = isOneway;

    }

    // Implement the Parcelable interface
    protected Trip(Parcel in) {
        flyingFrom = in.readString();
        flyingTo = in.readString();
        departureDate = in.readString();
        returnDate = in.readString();
        passengerCount = in.readInt();
        isOneway = in.readByte() != 0; // in.readByte() will return 0 for false
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flyingFrom);
        dest.writeString(flyingTo);
        dest.writeString(departureDate);
        dest.writeString(returnDate);
        dest.writeInt(passengerCount);
        dest.writeByte((byte) (isOneway ? 1 : 0)); // 1 for true, 0 for false
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };


    public ArrayList<Flight> getOutbound() {
        return outbound;
    }

    public void setOutbound(ArrayList<Flight> outbound) {
        this.outbound = outbound;
    }

    public ArrayList<Flight> getInbound() {
        return inbound;
    }

    public void setInbound(ArrayList<Flight> inbound) {
        this.inbound = inbound;
    }
}
