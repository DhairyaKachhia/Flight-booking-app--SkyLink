package com.example.skylink.UnitTestStub;

import static org.junit.Assert.*;

import com.example.skylink.business.validations.ValidatePassgnData;
import com.example.skylink.business.validations.IValidatePassgnData;

import org.junit.Test;

public class ValidatePassgnDataUnit {

    @Test
    public void validTitle_emptyTitle_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validTitle("");        // empty title field
        assertEquals("Title cannot be empty", error);
    }

    @Test
    public void validTitle_nonEmptyTitle_returnsEmptyString() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validTitle("Mr.");     // valid title
        assertEquals("", error);
    }

    @Test
    public void validFirstname_emptyFirstname_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validFirstname("");        // empty first name field
        assertEquals("First name cannot be empty", error);
    }

    @Test
    public void validFirstname_nonEmptyFirstname_returnsEmptyString() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validFirstname("Hello");    // valid first name
        assertEquals("", error);
    }

    @Test
    public void validLastname_emptyLastname_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validLastname("");         // empty last name field
        assertEquals("Last name cannot be empty", error);
    }

    @Test
    public void validLastname_nonEmptyLastname_returnsEmptyString() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validLastname("world");      // valid last name
        assertEquals("", error);
    }

    @Test
    public void validPhoneNum_emptyPhoneNum_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validPhoneNum("");         // empty phone number field
        assertEquals("Phone number cannot be empty", error);
    }

    @Test
    public void validPhoneNum_invalidPhoneNum_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validPhoneNum("12345");    // phone number does not have 10 digits
        assertEquals("Invalid phone number", error);
    }

    @Test
    public void validPhoneNum_validPhoneNum_returnsEmptyString() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validPhoneNum("1234567890");       // valid phone number with 10 digits
        assertEquals("", error);
    }

    @Test
    public void validEmail_emptyEmail_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validEmail("");            // empty email field
        assertEquals("Email cannot be empty", error);
    }

    @Test
    public void validEmail_invalidEmail_returnsErrorMessage() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validEmail("invalidemail");        // invalid email format
        assertEquals("Invalid email", error);
    }

    @Test
    public void validEmail_validEmail_returnsEmptyString() {
        IValidatePassgnData validator = new ValidatePassgnData();
        String error = validator.validEmail("validemail@example.com");      // valid email format
        assertEquals("", error);
    }


}