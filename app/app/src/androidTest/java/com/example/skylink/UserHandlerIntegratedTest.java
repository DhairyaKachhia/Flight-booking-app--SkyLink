package com.example.skylink;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.iUserProperties;
import com.example.skylink.persistence.Interfaces.IUserDB;

import org.junit.Before;
import org.junit.Test;

public class UserHandlerIntegratedTest {
    private IUserDB userStub;
    private UserHandler userHandler;

    @Before
    public void setUp() {
        userStub = Services.getUserDatabase();
        userHandler = new UserHandler(userStub);
    }

    @Test
    public void testCreateUser_Success() {
        // Mock data
        iUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "mayor101";

        // Perform the test
        boolean result = userHandler.createUser(mockUserProperties, rePassword);

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testCreateUser_EmptyName() {
        // Mock data
        iUserProperties mockUserProperties = new UserProperties("", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "mayor101";

        // Perform the test
        boolean result = userHandler.createUser(mockUserProperties, rePassword);

        // Verify the result
        assertFalse("User creation should fail for empty name", result);

    }

    @Test
    public void testCreateUser_InvalidEmailFormat() {
        // Mock data
        iUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "invalidemail", "mayor101");
        String rePassword = "mayor101";

        // Perform the test
        boolean result = userHandler.createUser(mockUserProperties, rePassword);

        // Verify the result
        assertFalse(result);
    }

    @Test
    public void testCreateUser_PasswordMismatch() {
        // Mock data
        iUserProperties mockUserProperties = new UserProperties("Mayokun Moses Akintunde", "akintundemayokun@gmail.com", "mayor101");
        String rePassword = "differentPassword";

        // Perform the test
        boolean result = userHandler.createUser(mockUserProperties, rePassword);

        // Verify the result
        assertFalse(result);
    }

    @Test
    public void testUpdateUserProfile_Success() {
        // Create a user for testing
        iUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        userHandler.createUser(mockUserProperties, rePassword);

        // Update the user profile
        iUserProperties updatedUserProperties = new UserProperties(
                "Mayokun M. Akintunde",
                "mayorakintunde@gmail.com",
                "mayor101",
                "Male",
                "456 Side St",
                "987654321",
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
        iUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        userHandler.createUser(mockUserProperties, rePassword);

        // Update the user profile with an empty password
        iUserProperties updatedUserProperties = new UserProperties(
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
        iUserProperties mockUserProperties = new UserProperties(
                "Mayokun Moses Akintunde",
                "akintundemayokun@gmail.com",
                "mayor101"
        );
        String rePassword = "mayor101";
        userHandler.createUser(mockUserProperties, rePassword);

        // Update the user profile with null user properties
        iUserProperties updatedUserProperties = null;

        // Perform the test
        boolean result = userHandler.updateUserProfile(updatedUserProperties);

        // Verify the result
        assertFalse(result);
    }
}
