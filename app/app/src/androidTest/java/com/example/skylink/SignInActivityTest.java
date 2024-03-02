package com.example.skylink;

//package com.example.skylink;
//
//import static org.junit.Assert.assertNotNull;
//
//import androidx.test.core.app.ActivityScenario;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//
//import com.example.skylink.business.Interface.iAirportPath;
//import com.example.skylink.objects.Implementations.FlightSearch;
//import com.example.skylink.objects.Interfaces.iFlight;
//import com.example.skylink.objects.Interfaces.iFlightSearch;
//import com.example.skylink.presentation.User_Auth.SignInActivity;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class AirportPathTestIntegration {
//
//    private iAirportPath airportPath;
//
//    @Rule
//    public ActivityScenarioRule<SignInActivity> scenarioRule = new ActivityScenarioRule<>(SignInActivity.class);
//
//    @Before
//    public void setUp() {
//        // Initialize any necessary objects or perform setup tasks here
//    }
//
//    @Test
//    public void testFindFlightsWithValidSearchReturnsValidResult() {
//
//    }
//}
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.skylink.R;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.junit.Rule;
import org.junit.Test;

public class SignInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityRule =
            new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void testSignIn() {
        // Access the application context using InstrumentationRegistry
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Alternatively, you can use ApplicationProvider for obtaining the context
        // Context appContext = ApplicationProvider.getApplicationContext();

        // Type email
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText("user@example.com"));

        // Type password
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText("password"));

        // Press the sign-in button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
                .perform(ViewActions.click());

        // Add any additional verifications/assertions based on your expected behavior
    }
}
