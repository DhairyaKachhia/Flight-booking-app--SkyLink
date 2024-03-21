package com.example.skylink;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.skylink.R;
import com.example.skylink.presentation.Payment.CreditCardPaymentActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CreditCardPaymentActivityTest {

    // Launch the CreditCardPaymentActivity for testing
    @Rule
    public ActivityScenarioRule<CreditCardPaymentActivity> activityScenarioRule =
            new ActivityScenarioRule<>(CreditCardPaymentActivity.class);

    @Before
    public void setUp() {
        // Ensures the activity is launched before each test
        ActivityScenario.launch(CreditCardPaymentActivity.class);
    }

    @Test
    public void testInvalidPayment() {
        // Perform actions: fill in invalid payment details and try to pay
        Espresso.onView(ViewMatchers.withId(R.id.etCreditCardNumber)).perform(ViewActions.typeText("1234567890123456"));
        Espresso.onView(ViewMatchers.withId(R.id.etExpirationDate)).perform(ViewActions.typeText("0123"));
        Espresso.onView(ViewMatchers.withId(R.id.etCVV)).perform(ViewActions.typeText("1234"));
        Espresso.onView(ViewMatchers.withId(R.id.etCardholderName)).perform(ViewActions.typeText("John Doe"));
        Espresso.onView(ViewMatchers.withId(R.id.etBillingAddress)).perform(ViewActions.typeText("123 Main St"));

        Espresso.onView(ViewMatchers.withId(R.id.btnPay)).perform(ViewActions.click());

        // Add assertions here to verify that the payment was not successful
        // Example: Espresso.onView(ViewMatchers.withId(R.id.errorMessageTextView)).check(ViewAssertions.matches(ViewMatchers.withText("Invalid payment details")));
    }

    @Test
    public void testValidPayment() {
        // Perform actions: fill in valid payment details and try to pay
        Espresso.onView(ViewMatchers.withId(R.id.etCreditCardNumber)).perform(ViewActions.typeText("1234567890123456"));
        Espresso.onView(ViewMatchers.withId(R.id.etExpirationDate)).perform(ViewActions.typeText("0123"));
        Espresso.onView(ViewMatchers.withId(R.id.etCVV)).perform(ViewActions.typeText("123"));
        Espresso.onView(ViewMatchers.withId(R.id.etCardholderName)).perform(ViewActions.typeText("John Doe"));
        Espresso.onView(ViewMatchers.withId(R.id.etBillingAddress)).perform(ViewActions.typeText("123 Main St"));

        Espresso.onView(ViewMatchers.withId(R.id.btnPay)).perform(ViewActions.click());

        // Add assertions here to verify that the payment was successful
        // Example: Espresso.onView(ViewMatchers.withId(R.id.successMessageTextView)).check(ViewAssertions.matches(ViewMatchers.withText("Payment successful")));
    }
}
