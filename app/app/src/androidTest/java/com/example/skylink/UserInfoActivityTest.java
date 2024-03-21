package com.example.skylink;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.skylink.presentation.FlightSearching.FlightDisplay;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.SeatSelect.Out_boundActivity;
import com.example.skylink.presentation.UserInfo.User_info;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.example.skylink.presentation.User_Auth.SignUpActivity;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

@RunWith(AndroidJUnit4.class)
public class UserInfoActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setup() {
        // Initialize Espresso Intents before the test starts
        Intents.init();
    }

    @Test
    public void testUserInfo() {
        String flyFrom = "Vancouver - YVR";
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
                .perform(PickerActions.setDate(2024, 4, 8));
        onView(withText("OK")).perform(click());        // Confirm the date selection

        // Click flight searching button
        onView(withId(R.id.searchBtn)).perform(click());

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
        onView(withId(R.id.etTitle)).perform(ViewActions.typeText("Mr."));
        onView(withId(R.id.etFirstName)).perform(ViewActions.typeText("Yiming"));
        onView(withId(R.id.etLastName)).perform(ViewActions.typeText("Zang"));
        onView(withId(R.id.etTelephoneNumber)).perform(ViewActions.typeText("1234567890"));
        onView(withId(R.id.etEmailAddress)).perform(ViewActions.typeText("yiming@gmail.com"));

        // Click submit
        onView(withId(R.id.submitBtn)).perform(ViewActions.click());

// --- Seat selection page

        // Verify that the expected intent was sent
        intended(hasComponent(Out_boundActivity.class.getName()));
    }

}