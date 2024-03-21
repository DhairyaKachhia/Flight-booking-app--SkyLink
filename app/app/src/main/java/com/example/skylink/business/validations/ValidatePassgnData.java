package com.example.skylink.business.validations;

import androidx.core.util.PatternsCompat;       // using this android import to validate email format
import java.util.regex.Pattern;

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

        String regex = "\\d{10}";   // checks for 10 digit number

        Pattern phonePattern = Pattern.compile(regex);

        if (phoneNum == null || phoneNum.isEmpty()) {
            error = "Phone number cannot be empty";
        } else if (!phonePattern.matcher(phoneNum).matches()) {
            error = "Invalid phone number";
        }

        return error;
    }

    @Override
    public String validEmail(String email) {
        String error = "";

        if (email == null || email.isEmpty()) {
            error = "Email cannot be empty";
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            error = "Invalid email";
        }

        return error;
    }
}
