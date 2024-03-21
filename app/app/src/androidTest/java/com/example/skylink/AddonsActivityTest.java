package com.example.skylink;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.presentation.Addons.Addons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddonsActivityTest {

    @Rule
    public ActivityScenarioRule<Addons> activityScenarioRule = new ActivityScenarioRule<>(Addons.class);

    @Test
    public void testAddExtraBag() {
        String expectedBagCount = "2";
        String expectedBagFee = "$50";

        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button

        Espresso.onView(withId(R.id.totalBags)).check(matches(withText(expectedBagCount)));     // check if number of bag is incremented
        Espresso.onView(withId(R.id.bagFees)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed

        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed in total price field

        // check if add bag button is disabled when max count is reached
        Espresso.onView(withId(R.id.bag_btn_increment)).check(matches(not(isEnabled())));

    }

    @Test
    public void testRemoveExtraBag() {
        String expectedBagCount = "1";
        String expectedBagFee = "$0";

        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button

        Espresso.onView(withId(R.id.bag_btn_decrement)).perform(ViewActions.click());           // find and click subtract button

        Espresso.onView(withId(R.id.totalBags)).check(matches(withText(expectedBagCount)));     // check if number of bag is decremented
        Espresso.onView(withId(R.id.bagFees)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed

        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedBagFee)));         // check if bag's fee is changed in total price field

        // check if subtract bag button is disabled when max count is reached
        Espresso.onView(withId(R.id.bag_btn_decrement)).check(matches(not(isEnabled())));

    }

    @Test
    public void testAddExtraPet() {
        String expectedPetCount = "2";
        String expectedPetFee = "$120";

        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button first time
        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button second time

        Espresso.onView(withId(R.id.totalPetSeat)).check(matches(withText(expectedPetCount)));     // check if number of pet is incremented
        Espresso.onView(withId(R.id.petSeatFees)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed

        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed in total price field


        // check if add pet button is disabled when max count is reached
        Espresso.onView(withId(R.id.pet_btn_increment)).check(matches(not(isEnabled())));

    }

    @Test
    public void testRemoveExtraPet() {
        String expectedPetCount = "0";
        String expectedPetFee = "$0";

        // press subtract button twice to make sure we start with 0 pet count
        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button first time
        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button second time


        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add one pet


        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button to remove the pet


        Espresso.onView(withId(R.id.totalPetSeat)).check(matches(withText(expectedPetCount)));     // check if number of pet is decremented
        Espresso.onView(withId(R.id.petSeatFees)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed

        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPetFee)));         // check if pet's fee is changed in total price field


        // check if subtract pet button is disabled when max count is reached
        Espresso.onView(withId(R.id.pet_btn_decrement)).check(matches(not(isEnabled())));
    }

    @Test
    public void testAddWifi() {
        String expectedWifiFee = "$50";

        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());           // find and click radio button to add wifi

        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).check(matches(isChecked()));             // check if wifi button is selected
        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWifiFee)));         // check if wifi fee is changed in total price field

    }

    @Test
    public void testRemoveWifi() {
        String expectedWifiFee = "$0";

        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());          // find and click radio button to add wifi

        Espresso.onView(withId(R.id.radioButtonNoWifi)).perform(ViewActions.click());               // find and click radio button to remove wifi

        Espresso.onView(withId(R.id.radioButtonNoWifi)).check(matches(isChecked()));                // check if wifi button is not selected
        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWifiFee)));       // check if wifi fee is changed in total price field

    }

    @Test
    public void testAddWheelchair() {
        String expectedWheelchairFee = "$0";

        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());           // find and click radio button to add wheelchair

        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).check(matches(isChecked()));             // check if wheelchair button is selected
        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWheelchairFee)));         // check if wheelchair's fee is changed in total price field

    }

    @Test
    public void testRemoveWheelchair() {
        String expectedWheelchairFee = "$0";

        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());           // find and click radio button to add wheelchair

        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).perform(ViewActions.click());                 // find and click radio button to remove wheelchair

        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).check(matches(isChecked()));             // check if wheelchair button is selected
        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedWheelchairFee)));         // check if wheelchair's fee is changed in total price field

    }

    @Test
    public void testAllAddons() {
        String expectedPrice = "$160";

        // add a bag => $50
        Espresso.onView(withId(R.id.bag_btn_increment)).perform(ViewActions.click());           // find and click add button to add a bag


        // add a pet => $60
        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add one pet
        Espresso.onView(withId(R.id.pet_btn_increment)).perform(ViewActions.click());           // find and click add button to add second pet

        Espresso.onView(withId(R.id.pet_btn_decrement)).perform(ViewActions.click());           // find and click subtract button to remove the pet


        // add wifi => $50
        Espresso.onView(withId(R.id.radioButtonIncludeWifi)).perform(ViewActions.click());           // find and click radio button to add wifi


        // do not add wheelchair => $0
        Espresso.onView(withId(R.id.radioButtonIncludeWheelchair)).perform(ViewActions.click());     // find and click radio button to add wheelchair
        Espresso.onView(withId(R.id.radioButtonNoWheelchair)).perform(ViewActions.click());          // find and click radio button to remove wheelchair


        Espresso.onView(withId(R.id.totalPriceTV)).check(matches(withText(expectedPrice)));         // check if selected addons fee is changed in total price field

    }

}