package com.example.skylink;


import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.skylink.presentation.UserInfo.User_info;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class AddPassengersTest {

    @Rule
    public ActivityScenarioRule<User_info> activityScenarioRule = new ActivityScenarioRule<>(User_info.class);

    @Test
    public void completeWorkflow() {
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
}