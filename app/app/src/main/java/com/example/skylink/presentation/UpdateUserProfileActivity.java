package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.skylink.R;

public class UpdateUserProfileActivity extends AppCompatActivity {

    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        submit = findViewById(R.id.btnSubmit);


        submit.setOnClickListener(v -> {

            Intent intent = new Intent(UpdateUserProfileActivity.this, MainActivity.class);
            startActivity(intent);

        });
    }
}