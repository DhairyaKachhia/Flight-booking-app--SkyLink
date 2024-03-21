package com.example.skylink;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.skylink.presentation.User_Auth.SignInActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import java.util.Random;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
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

        signUp(fullName,email,password,address,city,province,phone,dob,gender);
        clickLogOut();

        // Enter username
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());

        // Enter password
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn)).perform(ViewActions.click());

    }

    public void clickLogOut(){
        // Open the navigation drawer
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // Click on the "Log Out" menu item
        Espresso.onView(withId(R.id.nav_logout)).perform(ViewActions.click());
    }

    public void signUp(String fullName,String email, String password, String address, String city, String province, String phone, String dob, String gender){
        //        Open Sign Up Screen.
        Espresso.onView(withId(R.id.tvSignInClick)).perform(click());



        // Fill in the form with random values
        Espresso.onView(ViewMatchers.withId(R.id.etFullname))
                .perform(ViewActions.typeText(fullName), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.etRePassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());


        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click sign up button
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp)).perform(click());

        // Enter Address
        Espresso.onView(ViewMatchers.withId(R.id.etAddress))
                .perform(ViewActions.typeText(address), ViewActions.closeSoftKeyboard());

        // Enter City
        Espresso.onView(ViewMatchers.withId(R.id.etCity))
                .perform(ViewActions.typeText(city), ViewActions.closeSoftKeyboard());

        // Enter Province
        Espresso.onView(ViewMatchers.withId(R.id.etProvince))
                .perform(ViewActions.typeText(province), ViewActions.closeSoftKeyboard());

        // Enter Phone
        Espresso.onView(ViewMatchers.withId(R.id.etPhone))
                .perform(ViewActions.typeText(phone), ViewActions.closeSoftKeyboard());

        // Enter Dob
        Espresso.onView(ViewMatchers.withId(R.id.etDoB))
                .perform(ViewActions.typeText(dob), ViewActions.closeSoftKeyboard());

        // Enter Gender
        Espresso.onView(ViewMatchers.withId(R.id.etGender))
                .perform(ViewActions.typeText(gender), ViewActions.closeSoftKeyboard());

        // Verify if the form is completely filled
        Espresso.onView(ViewMatchers.withId(R.id.btnSubmit)).perform(ViewActions.click());
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
