package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.skylink.presentation.User_Auth.SignInActivity;

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
        String flyFrom = "Vancouver - YVR";
        String flyTo = "Hamilton - YHM";

        // Perform sign in
        EspressoUtils.signIn("akintundemayokun@gmail.com", "22ChancellorCircle");

        // Perform flight search
        EspressoUtils.performFlightSearch(flyFrom, flyTo, 2024, 4, 8);


        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));      // check if flights are shown

        // Click the button for economy class
        onView(withId(R.id.econPriceBtn)).perform(click());

        // Perform user info action
        EspressoUtils.performUserInfo("Mr.", "Yiming", "Zang", "1234567890", "yiming@gmail.com");
    }

    @Test
    public void selectAvailableSeat() {
//        Hold down the button so we can get         Log.d("Out_boundActivity", "Long press on confirmButton detected. Triggering action..."); printed to log
         // Scroll to the button
        onView(withId(R.id.confirmButton)).perform(click()); // Perform long click

    }


}
