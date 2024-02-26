package com.example.skylink.business;

import com.example.skylink.objects.UserProperties;
import org.mindrot.jbcrypt.BCrypt;
import  com.example.skylink.persistence.IUser;


public class UserHandler implements IUserHandler {
    public boolean createUser(UserProperties userProperties, String rePassword) {
        if (userProperties.getPassword().equals(rePassword)) {
            // Hash the rePassword using BCrypt
            String hashedRePassword = BCrypt.hashpw(rePassword, BCrypt.gensalt());
            userProperties.setPassword(hashedRePassword);

            long userId = IUser.addUser_Auth(userProperties);
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


        public boolean updateUserProfile(UserProperties userProperties) {
            long user_id = Session.getInstance().getUser_id();
            try{
                if(IUser.update_user_info(user_id,userProperties)){
                    return true;
                }
                return false;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

    public boolean signinUser(UserProperties userProperties) {
        String email = userProperties.getEmail();
        String providedPassword = userProperties.getPassword();

        try {
            String password_db = IUser.findPassword(email);
            if (BCrypt.checkpw(providedPassword, password_db)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
