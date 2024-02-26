package com.example.skylink.persistence;

import com.example.skylink.objects.PassengerData;

import java.util.ArrayList;
import java.util.List;
public class BookingDatabase {
    private final List<PassengerData> passengerData;

    public BookingDatabase(){
        this.passengerData = new ArrayList<>();
    }

    public void addBooking(PassengerData passengerData){
        this.passengerData.add(passengerData);
    }

    public boolean findBooking(PassengerData searchPassengerData) {
        PassengerData result = null;
        for (PassengerData b : passengerData) {
            if (b.getTitle().equals(searchPassengerData.getTitle()) &&
                    b.getFirstName().equals(searchPassengerData.getFirstName()) &&
                    b.getLastName().equals(searchPassengerData.getLastName()) &&
                    b.getTelephoneNumber().equals(searchPassengerData.getTelephoneNumber()) &&
                    b.getEmailAddress().equals(searchPassengerData.getEmailAddress())) {
                return  true;
            }
        }
        return false;
    }

}
