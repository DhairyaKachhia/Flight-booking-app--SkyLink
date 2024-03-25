package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.skylink.presentation.FlightSearching.FlightDisplay;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.SeatSelect.Out_boundActivity;
import com.example.skylink.presentation.UserInfo.User_info;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class OutBoundActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setUp() {
//        String flyFrom = "Vancouver - YVR";
//        String flyTo = "Hamilton - YHM";
//
//        // Perform sign in
//        EspressoUtils.signIn("", "22ChancellorCircle");
//
//        // Perform flight search
//        EspressoUtils.performFlightSearch(flyFrom, flyTo, 2024, 4, 8);
//
//
//        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));      // check if flights are shown
//
//        // Click the button for economy class
//        onView(withId(R.id.econPriceBtn)).perform(click());
//
//        // Perform user info action
//        EspressoUtils.performUserInfo("Mr.", "Yiming", "Zang", "1234567890", "yiming@gmail.com");

        Intents.init();

    }

    @Test
    public void selectAvailableSeat() {
//        Hold down the button so we can get         Log.d("Out_boundActivity", "Long press on confirmButton detected. Triggering action..."); printed to log
         // Scroll to the button

        String flyFrom = "Vancouver - YVR";
        String flyTo = "Hamilton - YHM";

// --- Sign-up page

        // call a method to perform signup test with valid inputs
        EspressoUtils.signUp("John Doe", "john@example.com", "password");


// --- Update user profile page

        // Verify that the expected intent was sent
        intended(hasComponent(UpdateUserProfileActivity.class.getName()));

        // call a method to perform update user info test with valid user profile info
        EspressoUtils.updateUserInfo("123 some rd.", "Winnipeg", "MB", "1234567890", "12/12/2000", "Male");


// --- Flight search page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightSearchP.class.getName()));

        /* Search flight from YVR to YHM on April 8, 2024 */

        // call a method to perform flight searching test with valid search data.
        EspressoUtils.performFlightSearch(flyFrom, flyTo, 2024, 4, 8);


// --- Flight display page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightDisplay.class.getName()));

        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));      // check if flights are shown

        // Click the button for economy class
        onView(withId(R.id.econPriceBtn)).perform(click());

// --- User info page

        // Verify that the expected intent was sent
        intended(hasComponent(User_info.class.getName()));

        // Fill in passenger information
        EspressoUtils.performUserInfo("Mr.", "Yiming", "Zang", "1234567890", "yiming@gmail.com");

// --- Seat selection page

        // Verify that the expected intent was sent
        intended(hasComponent(Out_boundActivity.class.getName()));

        onView(withId(R.id.confirmButton)).perform(click()); // Perform long click

    }


}
