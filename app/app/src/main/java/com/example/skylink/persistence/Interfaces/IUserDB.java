package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iUserProperties;

public interface IUserDB {

    long addUser_Auth(iUserProperties user);

    boolean update_user_info(long user_id, iUserProperties user);

    String findPassword(String email);

    IUserDB initialize();

    IUserDB drop();
}
