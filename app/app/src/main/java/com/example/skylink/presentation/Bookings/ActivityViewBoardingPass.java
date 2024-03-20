package com.example.skylink.presentation.Bookings;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.skylink.R;

public class ActivityViewBoardingPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_pass);

        findViewById(R.id.buttonReturn).setOnClickListener(v -> {
            Intent intent = new Intent(ActivityViewBoardingPass.this, BookingsDisplay.class);
            startActivity(intent);
            finish();
        });
    }
}
