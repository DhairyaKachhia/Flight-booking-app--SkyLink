package com.example.skylink.business.validations;

public interface IValidatePassgnData {

    // PassengerData fields
    String validTitle(String title);
    String validFirstname (String firstname);
    String validLastname (String lastname);
    String validPhoneNum (String phoneNum);
    String validEmail (String email);


}
