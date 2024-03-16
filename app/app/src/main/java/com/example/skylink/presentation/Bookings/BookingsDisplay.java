package com.example.skylink.presentation.Bookings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.R;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.persistence.Interfaces.IUserDB;
import com.example.skylink.persistence.Interfaces.iBookingDB;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;
import com.example.skylink.presentation.Session;

import java.util.List;

public class BookingsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_view);

        IUserProperties user = Services.getUserDatabase().getUserByEmail(Session.getInstance().getEmail());

        long userid = Session.getInstance().getUser_id();

        FlightBookingHandler flightBookingHandler = new FlightBookingHandler(true);

        List<iFlightInfo> bookings = flightBookingHandler.getBookingDetails(userid);

        System.out.println(bookings);

    }
}
