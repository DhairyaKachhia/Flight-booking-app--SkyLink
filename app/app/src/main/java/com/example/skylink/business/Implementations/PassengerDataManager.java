package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iPassengerDataManager;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Implementations.hsqldb.BookingStub;
import com.example.skylink.objects.Implementations.PassengerData;
import com.example.skylink.persistence.Interfaces.iBookingDB;

public class PassengerDataManager implements iPassengerDataManager {
    private iBookingDB bookingDatabase;

    public PassengerDataManager() {
        bookingDatabase = Services.getBookDatabase();
    }

    public iPassengerData addBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        iPassengerData newPassengerData = new PassengerData(title, firstName, lastName, telephoneNumber, emailAddress);
        bookingDatabase.addBooking(newPassengerData);

        return newPassengerData;
    }

    public boolean findBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        iPassengerData searchPassengerData = new PassengerData(title, firstName, lastName, telephoneNumber, emailAddress);
            if (bookingDatabase.findBooking(searchPassengerData)){
                return true;
            }
        return false;
    }
}
