package com.example.skylink.objects.Implementations;

import com.example.skylink.objects.Interfaces.IUserProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserProperties implements IUserProperties {

    private long user_id;

    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String countryOfOrigin;

    public UserProperties(String fullName, String email, String password, String gender, String address, String phone, String dateOfBirth, String countryOfOrigin) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
    }
    public UserProperties(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
    }

    public UserProperties(){

    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }


    public UserProperties(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String isValidFullName() {
        if (this.getFullName() == null || this.getFullName().isEmpty()) {
            return "Full name cannot be empty";
        }
        return null;
    }

    public String isValidAddress() {
        if (this.getAddress() == null || this.getAddress().isEmpty()) {
            return "Address cannot be empty";
        }
        return null;
    }

    public String isValidEmail() {
        if (this.getEmail() == null || this.getEmail().isEmpty()) {
            return "Email cannot be empty";
        } else if (!this.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Invalid email format";
        }
        return null;
    }

    public String isValidPassword() {
        if (this.getPassword() == null || this.getPassword().isEmpty()) {
            return "Password cannot be empty";
        } else if (this.getPassword().length() < 8) {
            return "Password must be at least 8 characters long";
        }
        return null;
    }

    public String isValidPhone() {
        if (this.getPhone() == null || this.getPhone().isEmpty()) {
            return "Phone cannot be empty";
        } else if (!this.getPhone().matches("[0-9]+")) {
            return "Phone can only contain digits";
        } else if (this.getPhone().length() != 10) {
            return "Phone must be 10 digits long";
        }
        return null;
    }

    public String isValidGender() {
        if (this.getGender() == null || this.getGender().isEmpty()) {
            return "Gender cannot be empty";
        }
        return null;
    }

    public String isValidDateOfBirth() {
        if (this.getDateOfBirth() == null || this.getDateOfBirth().isEmpty()) {
            return "Date of birth cannot be empty";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                sdf.parse(this.getDateOfBirth());
            } catch (ParseException e) {
                return "Date of birth must be in the format YYYY-MM-DD";
            }
        }
        return null;
    }
}
