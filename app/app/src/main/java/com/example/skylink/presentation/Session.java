package com.example.skylink.presentation;

import android.content.Context;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session implements ISession {

    private static Session instance;
    private String username;
    private String email;
    private iFlightSearch flightSearch;
    private int outboundPrice, inboundPrice, addonsPrice = 0;
    private int totalPrice, flightTotalPrice = 0;
    private List<iPassengerData> passengerData;


    private Map<String, String> priceType;

    public Map<String, String> getpriceType() {
        return priceType;
    }
    private Context context;
    HashMap<String, List<List<List<iFlight>>>> flightPathResults;
    private String  cardNum, expiryDate, cvv, cardholderName, billingAddress;

    public void setpriceType(String key, String value) {
        if (priceType == null) {
            priceType = new HashMap<>();
        }
        priceType.put(key, value);
    }

    private HashMap<String, List<List<iFlight>>> selectedFlights;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

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
    public int getOutboundPrice() {
        return outboundPrice;
    }

    @Override
    public void setOutboundPrice(int outboundPrice) {
        this.outboundPrice = outboundPrice;
    }

    @Override
    public int getInboundPrice() {
        return inboundPrice;
    }

    @Override
    public void setInboundPrice(int inboundPrice) {
        this.inboundPrice = inboundPrice;
    }

    @Override
    public int getAddonsPrice() {
        return addonsPrice;
    }

    @Override
    public void setAddonsPrice(int addonsPrice) {
        this.addonsPrice = addonsPrice;
    }

    @Override
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int getTotalPrice() {
        totalPrice = outboundPrice + inboundPrice + addonsPrice;
        return totalPrice;
    }

    @Override
    public int getFlightTotalPrice() {
        flightTotalPrice = outboundPrice + inboundPrice;
        return flightTotalPrice;
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

    private List<iFlightInfo> flightInfoCompleted;

    public List<iFlightInfo> getFlightInfoCompleted() {
        return flightInfoCompleted;
    }

    public void setFlightInfoCompleted(iFlightInfo flightInfoCompleted) {
        if (this.flightInfoCompleted == null) {
            this.flightInfoCompleted = new ArrayList<>();
        }
        this.flightInfoCompleted.add(flightInfoCompleted);
    }





    /*
    *   Storing Payment info:
    *      cardNum, expiryDate, cvv, cardholderName, billingAddress
    */

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
