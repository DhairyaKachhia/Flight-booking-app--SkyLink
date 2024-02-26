package com.example.skylink.business;

import com.example.skylink.persistence.BookingDatabase;
import com.example.skylink.objects.PassengerData;

public class PassengerDataManager {
    private BookingDatabase bookingDatabase;

    public PassengerDataManager() {
        this.bookingDatabase = new BookingDatabase();
    }

    public PassengerData addBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        PassengerData newPassengerData = new PassengerData(title, firstName, lastName, telephoneNumber, emailAddress);
        bookingDatabase.addBooking(newPassengerData);

        return newPassengerData;
    }

    public boolean findBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        PassengerData searchPassengerData = new PassengerData(title, firstName, lastName, telephoneNumber, emailAddress);
            if (bookingDatabase.findBooking(searchPassengerData)){
                return true;
            }
        return false;
    }
}
