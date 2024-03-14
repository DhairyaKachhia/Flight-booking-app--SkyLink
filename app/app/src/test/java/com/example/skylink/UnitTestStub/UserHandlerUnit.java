package com.example.skylink.UnitTestStub;

import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.persistence.Interfaces.IUserDB;
import org.junit.Before;
import com.example.skylink.persistence.Implementations.stub.UserStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserHandlerUnit {
    private IUserDB userStub;
    private UserHandler userHandler;

    @Before
    public void setUp() {
        userStub = new UserStub();
        userHandler = new UserHandler(userStub);
    }

    @Test
    public void testCreateUser_Success() {
        IUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "mayor101";

        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSignInUser_Success() {
        IUserProperties mockUserProperties = new UserProperties("Test User", "test@example.com", "password123");
        String rePassword = "password123";
        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("User creation should succeed.");
        }

        boolean signInResult = userHandler.signinUser(new UserProperties("test@example.com", "password123"));
        assertTrue("User should be able to sign in successfully.", signInResult);
    }

    @Test
    public void testSignInUser_WrongPassword() throws UserHandler.UserCreationException {
        IUserProperties userProperties = new UserProperties("John Doe", "john@example.com", "correctPassword");
        userHandler.createUser(userProperties, "correctPassword");

        IUserProperties signInAttempt = new UserProperties("john@example.com", "wrongPassword");
        boolean result = userHandler.signinUser(signInAttempt);

        assertFalse("SignIn should fail due to wrong password", result);
    }


    @Test
    public void testIsValidUserPropertiesForUpdate_InvalidAddress() {
        IUserProperties userProperties = new UserProperties(
                "Jane Doe", "jane.doe@example.com", "Password123!", "Female",
                "", // Intentionally invalid address
                "1234567890", "1985-10-15", "Country");

        String validationMessage = userHandler.isValidUserPropertiesForUpdate(userProperties);
        assertEquals("Address cannot be empty", validationMessage);
    }

    @Test
    public void testCreateUser_EmptyName() {
        // Mock data
        IUserProperties mockUserProperties = new UserProperties("", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "mayor101";

        try {
            // Perform the test
            userHandler.createUser(mockUserProperties, rePassword);

            // If createUser does not throw an exception, the test should fail
            fail("User creation should fail for empty name");
        } catch (UserHandler.UserCreationException e) {
            // If createUser throws an exception, the test passes
            assertEquals("Full name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testCreateUser_InvalidEmailFormat() {
        // Mock data
        IUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "invalidemail", "mayor101");
        String rePassword = "mayor101";

        try {
            // Perform the test
            userHandler.createUser(mockUserProperties, rePassword);

            // If createUser does not throw an exception, the test should fail
            fail("User creation should fail for invalid email format");
        } catch (UserHandler.UserCreationException e) {
            // If createUser throws an exception, the test passes
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    public void testCreateUser_PasswordMismatch() {
        // Mock data
        IUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "differentPassword";

        try {
            userHandler.createUser(mockUserProperties, rePassword);
            fail("User creation should fail for password mismatch");
        } catch (UserHandler.UserCreationException e) {
            assertEquals("Passwords do not match", e.getMessage());
        }
    }

    @Test
    public void testUpdateUserProfile_Success() {
        // Create a user for testing
        IUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("Exception should not be thrown");
        }

        // Update the user profile
        IUserProperties updatedUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101",
                "Male",
                "456 Side St",
                "1987654321",
                "1990-01-01",
                "New Country"
        );
        boolean result = userHandler.updateUserProfile(updatedUserProperties);

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testUpdateUserProfile_EmptyPassword() {
        // Create a user for testing
        IUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("Exception should not be thrown");
        }

        // Update the user profile with an empty password
        IUserProperties updatedUserProperties = new UserProperties(
                "Mayokun M. Akintunde",
                "mayorakintunde@gmail.com",
                "",
                "Male",
                "456 Side St",
                "987654321",
                "1990-01-01",
                "New Country"
        );

        // Perform the test
        boolean result = userHandler.updateUserProfile(updatedUserProperties);

        // Verify the result
        assertFalse(result);
    }

    @Test
    public void testUpdateUserProfile_NullUserProperties() {
        // Create a user for testing
        IUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("Exception should not be thrown");
        }

        // Update the user profile with null user properties
        IUserProperties updatedUserProperties = null;

        // Perform the test
        boolean result = userHandler.updateUserProfile(updatedUserProperties);

        // Verify the result
        assertFalse(result);
    }
}
