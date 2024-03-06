package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.objects.Session;
import com.example.skylink.persistence.Interfaces.IUserDB;
import org.mindrot.jbcrypt.BCrypt;

public class UserHandler implements IUserHandler {
    private IUserDB userDB;

    public UserHandler(boolean forProduction){
        this.userDB = Services.getUserDatabase();
    }

    public UserHandler(IUserDB userDB) {
        this.userDB = userDB;
    }

    public void createUser(IUserProperties userProperties, String rePassword) throws UserCreationException {
        
        String validationMessage = isValidUserPropertiesForCreation(userProperties);
        if (validationMessage != null) {
            throw new UserCreationException(validationMessage);
        }

        if (!userProperties.getPassword().equals(rePassword)) {
            throw new UserCreationException("Passwords do not match");
        }

        String hashedRePassword = BCrypt.hashpw(rePassword, BCrypt.gensalt());
        userProperties.setPassword(hashedRePassword);

        long userId = userDB.addUser_Auth(userProperties);

        if (userId == -1) {
            throw new UserCreationException("Unable to create new user");
        }

        Session.getInstance().setUser_id(userId);
    }

    public String isValidUserPropertiesForCreation(IUserProperties userProperties) {

        String fullNameValidation = userProperties.isValidFullName();
        if (fullNameValidation != null) {
            return fullNameValidation;
        }

        String emailValidation = userProperties.isValidEmail();
        if (emailValidation != null) {
            return emailValidation;
        }

        String passwordValidation = userProperties.isValidPassword();
        if (passwordValidation != null) {
            return passwordValidation;
        }
        
        return null;
    }

    public String isValidUserPropertiesForUpdate(IUserProperties userProperties) {
        String baseUserPropertiesValidation = isValidUserPropertiesForCreation(userProperties);
        if (baseUserPropertiesValidation != null) {
            return baseUserPropertiesValidation;
        }

        // Validate address
        String addressValidation = userProperties.isValidAddress();
        if (addressValidation != null) {
            return addressValidation;
        }

        // Validate phone
        String phoneValidation = userProperties.isValidPhone();
        if (phoneValidation != null) {
            return phoneValidation;
        }

        // Validate gender
        String genderValidation = userProperties.isValidGender();
        if (genderValidation != null) {
            return genderValidation;
        }

        // Validate date of birth
        String dobValidation = userProperties.isValidDateOfBirth();
        if (dobValidation != null) {
            return dobValidation;
        }

        return null;
    }

    public boolean updateUserProfile(IUserProperties userProperties) {
        // Already checked but doesn't hurt to validate
        String validationMessage = isValidUserPropertiesForUpdate(userProperties);
        if (validationMessage != null) {
            return false;
        }

        long user_id = Session.getInstance().getUser_id();

        try {
            if (userDB.update_user_info(user_id, userProperties)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signinUser(IUserProperties userProperties) {
        // Validate user properties
        String validationMessage = isValidUserPropertiesForCreation(userProperties);
        if (validationMessage != null) {
            return false;
        }
        
        String email = userProperties.getEmail();
        String providedPassword = userProperties.getPassword();

        try {
            String password_db = userDB.findPassword(email);
            if (BCrypt.checkpw(providedPassword, password_db)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public IUserProperties getUserByEmail(String email) {
        return userDB.getUserByEmail(email);
    }

    public class UserCreationException extends Exception {
        public UserCreationException(String message) {
            super(message);
        }
    }
}