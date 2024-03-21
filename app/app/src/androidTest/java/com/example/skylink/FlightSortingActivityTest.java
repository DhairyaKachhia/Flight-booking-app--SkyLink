package com.example.skylink;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.EspressoUtils;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
public class FlightSortingActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void testFlightSorting() {
        String fromLocation = "Toronto - YYZ";
        String toLocation = "Vancouver - YVR";
        int year = 2024;
        int monthOfYear = 4;
        int dayOfMonth = 7;

        // Sign in
        EspressoUtils.signIn("akintundemayokun@gmail.com", "22ChancellorCircle");

        // Verify FlightSearchP activity is started
        EspressoUtils.verifyActivity(FlightSearchP.class);

        // Perform flight search
        EspressoUtils.performFlightSearch(fromLocation, toLocation, year, monthOfYear, dayOfMonth);

        // --- Flight display page ---

        // Select Direct flight from the spinner list
        EspressoUtils.selectSpinnerItem(R.id.sortingListOption, "Direct flight");

        // Check if first flight does not have a mid airport
//        onView(withId(R.id.flightListView)).check(matches(atPosition(0, withText(""))));

        // Select Earliest departure from the spinner list
        EspressoUtils.selectSpinnerItem(R.id.sortingListOption, "Earliest departure");

        // Check if first flight has 'YOW' as mid airport
//        onView(withId(R.id.flightListView)).check(matches(atPosition(0, withText("YOW "))));

        // Select Lowest price from the spinner list
        EspressoUtils.selectSpinnerItem(R.id.sortingListOption, "Lowest price");

        // Check if first flight has 'YOW' as mid airport
//        onView(withId(R.id.flightListView)).check(matches(atPosition(0, withText("YOW "))));
    }


//    public  Matcher<View> atPosition(final int position, final Matcher<View> itemMatcher) {
//        return new BoundedMatcher<View, ListView>(ListView.class) {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("has item at position " + position + ": ");
//                itemMatcher.describeTo(description);
//            }
//
//            @Override
//            protected boolean matchesSafely(ListView listView) {
//                int firstPosition = listView.getFirstVisiblePosition();
//                int lastPosition = listView.getLastVisiblePosition();
//                int adapterPosition = position - firstPosition;
//
//                if (adapterPosition < 0 || adapterPosition >= listView.getChildCount()) {
//                    return false;
//                }
//
//                View itemView = listView.getChildAt(adapterPosition);
//                return itemMatcher.matches(itemView);
//            }
//        };
//    return null;
//    }
}
