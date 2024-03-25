package com.example.skylink;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FlightSearchActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void testFlightSearch() {
        String fromLocation = "Toronto - YYZ";
        String toLocation = "Vancouver - YVR";
        int year = 2024;
        int monthOfYear = 4;
        int dayOfMonth = 7;

        // Fix the logic here.

        String[] userInfo = UserInfoGenerator.generateUserInfo();

        EspressoUtils.signUp(userInfo[0], userInfo[1], userInfo[2]);

        EspressoUtils.verifyActivity(UpdateUserProfileActivity.class);

        EspressoUtils.updateUserInfo(userInfo[3], userInfo[4], userInfo[5], userInfo[6], userInfo[7], userInfo[8]);

        EspressoUtils.clickLogOut();

        EspressoUtils.signIn(userInfo[1],userInfo[2]);

        EspressoUtils.verifyActivity(FlightSearchP.class);

        EspressoUtils.performFlightSearch(fromLocation, toLocation, year, monthOfYear, dayOfMonth);
    }

}
