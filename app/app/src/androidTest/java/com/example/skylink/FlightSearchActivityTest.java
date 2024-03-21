package com.example.skylink;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static net.bytebuddy.matcher.ElementMatchers.is;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.anything;

import static java.util.regex.Pattern.matches;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Random;




@LargeTest
@RunWith(AndroidJUnit4.class)
public class FlightSearchActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void FlightSearch(){
        signIn();
        onView(withId(R.id.autoComplete_from)).perform(click());

        // Click on the desired dropdown option
        onView(ViewMatchers.withText("Toronto - YYZ")).inRoot(RootMatchers.isPlatformPopup()).perform(ViewActions.click());

        // Repeat the same process for the "To" AutoCompleteTextView
        onView(ViewMatchers.withId(R.id.autoComplete_to)).perform(ViewActions.click());
        onView(ViewMatchers.withText("Vancouver - YVR")).inRoot(RootMatchers.isPlatformPopup()).perform(ViewActions.click());


        // Perform click action on the departure date EditText
        onView(ViewMatchers.withId(R.id.et_departure)).perform(click());

        // Change the date of the DatePicker
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2024, 4, 7));

        Espresso.onView(withText("OK")).perform(click());


        // Click on the search button
        Espresso.onView(ViewMatchers.withId(R.id.searchBtn)).perform(ViewActions.click());



    }
    public void signIn() {
        // Generate random values for each field
        String fullName = generateRandomString(10);
        String email = generateRandomEmail();
        String password = generateRandomString(12);
        String address = generateRandomString(20);
        String city = generateRandomString(10);
        String province = generateRandomString(2);
        String phone = generateRandomPhoneNumber();
        String dob = generateRandomDateOfBirth();
        String gender = generateRandomGender();

//        signUp(fullName,email,password,address,city,province,phone,dob,gender);
//        clickLogOut();

        // Enter username
        onView(ViewMatchers.withId(R.id.etEmail))
                .perform(typeText("akintundemayokun@gmail.com"), closeSoftKeyboard());

        // Enter password
        onView(ViewMatchers.withId(R.id.etPassword))
                .perform(typeText("22ChancellorCircle"), closeSoftKeyboard());

        onView(ViewMatchers.withId(R.id.btnSignIn)).perform(ViewActions.click());

    }

    public void clickLogOut(){
        // Open the navigation drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // Click on the "Log Out" menu item
        onView(withId(R.id.nav_logout)).perform(ViewActions.click());
    }

    public void signUp(String fullName,String email, String password, String address, String city, String province, String phone, String dob, String gender){
        //        Open Sign Up Screen.
        onView(withId(R.id.tvSignInClick)).perform(click());



        // Fill in the form with random values
        onView(ViewMatchers.withId(R.id.etFullname))
                .perform(typeText(fullName), closeSoftKeyboard());

        onView(ViewMatchers.withId(R.id.etEmail))
                .perform(typeText(email), closeSoftKeyboard());

        onView(ViewMatchers.withId(R.id.etPassword))
                .perform(typeText(password), closeSoftKeyboard());

        onView(ViewMatchers.withId(R.id.etRePassword))
                .perform(typeText(password), closeSoftKeyboard());


        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign up button
        onView(ViewMatchers.withId(R.id.btnSignUp)).perform(click());

        // Enter Address
        onView(ViewMatchers.withId(R.id.etAddress))
                .perform(typeText(address), closeSoftKeyboard());

        // Enter City
        onView(ViewMatchers.withId(R.id.etCity))
                .perform(typeText(city), closeSoftKeyboard());

        // Enter Province
        onView(ViewMatchers.withId(R.id.etProvince))
                .perform(typeText(province), closeSoftKeyboard());

        // Enter Phone
        onView(ViewMatchers.withId(R.id.etPhone))
                .perform(typeText(phone), closeSoftKeyboard());

        // Enter Dob
        onView(ViewMatchers.withId(R.id.etDoB))
                .perform(typeText(dob), closeSoftKeyboard());

        // Enter Gender
        onView(ViewMatchers.withId(R.id.etGender))
                .perform(typeText(gender), closeSoftKeyboard());

        // Verify if the form is completely filled
        onView(ViewMatchers.withId(R.id.btnSubmit)).perform(ViewActions.click());
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    private String generateRandomEmail() {
        return generateRandomString(10) + "@" + generateRandomString(5) + ".com";
    }

    private String generateRandomPhoneNumber() {
        return "1" + generateRandomNumericString(10); // Assuming the phone number starts with 1
    }

    private String generateRandomNumericString(int length) {
        String digits = "0123456789";
        StringBuilder randomNumericString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomNumericString.append(digits.charAt(random.nextInt(digits.length())));
        }
        return randomNumericString.toString();
    }

    private String generateRandomDateOfBirth() {
        // Generate random date of birth between 01/01/1950 and 01/01/2005
        int minYear = 1950;
        int maxYear = 2005;
        Random random = new Random();
        int year = random.nextInt(maxYear - minYear + 1) + minYear;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; // Assuming all months have 28 days
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    private String generateRandomGender() {
        String[] genders = {"M", "F", "Other"}; // Add more options if needed
        Random random = new Random();
        return genders[random.nextInt(genders.length)];
    }


}
