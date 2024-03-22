package com.example.skylink.presentation.Bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.bookings_view);

        bookingsRecyclerView = findViewById(R.id.bookingsRecyclerView);
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        long userid = Session.getInstance().getUser_id();
        FlightBookingHandler flightBookingHandler = new FlightBookingHandler(true);
        List<iFlightInfo> bookings = flightBookingHandler.getBookingDetails(userid);

        if (bookings != null && !bookings.isEmpty()) {
            BookingsAdapter bookingsAdapter = new BookingsAdapter(bookings);
            bookingsRecyclerView.setAdapter(bookingsAdapter);
        } else {
            System.out.println("No bookings found");
        }

        Button viewBoardingPassButton = findViewById(R.id.viewBoardingPassButton);
        viewBoardingPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingsDisplay.this, ActivityViewBoardingPass.class);
                startActivity(intent);
            }
        });

        if (Session.getInstance().isCheckedIn()) {
            viewBoardingPassButton.setText("Checked In");
            viewBoardingPassButton.setEnabled(false);
        }
    }
}
