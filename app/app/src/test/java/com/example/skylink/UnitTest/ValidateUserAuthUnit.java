package com.example.skylink.UnitTest;

import static org.junit.Assert.*;

import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;

import org.junit.Test;

public class ValidateUserAuthUnit {

    @Test
    public void validEmail_emptyEmail_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validEmail("");        // empty email field
        assertEquals("Email cannot be empty", error);
    }

    @Test
    public void validEmail_invalidEmail_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validEmail("invalidemail");        // invalid format
        assertEquals("Invalid email", error);
    }

    @Test
    public void validEmail_validEmail() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validEmail("validemail@example.com");      // valid email
        assertEquals("", error);
    }

    @Test
    public void validPassword_emptyPassword_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validPassword("");         // empty password field
        assertEquals("Password cannot be empty", error);
    }

    @Test
    public void validPassword_nonEmptyPassword_returnsEmptyString() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validPassword("password123");      // valid password
        assertEquals("", error);
    }

    @Test
    public void validRePassword_emptyRePassword_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validRePassword("password", "");    // empty reEnter password field
        assertEquals("Password cannot be empty", error);
    }

    @Test
    public void validRePassword_nonMatchingPasswords_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validRePassword("password", "differentpassword");      // password mismatch
        assertEquals("Password do not match", error);
    }

    @Test
    public void validRePassword_matchingPasswords_returnsEmptyString() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validRePassword("password", "password");   // password matches
        assertEquals("", error);
    }

    @Test
    public void validFullname_emptyName_returnsErrorMessage() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validFullname("");         // empty fullname field
        assertEquals("Name cannot be empty", error);
    }

    @Test
    public void validFullname_nonEmptyName_returnsEmptyString() {
        IValidateUserAuth validator = new ValidateUserAuth();
        String error = validator.validFullname("Hello world");     // valid fullname
        assertEquals("", error);
    }
}