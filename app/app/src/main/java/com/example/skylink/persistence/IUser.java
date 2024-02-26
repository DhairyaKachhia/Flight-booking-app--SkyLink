package com.example.skylink.persistence;

import com.example.skylink.objects.UserProperties;

public interface IUser {

    /**
     * Initializes the database.
     */
    IUser initialize();

     long addUser_Auth(UserProperties user);

     boolean update_user_info(long user_id,UserProperties user);

     String findPassword(String email);


    /**
     * Drops the database.
     */
    IUser drop();

}