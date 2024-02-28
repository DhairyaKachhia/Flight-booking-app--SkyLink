package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.objects.Interfaces.iUserProperties;
import com.example.skylink.objects.Implementations.UserProperties;
import org.mindrot.jbcrypt.BCrypt;


public class UserHandler implements IUserHandler {
    public boolean createUser(iUserProperties userProperties, String rePassword) {
        if (userProperties.getPassword().equals(rePassword)) {
            // Hash the rePassword using BCrypt
            String hashedRePassword = BCrypt.hashpw(rePassword, BCrypt.gensalt());
            userProperties.setPassword(hashedRePassword);

            long userId = Services.getUserDatabase().addUser_Auth(userProperties);
            Session.getInstance().setUser_id(userId);

            if (userId != -1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


        public boolean updateUserProfile(iUserProperties userProperties) {
            long user_id = Session.getInstance().getUser_id();
            try{
                if(Services.getUserDatabase().update_user_info(user_id,userProperties)){
                    return true;
                }
                return false;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

    public boolean signinUser(iUserProperties userProperties) {
        String email = userProperties.getEmail();
        String providedPassword = userProperties.getPassword();

        try {
            String password_db = Services.getUserDatabase().findPassword(email);
            if (BCrypt.checkpw(providedPassword, password_db)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
