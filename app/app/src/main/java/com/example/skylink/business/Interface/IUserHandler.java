package com.example.skylink.business.Interface;

import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.objects.Interfaces.IUserProperties;

public interface IUserHandler {
    void createUser(IUserProperties userProperties, String rePassword) throws UserHandler.UserCreationException;

    boolean updateUserProfile(IUserProperties userProperties);
    boolean signinUser(IUserProperties userProperties);

    long getUserIdByEmail(String email);

    IUserProperties getUserByEmail(String email);

}
