package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.business.validations.IValidateUserProperties;
import com.example.skylink.business.validations.ValidateUserProperties;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.presentation.Session;
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

        Session.getInstance().getUserProperties().setUser_id(userId);
    }

    public String isValidUserPropertiesForCreation(IUserProperties userProperties) {
        IValidateUserProperties validator = new ValidateUserProperties();

        String fullNameValidation = validator.validFullname(userProperties.getFullName());
        if (fullNameValidation != null && !fullNameValidation.isEmpty()) {
            return fullNameValidation;
        }

        String emailValidation = validator.validEmail(userProperties.getEmail());
        if (emailValidation != null && !emailValidation.isEmpty()) {
            return emailValidation;
        }

        String passwordValidation = validator.validPassword(userProperties.getPassword());
        if (passwordValidation != null && !passwordValidation.isEmpty()) {
            return passwordValidation;
        }
        
        return null;
    }

    public String isValidUserPropertiesForUpdate(IUserProperties userProperties) {

        IValidateUserProperties validator = new ValidateUserProperties();

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
        String phoneValidation = validator.validPhone(userProperties.getPhone());
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

        if (userProperties == null) {
            return false;
        }

        // Already checked during sign up activity but doesn't hurt to validate
        String validationMessage = isValidUserPropertiesForUpdate(userProperties);
        if (validationMessage != null) {
            return false;
        }

        long user_id = Session.getInstance().getUserProperties().getUser_id();

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
    public long getUserIdByEmail(String email) {

        try {
            long userId = userDB.getUserId(email);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
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