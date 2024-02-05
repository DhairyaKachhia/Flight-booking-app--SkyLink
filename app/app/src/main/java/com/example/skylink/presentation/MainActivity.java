package com.example.skylink.presentation;
import com.example.skylink.data.DatabaseManager;
import  com.example.skylink.business.FetchAirport;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.R;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private SQLiteDatabase profileDatabase;

    private RadioGroup radioGroupTripType;
    private String tripType;

    AutoCompleteTextView autoCompleteFrom;
    AutoCompleteTextView autoCompleteTo;

    ArrayAdapter<String> adapterItemsFrom;
    ArrayAdapter<String> adapterItemsTo;
    String[] cities = {
            "Toronto",
            "Montreal",
            "Calgary",
            "Ottawa",
            "Edmonton",
            "Mississauga",
            "Winnipeg",
            "Vancouver",
            "Brampton",
            "Hamilton"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteFrom = findViewById(R.id.autoComplete_from);
        autoCompleteTo = findViewById(R.id.autoComplete_to);
        radioGroupTripType = findViewById(R.id.radioGroupTripType);

        // Check if a RadioButton is initially selected
        int selectedTripTypeId = radioGroupTripType.getCheckedRadioButtonId();
        if (selectedTripTypeId != -1) { // -1 means no id is selected
            RadioButton selectedTripType = findViewById(selectedTripTypeId);
            tripType = selectedTripType.getText().toString();
        }

        // Initialize adapters for AutoCompleteTextViews
        ArrayAdapter<String> adapterItemsFrom = new ArrayAdapter<>(this, R.layout.list_item, cities);
        ArrayAdapter<String> adapterItemsTo = new ArrayAdapter<>(this, R.layout.list_item, cities);
        autoCompleteFrom.setAdapter(adapterItemsFrom);
        autoCompleteTo.setAdapter(adapterItemsTo);

        // Set an OnCheckedChangeListener to the radio group to listen for changes
        radioGroupTripType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle the change
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    tripType = radioButton.getText().toString();
                }
            }
        });
    }

    private void retrieveLocations() {
        String fromLocation = autoCompleteFrom.getText().toString();
        String toLocation = autoCompleteTo.getText().toString();
    }

    @Override
    protected void onDestroy() {
        // Close the database when the activity is destroyed
        if (profileDatabase != null) {
            profileDatabase.close();
        }
        super.onDestroy();
    }
}
