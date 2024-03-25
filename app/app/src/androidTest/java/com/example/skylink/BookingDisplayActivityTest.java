package com.example.skylink;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;

import com.example.skylink.presentation.Bookings.BookingsDisplay;
import com.example.skylink.presentation.FlightSearching.FlightDisplay;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

@RunWith(AndroidJUnit4.class)
public class BookingDisplayActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }

    @Test
    public void testBookingDisplay() {

        // 1. Create a new account
        String[] userInfo = UserInfoGenerator.generateUserInfo();
        EspressoUtils.signUp(userInfo[0], userInfo[1], userInfo[2]);
        EspressoUtils.verifyActivity(UpdateUserProfileActivity.class);
        EspressoUtils.updateUserInfo(userInfo[3], userInfo[4], userInfo[5], userInfo[6], userInfo[7], userInfo[8]);
        EspressoUtils.clickLogOut();
        EspressoUtils.signIn(userInfo[1],userInfo[2]);
        EspressoUtils.verifyActivity(FlightSearchP.class);

        // 2. Search for a flight
        String fromLocation = "Toronto - YYZ";
        String toLocation = "Hamilton - YHM";
        int year = 2024;
        int monthOfYear = 4;
        int dayOfMonth = 6;

        /* Search flight from YYZ to YHM on April 6, 2024 */
        EspressoUtils.performFlightSearch(fromLocation, toLocation, year, monthOfYear, dayOfMonth);
        intended(hasComponent(FlightDisplay.class.getName()));
        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.sortingListOption)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Direct flight"))).perform(click());

        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText(""))));

        Espresso.onView(withId(R.id.sortingListOption)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Earliest departure"))).perform(click());

        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText("YOW "))));

        Espresso.onView(withId(R.id.sortingListOption)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Lowest price"))).perform(click());

        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText("YOW "))));

        onView(withId(R.id.econPriceBtn)).perform(click());


        // Book a flight

        // Select Seats

        // Add Passenger Info

        // Pay

        // Go to Bookings Display Screen

        // View Flight

    }

    public static Matcher<View> atPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, ListView>(ListView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(ListView listView) {
                // Check if the ListView contains an item at the given position
                int itemCount = listView.getAdapter().getCount();
                if (position < 0 || position >= itemCount) {
                    return false;
                }

                // Get the view for the item at the given position
                View itemView = listView.getChildAt(position - listView.getFirstVisiblePosition());
                if (itemView == null) {
                    return false;
                }

                TextView midAirport = itemView.findViewById(R.id.midCodeTV);

                // Check if the midAirport in the item matches the given matcher
                return itemMatcher.matches(midAirport);
            }
        };
    }

}
