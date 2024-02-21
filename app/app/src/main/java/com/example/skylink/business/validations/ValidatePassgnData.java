package com.example.skylink.business.validations;

import android.util.Patterns;

public class ValidatePassgnData implements IValidatePassgnData {

    @Override
    public String validTitle(String title) {
        String error = "";

        if (title == null || title.isEmpty()) {
            error = "Title cannot be empty";
        }

        return error;
    }

    @Override
    public String validFirstname(String firstname) {
        String error = "";

        if (firstname == null || firstname.isEmpty()) {
            error = "First name cannot be empty";
        }

        return error;
    }

    @Override
    public String validLastname(String lastname) {
        String error = "";

        if (lastname == null || lastname.isEmpty()) {
            error = "Last name cannot be empty";
        }

        return error;
    }

    @Override
    public String validPhoneNum(String phoneNum) {
        String error = "";

        if (phoneNum == null || phoneNum.isEmpty()) {
            error = "Phone number cannot be empty";
        } else if (!android.util.Patterns.PHONE.matcher(phoneNum).matches()) {
            error = "Invalid phone number";
        }

        return error;
    }

    @Override
    public String validEmail(String email) {
        String error = "";

        if (email == null || email.isEmpty()) {
            error = "Email cannot be empty";
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = "Invalid email";
        }

        return error;
    }
}
