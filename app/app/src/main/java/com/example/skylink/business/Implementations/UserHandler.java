package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.objects.Interfaces.iUserProperties;
import com.example.skylink.persistence.Interfaces.IUserDB;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHandler implements IUserHandler {
    private IUserDB userDB;

    public UserHandler(IUserDB userDB) {
        this.userDB = userDB;
    }

    public boolean createUser(iUserProperties userProperties, String rePassword) {
        // Check if all user properties are valid
        if (isValidUserProperties(userProperties) && userProperties.getPassword().equals(rePassword)) {
            // Hash the rePassword using BCrypt
            String hashedRePassword = BCrypt.hashpw(rePassword, BCrypt.gensalt());
            userProperties.setPassword(hashedRePassword);

            // Attempt to add the user to the database
            long userId = userDB.addUser_Auth(userProperties);
            Session.getInstance().setUser_id(userId);

            // Check if the user was successfully added to the database
            return userId != -1;
        } else {
            // Invalid user properties or mismatched passwords
            return false;
        }
    }

    // Additional method to check the validity of user properties
    private boolean isValidUserProperties(iUserProperties userProperties) {
        return userProperties != null &&
                isValidFullName(userProperties.getFullName()) &&
                isValidEmail(userProperties.getEmail()) &&
                isValidPassword(userProperties.getPassword());
    }

    // Updated validation method for email using Apache Commons Validator
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Define a simple regular expression for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, false otherwise
        return matcher.matches();
    }


    // Implement validation methods for full name, email, and password as needed
    private boolean isValidFullName(String fullName) {
        // Implement validation logic for full name
        return fullName != null && !fullName.isEmpty();
    }

    private boolean isValidPassword(String password) {
        // Implement validation logic for password
        return password != null && password.length() >= 8; // or any other validation rules
    }

    public boolean updateUserProfile(iUserProperties userProperties) {
        // Validate user properties
        if (isValidUserProperties(userProperties)) {
            long user_id = Session.getInstance().getUser_id();
            try {
                if (userDB.update_user_info(user_id, userProperties)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean signinUser(iUserProperties userProperties) {
        // Validate user properties
        if (isValidUserProperties(userProperties)) {
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
        }

        return false;
    }
}
