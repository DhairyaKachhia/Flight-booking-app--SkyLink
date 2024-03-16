package com.example.skylink;
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

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


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
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText(validEmail));
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText(validPassword));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign in button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn)).perform(ViewActions.click());

        // Check if navigation to next screen occurs
        // Replace R.id.nextScreenId with the ID of the view on the next screen
        Espresso.onView(ViewMatchers.withId(R.layout.activity_sign_in)).check(matches(isDisplayed()));
    }

    @Test
    public void signInWithInvalidCredentials() {
        // Type invalid email and password
        String invalidEmail = "invalidemail@example.com";
        String invalidPassword = "invalidpassword";
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText(invalidEmail));
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText(invalidPassword));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign in button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn)).perform(ViewActions.click());

        // Check if the "Incorrect email or password" message is displayed
        Espresso.onView(withText("Incorrect email or password")).check(matches(isDisplayed()));
    }
}
