package com.example.skylink;

import static org.junit.Assert.*;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.persistence.Interfaces.IUserDB;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.iUserProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserHandlerIntegratedTest {

    private UserHandler userHandler;
    private IUserDB userDatabase;

    @Before
    public void setUp() {
        userDatabase = new InMemoryUserDB();
        userHandler = new UserHandler(userDatabase);
    }

    @After
    public void tearDown() {
        ((InMemoryUserDB) userDatabase).clear();
    }

    @Test
    public void testCreateUser() {
        boolean result = userHandler.createUser(createUserProperties(), "password");
        assertTrue(result);
    }

    @Test
    public void testSigninUser() {
        // Create user
        UserProperties user = createUserProperties();
        String email = user.getEmail();
        String password = user.getPassword();

        // Add user info to db
        userDatabase.addUser_Auth(user);

        // sign in
        boolean result = userHandler.signinUser(createUserProperties());

        assertTrue(result);
    }

    private UserProperties createUserProperties() {

        return new UserProperties("yiming", "yiming@gmail.com", "password", "male", "winnipeg", "2041234567", "01-01-1990", "Canada");
    }

    private static class InMemoryUserDB implements IUserDB {
        private Map<String, String> userCredentials = new HashMap<>();

        @Override
        public long addUser_Auth(iUserProperties user) {
            // Store email and password
            userCredentials.put(user.getEmail(), BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            return 1; // Return user id.
        }

        @Override
        public boolean update_user_info(long user_id, iUserProperties user) {
            //null
            return true;
        }

        @Override
        public String findPassword(String email) {

            return userCredentials.get(email);
        }

        @Override
        public IUserDB initialize() {
            // initialize db
            return this;
        }

        @Override
        public IUserDB drop() {
            clear();
            return this;
        }

        public void clear() {
            userCredentials.clear();
        }
    }
}
