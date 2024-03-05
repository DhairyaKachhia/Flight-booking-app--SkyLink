package com.example.skylink;

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

        // Example of using the context (you can use it for other purposes in your test)
        String appName = appContext.getString(R.string.app_name);
        System.out.println("App Name: " + appName);

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
