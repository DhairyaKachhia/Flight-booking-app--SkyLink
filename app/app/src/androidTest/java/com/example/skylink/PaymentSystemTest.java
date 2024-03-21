package com.example.skylink;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.skylink.presentation.Payment.CreditCardPaymentActivity;

@RunWith(AndroidJUnit4.class)
public class PaymentSystemTest {

    @Rule
    public ActivityScenarioRule<CreditCardPaymentActivity> activityScenarioRule = new ActivityScenarioRule<>(CreditCardPaymentActivity.class);

    @Test
    public void testPaymentProcess() {
            Espresso.onView(ViewMatchers.withId(R.id.etCreditCardNumber))
                    .perform(ViewActions.typeText("1234567890123456"), ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.etExpirationDate))
                    .perform(ViewActions.typeText("1230"), ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.etCVV))
                    .perform(ViewActions.typeText("123"), ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.etCardholderName))
                    .perform(ViewActions.typeText(" Yiming Zang "), ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.etBillingAddress))
                    .perform(ViewActions.typeText("123 Main St, Winnipeg, MB"), ViewActions.closeSoftKeyboard());

            Espresso.onView(withId(R.id.btnPay)).perform(ViewActions.click());
    }
}
