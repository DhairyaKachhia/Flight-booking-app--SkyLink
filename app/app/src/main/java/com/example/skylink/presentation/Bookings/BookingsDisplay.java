package com.example.skylink.presentation.Bookings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.skylink.R;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;
import java.util.List;

public class BookingsDisplay extends AppCompatActivity {

    private RecyclerView bookingsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_view);

        bookingsRecyclerView = findViewById(R.id.bookingsRecyclerView);
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        long userid = Session.getInstance().getUser_id();
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
