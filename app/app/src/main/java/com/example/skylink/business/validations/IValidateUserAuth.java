package com.example.skylink.business.validations;

public interface IValidateUserAuth {

    // sign-in and sign-up field
    String validEmail(String email);
    String validPassword(String password);
    String validRePassword(String password, String rePassword);
    String validFullname(String name);
}
