package com.example.skylink.business;

public interface IUsesHandler {
    boolean createUser(String fullName, String email, String password, String rePassword);

    boolean updateUserProfile(String gender, String address, String phone, String dateOfBirth, String countryOfOrigin);
}
