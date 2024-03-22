package com.example.skylink.presentation.Bookings;

import static com.example.skylink.R.*;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.R;
import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;
import java.util.List;

public class BookingsDisplay extends AppCompatActivity {

    private RecyclerView bookingsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.bookings_view);

        bookingsRecyclerView = findViewById(id.bookingsRecyclerView);
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        long userid = Session.getInstance().getUserProperties().getUser_id();
        FlightBookingHandler flightBookingHandler = new FlightBookingHandler(true);
        List<iFlightInfo> bookings = flightBookingHandler.getBookingDetails(userid);

        if (bookings != null && !bookings.isEmpty()) {
            BookingsAdapter bookingsAdapter = new BookingsAdapter(bookings);
            bookingsRecyclerView.setAdapter(bookingsAdapter); // Set the adapter to the RecyclerView
        } else {
            System.out.println("No bookings found");
        }

    }
}
