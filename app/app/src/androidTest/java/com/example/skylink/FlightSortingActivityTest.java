package com.example.skylink;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.presentation.FlightSearching.FlightDisplay;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.example.skylink.presentation.User_Auth.SignUpActivity;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FlightSortingActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setup() {
        // Initialize Espresso Intents before the test starts
        Intents.init();
    }

    @Test
    public void testFlightSorting() {
        String flyFrom = "Toronto - YYZ";
        String flyTo = "Hamilton - YHM";

        Espresso.onView(ViewMatchers.withId(R.id.tvSignInClick)).perform(ViewActions.click());      // click sign-up link

// --- Sign-up page

        // Verify that the expected intent was sent
        intended(hasComponent(SignUpActivity.class.getName()));

        // Type valid input values
        Espresso.onView(ViewMatchers.withId(R.id.etFullname)).perform(ViewActions.typeText("John Doe"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("john@example.com"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("password"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etRePassword)).perform(ViewActions.typeText("password"), closeSoftKeyboard());

        // Click sign up button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp)).perform(ViewActions.click());

// --- Update user profile page

        // Verify that the expected intent was sent
        intended(hasComponent(UpdateUserProfileActivity.class.getName()));

        // fill update user profile page info
        Espresso.onView(ViewMatchers.withId(R.id.etAddress)).perform(ViewActions.typeText("123 some rd."), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCity)).perform(ViewActions.typeText("Winnipeg"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etProvince)).perform(ViewActions.typeText("MB"), closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText("1234567890"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etDoB)).perform(ViewActions.typeText("12/12/2000"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etGender)).perform(ViewActions.typeText("Male"), closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btnSubmit)).perform(ViewActions.click());      // click submit button

// --- Flight search page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightSearchP.class.getName()));

        // pick YYZ from the autocomplete list
        onView(withId(R.id.autoComplete_from)).perform(click());
        onView(withText(flyFrom)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.autoComplete_from)).check(matches(withText(flyFrom)));

        // pick YHM from the autocomplete list
        onView(withId(R.id.autoComplete_to)).perform(click());
        onView(withText(flyTo)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.autoComplete_to)).check(matches(withText(flyTo)));


        // pick April 6, 2024 in date picker
        onView(withId(R.id.et_departure)).perform(click());
        // Set the date in the DatePicker
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2024, 4, 6));
        onView(withText("OK")).perform(click());        // Confirm the date selection

        // Click flight searching button
        onView(withId(R.id.searchBtn)).perform(click());

// --- Flight display page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightDisplay.class.getName()));

        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));      // check if flights are shown


        // Select Direct flight from the spinner list
        Espresso.onView(withId(R.id.sortingListOption)).perform(click());       // Perform click on the spinner
        onData(allOf(is(instanceOf(String.class)), is("Direct flight"))).perform(click());

        // checks if first flight does not have a mid airport
        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText(""))));


        // Select Earliest departure from the spinner list
        Espresso.onView(withId(R.id.sortingListOption)).perform(click());       // Perform click on the spinner
        onData(allOf(is(instanceOf(String.class)), is("Earliest departure"))).perform(click());

        // checks if first flight has 'YOW' as mid airport
        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText("YOW "))));


        // Select Lowest price from the spinner list
        Espresso.onView(withId(R.id.sortingListOption)).perform(click());       // Perform click on the spinner
        onData(allOf(is(instanceOf(String.class)), is("Lowest price"))).perform(click());

        // checks if first flight has 'YOW' as mid airport
        Espresso.onView(withId(R.id.flightListView))
                .check(matches(atPosition(0, withText("YOW "))));

    }


    // Custom matcher to match the midAirport text of the item at a specific position in the ListView
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