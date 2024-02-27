package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iUserProperties;

public interface IUserHandler {
    boolean createUser(iUserProperties userProperties, String rePassword);

    boolean updateUserProfile(iUserProperties userProperties);
    boolean signinUser(iUserProperties userProperties);


}
