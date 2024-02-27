package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iUserProperties;

public interface IUserHSQLDB {

    /**
     * Initializes the database.
     */
    IUserHSQLDB initialize();

     long addUser_Auth(iUserProperties user);

     boolean update_user_info(long user_id,iUserProperties user);

     String findPassword(String email);


    /**
     * Drops the database.
     */
    IUserHSQLDB drop();

}