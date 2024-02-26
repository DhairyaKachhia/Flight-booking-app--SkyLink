package com.example.skylink.persistence;

public interface IUser {

    /**
     * Initializes the database.
     */
    IUser initialize();

    /**
     * Drops the database.
     */
    IUser drop();

}