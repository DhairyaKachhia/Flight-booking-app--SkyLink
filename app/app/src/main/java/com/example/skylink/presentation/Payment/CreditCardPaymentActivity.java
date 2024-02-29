package com.example.skylink.presentation.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.skylink.R;

import androidx.appcompat.app.AppCompatActivity;

public class CreditCardPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payment);

        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> {
            Intent intent = new Intent(CreditCardPaymentActivity.this, PaymentSuccessfulActivity.class);
            startActivity(intent);
        });
    }
}

