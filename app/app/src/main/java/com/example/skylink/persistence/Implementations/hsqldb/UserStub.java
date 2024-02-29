package com.example.skylink.persistence.Implementations.hsqldb;

import com.example.skylink.objects.Interfaces.iUserProperties;
import com.example.skylink.persistence.Interfaces.IUserDB;


import java.util.HashMap;

public class UserStub implements IUserDB {
    private final HashMap<Long, iUserProperties> users;
    private long idCounter = 1;

    public UserStub() {
        this.users = new HashMap<>();
    }

    @Override
    public long addUser_Auth(iUserProperties user) {
        long userId = idCounter++;
        users.put(userId, user);
        return userId;
    }

    @Override
    public boolean update_user_info(long user_id, iUserProperties user) {
        iUserProperties storedUser = users.get(user_id);
        if (storedUser != null) {
            // Update user information
            storedUser.setFullName(user.getFullName());
            storedUser.setEmail(user.getEmail());
            storedUser.setPassword(user.getPassword());
            storedUser.setGender(user.getGender());
            storedUser.setAddress(user.getAddress());
            storedUser.setPhone(user.getPhone());
            storedUser.setDateOfBirth(user.getDateOfBirth());
            storedUser.setCountryOfOrigin(user.getCountryOfOrigin());
            return true;
        }
        return false; // User not found
    }

    @Override
    public String findPassword(String email) {
        for (iUserProperties user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user.getPassword();
            }
        }
        return null; // User not found
    }

    @Override
    public IUserDB initialize() {
        users.clear();
        idCounter = 1;
        return this;
    }

    @Override
    public IUserDB drop() {
        users.clear();
        idCounter = 1;
        return this;
    }
}
