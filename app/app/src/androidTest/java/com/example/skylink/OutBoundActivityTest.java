package com.example.skylink;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class OutBoundActivityTest {

    private View decorView;

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setUp() {
        String flyFrom = "Vancouver - YVR";
        String flyTo = "Hamilton - YHM";

        // Perform sign in
        EspressoUtils.signIn("john@example.com", "password");

        // Wait for some time to ensure sign-in is complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        intended(hasComponent(FlightSearchP.class.getName()));
        EspressoUtils.performFlightSearch(flyFrom, flyTo, 2024, 4, 8);

//        intended(hasComponent(FlightDisplay.class.getName()));
        onView(withId(R.id.econPriceBtn)).perform(click());

//        intended(hasComponent(User_info.class.getName()));
        EspressoUtils.performUserInfo("Mr.", "Yiming", "Zang", "1234567890", "yiming@gmail.com");

        // Get the decor view of the OutBoundActivity
        ActivityScenario<Out_boundActivity> scenario = ActivityScenario.launch(Out_boundActivity.class);
        scenario.onActivity(new ActivityScenario.ActivityAction<Out_boundActivity>() {
            @Override
            public void perform(Out_boundActivity activity) {
                // Get the decor view of the activity
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void selectSeat_ClickUntilToast() {
        // Perform clicking on various parts of the screen until the toast appears
        while (true) {
            // Click on the screen multiple times to increase chances of clicking on a seat
            for (int i = 0; i < 10; i++) {
                Espresso.onView(isRoot()).perform(ViewActions.click());
            }

            // Check if the toast appears
            try {
                Espresso.onView(withText("Passenger already has a selected seat"))
                        .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                break; // If toast appears, break the loop
            } catch (Exception e) {
                // If toast not found, continue clicking
            }
        }

        // Wait for a short duration for stability
        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the confirm button to proceed
        Espresso.onView(withId(R.id.confirmButton)).perform(click());
    }
}
