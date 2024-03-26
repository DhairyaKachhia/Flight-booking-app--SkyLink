package com.example.skylink.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserInfoGenerator {

    public static String[] generateUserInfo() {
        String[] userInfo = new String[9];
        userInfo[0] = generateRandomString(10); // fullName
        userInfo[1] = generateRandomEmail(); // email
        userInfo[2] = generateRandomString(12); // password
        userInfo[3] = generateRandomString(20); // address
        userInfo[4] = generateRandomString(10); // city
        userInfo[5] = generateRandomString(2); // province
        userInfo[6] = generateRandomPhoneNumber(); // phone
        userInfo[7] = generateRandomDateOfBirth(); // dob
        userInfo[8] = generateRandomGender(); // gender
        return userInfo;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    private static String generateRandomEmail() {
        return generateRandomString(10) + "@" + generateRandomString(5) + ".com";
    }

    private static String generateRandomPhoneNumber() {
        return generateRandomNumericString(10);
    }

    private static String generateRandomNumericString(int length) {
        String digits = "0123456789";
        StringBuilder randomNumericString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomNumericString.append(digits.charAt(random.nextInt(digits.length())));
        }
        return randomNumericString.toString();
    }

    private static String generateRandomDateOfBirth() {
        // Generate random date of birth between 01/01/1950 and 01/01/2005
        int minYear = 1950;
        int maxYear = 2005;
        Random random = new Random();
        int year = random.nextInt(maxYear - minYear + 1) + minYear;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; // Assuming all months have 28 days
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    private static String generateRandomGender() {
        String[] genders = {"M", "F", "Other"}; // Add more options if needed
        Random random = new Random();
        return genders[random.nextInt(genders.length)];
    }
}
