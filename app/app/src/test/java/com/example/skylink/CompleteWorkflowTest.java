package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import com.example.skylink.presentation.User_Auth.SignInActivity;

@RunWith(AndroidJUnit4.class)
public class CompleteWorkflowTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void completeWorkflow() {
        //Code for sign in activity.
        //To be added
        //Click sign up button on the sign in page
        onView(withId(R.id.btnSignUp)).perform(click());
        // Enter personal info in the signup page
        onView(withId(R.id.etFullname)).perform(ViewActions.typeText("Yiming Zang"));
        onView(withId(R.id.etEmail)).perform(ViewActions.typeText("yiming@gmail.com"));
        onView(withId(R.id.etPassword)).perform(ViewActions.typeText("password123"));

        // Click signup button
        onView(withId(R.id.btnSignUp)).perform(click());

        // Enter profile info
        onView(withId(R.id.etAddress)).perform(ViewActions.typeText("111 main st"));
        onView(withId(R.id.etCity)).perform(ViewActions.typeText("Winnipeg"));
        onView(withId(R.id.etProvince)).perform(ViewActions.typeText("MB"));
        onView(withId(R.id.etPhone)).perform(ViewActions.typeText("1234567890"));
        onView(withId(R.id.etDoB)).perform(ViewActions.typeText("01/01/1999"));
        onView(withId(R.id.etGender)).perform(ViewActions.typeText("Male"));

        // Click submit Button
        onView(withId(R.id.btnSubmit)).perform(click());

        // Enter flight searching info
        onView(withId(R.id.et_departure)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2024, 10, 4));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.autoComplete_from)).perform(click());
        onView(withText("YYZ")).perform(click());

        onView(withId(R.id.autoComplete_to)).perform(click());
        onView(withText("YVR")).perform(click());


        // Increase passenger number
        onView(withId(R.id.btn_increment)).perform(click());

        // Decrease passenger number
        onView(withId(R.id.btn_decrement)).perform(click());
        // Click flight searching button
        onView(withId(R.id.searchBtn)).perform(click());

        // Click the button for economy class
        onView(withId(R.id.econPriceBtn)).perform(click());

        Espresso.pressBack();//Go back

        // Click the button for business class
        onView(withId(R.id.busnPriceBtn)).perform(click());
        //Go to passenger info page and fill out info
        onView(withText("Passenger Information")).check(matches(isDisplayed()));
        // Fill in passenger information
        onView(withId(R.id.etTitle)).perform(ViewActions.typeText("Mr."));
        onView(withId(R.id.etFirstName)).perform(ViewActions.typeText("Yiming"));
        onView(withId(R.id.etLastName)).perform(ViewActions.typeText("Zang"));
        onView(withId(R.id.etTelephoneNumber)).perform(ViewActions.typeText("1234567890"));
        onView(withId(R.id.etEmailAddress)).perform(ViewActions.typeText("yiming@gmail.com"));
        //Click submit
        onView(withId(R.id.btnSubmit)).perform(ViewActions.click());

    }

    @Test
    public void incompleteRegistration() {
        // Try to register with invalid info, should not register successfully.
    }

    @Test
    public void invalidDateOfBirth() {
        // Test if warning info is popped up.
    }

    @Test
    public void invalidFlightSearch() {
        // Check if our app returns the expected info
    }

    @Test
    public void navigateToProfileFromMenu() {
        // To be added
    }

    @Test
    public void logoutFromMenu() {
        // To be added
    }
}
