package com.example.skylink.objects;

import android.content.Context;

import com.example.skylink.business.Interface.iPayment;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session implements ISession {

    private static Session instance;
    private String username;
    private String email;

    // Temporary storage for flights and bookings
    private iFlightSearch flightSearch;

    private int totalPrice;

    private List<iPassengerData> passengerData;

    private iPayment pay;

    public iPayment getPay() {
        return pay;
    }

    public void setPay(iPayment pay) {
        this.pay = pay;
    }

    private HashMap<iPassengerData, String> seatMap;

    private Map<String, String> priceType;

    public Map<String, String> getpriceType() {
        return priceType;
    }

    public void setpriceType(String key, String value) {
        if (priceType == null) {
            priceType = new HashMap<>();
        }
        priceType.put(key, value);
    }


    public HashMap<iPassengerData, String> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(HashMap<iPassengerData, String> seatMap) {
        this.seatMap = seatMap;
    }

    private HashMap<String, List<List<iFlight>>> selectedFlights;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;




    HashMap<String, List<List<List<iFlight>>>> flightPathResults;

    private long user_id;

    private Session() {
        // Private constructor to prevent instantiation

    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }



    public List<iPassengerData> getPassengerData() {
        return passengerData;
    }

    public void setPassengerData(List<iPassengerData> passengerData) {
        this.passengerData = passengerData;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public iFlightSearch getFlightSearch() {
        return flightSearch;
    }

    public void setFlightSearch(iFlightSearch flightSearch) {
        this.flightSearch = flightSearch;
    }

    @Override
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int getTotalPrice() {
        return totalPrice;
    }

    public HashMap<String, List<List<List<iFlight>>>> getFlightPathResults() {
        return flightPathResults;
    }

    public void setFlightPathResults(HashMap<String, List<List<List<iFlight>>>> flightPathResults) {
        this.flightPathResults = flightPathResults;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, List<List<iFlight>>> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlights(HashMap<String, List<List<iFlight>>> selectedFlights) {
        this.selectedFlights = selectedFlights;
    }


    /*
    *   Storing Payment info:
    *      cardNum, expiryDate, cvv, cardholderName, billingAddress
    */

    private String  cardNum, expiryDate, cvv, cardholderName, billingAddress;

    @Override
    public String getCardNum() {
        return cardNum;
    }

    @Override
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String getCvv() {
        return cvv;
    }

    @Override
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String getCardholderName() {
        return cardholderName;
    }

    @Override
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    @Override
    public String getBillingAddress() {
        return billingAddress;
    }

    @Override
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
