package com.example.skylink.IntegrationTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.skylink.TestUtils.TestUtils;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.IUserProperties;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UserHandlerIntegrated {
    private IUserHandler userHandler;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for User Handler");
        this.tempDB = TestUtils.copyDB();
        this.userHandler = new UserHandler(true);
        assertNotNull(userHandler);

    }

    @Test
    public void testCreateUser_Success() {
        IUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "person1@gmail.com", "mayor101");
        String rePassword = "mayor101";

        try {
            userHandler.createUser(mockUserProperties, rePassword);
        } catch (UserHandler.UserCreationException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testCreateUser_EmptyName() {
        IUserProperties mockUserProperties = new UserProperties("", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "mayor101";

        try {
            userHandler.createUser(mockUserProperties, rePassword);
            fail("User creation should fail for empty name");
        } catch (UserHandler.UserCreationException e) {
            assertEquals("Full name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testCreateUser_InvalidEmailFormat() {
        IUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "invalidemail", "mayor101");
        String rePassword = "mayor101";

        try {
            userHandler.createUser(mockUserProperties, rePassword);
            fail("User creation should fail for invalid email format");
        } catch (UserHandler.UserCreationException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    public void testCreateUser_PasswordMismatch() {
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
                "Mayokun M. Akintunde",
                "mayorakintunde@gmail.com",
                "mayor101",
                "Male",
                "456 Side St",
                "9876543210",
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
                "person2@gmail.com",
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
                "person2@gmail.com",
                "",
                "Male",
                "456 Side St",
                "9876543210",
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
                "person3@gmail.com",
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
