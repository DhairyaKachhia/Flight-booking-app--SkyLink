package com.example.skylink.business;

import com.example.skylink.objects.UserProperties;

public interface IUserHandler {
    boolean createUser(UserProperties userProperties, String rePassword);

    boolean updateUserProfile(UserProperties userProperties);
    boolean signinUser(UserProperties userProperties);


}
