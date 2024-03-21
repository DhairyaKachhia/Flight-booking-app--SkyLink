package com.example.skylink;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.widget.DatePicker;

import com.example.skylink.objects.Implementations.City;
import com.example.skylink.persistence.Implementations.hsqldb.FlightHSQLDB;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;
//Please add YUL to YYC to the flight database.
@RunWith(AndroidJUnit4.class)
public class FlightSearchActivityTest {

    @Rule
    public ActivityScenarioRule<FlightSearchP> activityScenarioRule = new ActivityScenarioRule<>(FlightSearchP.class);


    @Test
    public void completeWorkflow() {
// Search flights from Montreal to Brampton

        onView(withId(R.id.autoComplete_from)).perform(click());

        onView(withId(R.id.autoComplete_to)).perform(click());


        onView(withId(R.id.autoComplete_to)).perform(click());

        onView(withId(R.id.swapBtn)).perform(click());


        // Click the departure date EditText
        onView(withId(R.id.et_departure)).perform(click());
        // Set the date in the DatePicker
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2024, 4, 10));
        // Confirm the date selection
        onView(withText("OK")).perform(click());


        // Increase passenger number
        onView(withId(R.id.btn_increment)).perform(click());

        onView(withId(R.id.btn_decrement)).perform(click());

        onView(withId(R.id.swapBtn));

        onView(withId(R.id.swapBtn));

        // Click flight searching button
        onView(withId(R.id.searchBtn)).perform(click());

        // Click the button for economy class
        onView(withId(R.id.econPriceBtn)).perform(click());

        Espresso.pressBack();

        onView(withId(R.id.searchBtn));
        // Click the button for business class
        onView(withId(R.id.busnPriceBtn)).perform(click());

    }
}
