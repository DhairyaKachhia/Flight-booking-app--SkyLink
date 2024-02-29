package com.example.skylink.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.skylink.R;
import com.example.skylink.business.Implementations.Session;
import com.example.skylink.business.Interface.ISession;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;

import java.util.ArrayList;
import java.util.List;

public class PaymentSuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);
        ISession session = Session.getInstance();
        Button buttonMainMenu = findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccessfulActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
