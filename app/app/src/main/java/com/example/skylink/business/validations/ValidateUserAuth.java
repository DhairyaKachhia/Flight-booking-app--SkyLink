package com.example.skylink.business.validations;

import androidx.core.util.PatternsCompat;       // using this android import to validate email format

public class ValidateUserAuth implements IValidateUserAuth{
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

    @Override
    public String validPassword(String password) {
        String error = "";

        if (password == null || password.isEmpty()) {
            error = "Password cannot be empty";
        }

        return error;
    }

    @Override
    public String validRePassword(String password, String rePassword) {
        String error = "";

        error = validPassword(rePassword);


        if (error.isEmpty()) {

            if (!password.equals(rePassword)) {
                error = "Password do not match";
            }
        }

        return error;
    }

    @Override
    public String validFullname(String name) {
        String error = "";

        if (name == null || name.isEmpty()) {
            error = "Name cannot be empty";
        }

        return error;
    }
}
