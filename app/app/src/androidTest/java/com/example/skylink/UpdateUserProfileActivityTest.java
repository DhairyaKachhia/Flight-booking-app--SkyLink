package com.example.skylink;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

public class UpdateUserProfileActivityTest {

    @Rule
    public ActivityScenarioRule<UpdateUserProfileActivity> activityRule = new ActivityScenarioRule<>(UpdateUserProfileActivity.class);

    @Test
    public void testUpdateUserProfileWithValidInput() {
        //Should be used along with signin or signup activity, or the app will give error "User doesnot exist.
        onView(withId(R.id.drawer_layout)).perform(ViewActions.click());

        onView(withText("Update Profile")).perform(ViewActions.click());

        onView(withId(R.id.etAddress)).perform(ViewActions.typeText("123 Main St"));

        onView(withId(R.id.etCity)).perform(ViewActions.typeText("Winnipeg"));

        onView(withId(R.id.etProvince)).perform(ViewActions.typeText("MB"));

        onView(withId(R.id.etPhone)).perform(ViewActions.typeText("1234567890"));

        onView(withId(R.id.etDoB)).perform(ViewActions.typeText("01/01/2000"));

        onView(withId(R.id.etGender)).perform(ViewActions.typeText("Male"));

        onView(withId(R.id.btnSubmit)).perform(ViewActions.click());

        onView(withText("Profile updated successfully"))
                .inRoot(RootMatchers.isPlatformPopup())
                .check(matches(ViewMatchers.isDisplayed()));
    }
}
