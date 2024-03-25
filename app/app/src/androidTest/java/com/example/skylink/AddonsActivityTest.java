package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.presentation.Addons.Addons;
import com.example.skylink.presentation.FlightSearching.FlightDisplay;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.SeatSelect.Out_boundActivity;
import com.example.skylink.presentation.UserInfo.User_info;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.example.skylink.presentation.User_Auth.UpdateUserProfileActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddonsActivityTest {

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

// --- Sign-up page

        // call a method to perform signup test with valid inputs
        EspressoUtils.signUp("John Doe", "john@example.com", "password");


// --- Update user profile page

        // Verify that the expected intent was sent
        intended(hasComponent(UpdateUserProfileActivity.class.getName()));

        // call a method to perform update user info test with valid user profile info
        EspressoUtils.updateUserInfo("123 some rd.", "Winnipeg", "MB", "1234567890", "12/12/2000", "Male");


// --- Flight search page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightSearchP.class.getName()));

        /* Search flight from YVR to YHM on April 8, 2024 */

        // call a method to perform flight searching test with valid search data.
        EspressoUtils.performFlightSearch(flyFrom, flyTo, 2024, 4, 8);


// --- Flight display page

        // Verify that the expected intent was sent
        intended(hasComponent(FlightDisplay.class.getName()));

        onView(withId(R.id.flightListView)).check(matches(isDisplayed()));      // check if flights are shown

        // Click the button for economy class
        onView(withId(R.id.busnPriceBtn)).perform(click());

// --- User info page

        // Verify that the expected intent was sent
        intended(hasComponent(User_info.class.getName()));

        // Fill in passenger information
        EspressoUtils.performUserInfo("Mr.", "Yiming", "Zang", "1234567890", "yiming@gmail.com");

// --- Seat selection page

        // Verify that the expected intent was sent
        intended(hasComponent(Out_boundActivity.class.getName()));

//        onView(allOf(withId(R.id.Flight_Layout), withTagValue(equalTo("black"))))
//                .perform(click());

        onView(allOf(withId(R.id.Flight_Layout), isClickable()))
                .perform(click());

        onView(withId(R.id.confirmButton)).perform(click());
    }

//    @Test
//    public void testAddExtraBag() {
//        String expectedBagCount = "2";
//        String expectedBagFee = "$50";
//
//        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button
//
//        Espresso.onView(withId(R.id.totalBags)).check(matches(withText(expectedBagCount)));     // check if number of bag is incremented
//        Espresso.onView(withId(R.id.bagFees)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed
//
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed in total price field
//
//        // check if add bag button is disabled when max count is reached
//        Espresso.onView(withId(R.id.bag_btn_increment)).check(matches(not(isEnabled())));
//
//    }
//
//    @Test
//    public void testRemoveExtraBag() {
//        String expectedBagCount = "1";
//        String expectedBagFee = "$0";
//
//        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button
//
//        Espresso.onView(withId(R.id.bag_btn_decrement)).perform(ViewActions.click());           // find and click subtract button
//
//        Espresso.onView(withId(R.id.totalBags)).check(matches(withText(expectedBagCount)));     // check if number of bag is decremented
//        Espresso.onView(withId(R.id.bagFees)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed
//
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed in total price field
//
//        // check if subtract bag button is disabled when max count is reached
//        Espresso.onView(withId(R.id.bag_btn_decrement)).check(matches(not(isEnabled())));
//
//    }
//
//    @Test
//    public void testAddExtraPet() {
//        String expectedPetCount = "2";
//        String expectedPetFee = "$120";
//
//        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button first time
//        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button second time
//
//        Espresso.onView(withId(R.id.totalPetSeat)).check(matches(withText(expectedPetCount)));     // check if number of pet is incremented
//        Espresso.onView(withId(R.id.petSeatFees)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed
//
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed in total price field
//
//
//        // check if add pet button is disabled when max count is reached
//        Espresso.onView(withId(R.id.pet_btn_increment)).check(matches(not(isEnabled())));
//
//    }
//
//    @Test
//    public void testRemoveExtraPet() {
//        String expectedPetCount = "0";
//        String expectedPetFee = "$0";
//
//        // press subtract button twice to make sure we start with 0 pet count
//        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button first time
//        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button second time
//
//
//        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add one pet
//
//
//        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button to remove the pet
//
//
//        Espresso.onView(withId(R.id.totalPetSeat)).check(matches(withText(expectedPetCount)));     // check if number of pet is decremented
//        Espresso.onView(withId(R.id.petSeatFees)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed
//
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed in total price field
//
//
//        // check if subtract pet button is disabled when max count is reached
//        Espresso.onView(withId(R.id.pet_btn_decrement)).check(matches(not(isEnabled())));
//    }
//
//    @Test
//    public void testAddWifi() {
//        String expectedWifiFee = "$50";
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());           // find and click radio button to add wifi
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).check(matches(isChecked()));             // check if wifi button is selected
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWifiFee)));         // check if wifi fee is changed in total price field
//
//    }
//
//    @Test
//    public void testRemoveWifi() {
//        String expectedWifiFee = "$0";
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());          // find and click radio button to add wifi
//
//        Espresso.onView(withId(R.id.radioButtonNoWifi)).perform(ViewActions.click());               // find and click radio button to remove wifi
//
//        Espresso.onView(withId(R.id.radioButtonNoWifi)).check(matches(isChecked()));                // check if wifi button is not selected
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWifiFee)));       // check if wifi fee is changed in total price field
//
//    }
//
//    @Test
//    public void testAddWheelchair() {
//        String expectedWheelchairFee = "$0";
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());           // find and click radio button to add wheelchair
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).check(matches(isChecked()));             // check if wheelchair button is selected
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWheelchairFee)));         // check if wheelchair's fee is changed in total price field
//
//    }
//
//    @Test
//    public void testRemoveWheelchair() {
//        String expectedWheelchairFee = "$0";
//
//        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());           // find and click radio button to add wheelchair
//
//        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).perform(ViewActions.click());                 // find and click radio button to remove wheelchair
//
//        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).check(matches(isChecked()));             // check if wheelchair button is selected
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWheelchairFee)));         // check if wheelchair's fee is changed in total price field
//
//    }
//
//    @Test
//    public void testAllAddons() {
//        String expectedPrice = "$160";
//
//        // add a bag => $50
//        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button to add a bag
//
//
//        // add a pet => $60
//        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add one pet
//        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add second pet
//
//        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button to remove the pet
//
//
//        // add wifi => $50
//        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());           // find and click radio button to add wifi
//
//
//        // do not add wheelchair => $0
//        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());     // find and click radio button to add wheelchair
//        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).perform(ViewActions.click());          // find and click radio button to remove wheelchair
//
//
//        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPrice)));         // check if selected addons fee is changed in total price field
//
//    }

}