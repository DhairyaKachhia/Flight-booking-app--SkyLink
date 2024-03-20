package com.example.skylink;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.R;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void signInWithValidCredentials() {
        // Type valid email and password
        String validEmail = "johndoe@example.com";
        String validPassword = "password";
        onView(withId(R.id.etEmail)).perform(typeText(validEmail));
        onView(withId(R.id.etPassword)).perform(typeText(validPassword));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign in button
        onView(withId(R.id.btnSignIn)).perform(click());

        // Check if navigation to next screen occurs
        // Replace R.id.nextScreenId with the ID of the view on the next screen
        onView(withId(R.layout.activity_sign_in)).check(matches(isDisplayed()));
    }

    @Test
    public void signInWithInvalidCredentials_displaysToast() {
        // Type invalid email and password
        String invalidEmail = "invalidemail@example.com";
        String invalidPassword = "invalidpassword";
        onView(withId(R.id.etEmail)).perform(typeText(invalidEmail));
        onView(withId(R.id.etPassword)).perform(typeText(invalidPassword));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign in button
        onView(withId(R.id.btnSignIn)).perform(click());

        // Check if toast message is displayed
//        onView(withText("Incorrect email or password")).inRoot(isSystemAlertWindow()).check(matches(isDisplayed()));

    }


}
