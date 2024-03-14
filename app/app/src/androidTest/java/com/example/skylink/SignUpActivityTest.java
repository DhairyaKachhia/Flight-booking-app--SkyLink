package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.R;
import com.example.skylink.presentation.User_Auth.SignUpActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpActivityTest {

    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void signUpWithValidInputs() {
        // Type valid input values
        Espresso.onView(ViewMatchers.withId(R.id.etFullname)).perform(ViewActions.typeText("John Doe"));
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("johndoe@example.com"));
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("password"));
        Espresso.onView(ViewMatchers.withId(R.id.etRePassword)).perform(ViewActions.typeText("password"));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign up button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp)).perform(ViewActions.click());

        // Check if navigation to next screen occurs
        // Replace R.id.nextScreenId with the ID of the view on the next screen
//        Espresso.onView(ViewMatchers.withId(R.id.nextScreenId)).check(matches(isDisplayed()));
    }

    @Test
    public void signUpWithInvalidInputs() {
        // Type invalid input values
        Espresso.onView(ViewMatchers.withId(R.id.etFullname)).perform(ViewActions.typeText(""));
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("invalidemail")); // Invalid email format
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("password"));
        Espresso.onView(ViewMatchers.withId(R.id.etRePassword)).perform(ViewActions.typeText("differentpassword"));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign up button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp)).perform(ViewActions.click());

        // Check if error message is displayed
        Espresso.onView(withText("Error message")).check(matches(isDisplayed())); // Replace "Error message" with the actual error message displayed
    }
}
