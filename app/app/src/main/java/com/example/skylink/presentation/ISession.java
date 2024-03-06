package com.example.skylink.presentation;
import android.content.Context;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISession {

    void setTotalPrice(int totalPrice);

    int getTotalPrice();

    Map<String, String> getpriceType();

    void setpriceType(String key, String value);

    HashMap<iPassengerData, String> getSeatMap();

    void setSeatMap(HashMap<iPassengerData, String> seatMap);

    Context getContext();

    void setContext(Context context);

    long getUser_id();

    void setUser_id(long user_id);

    List<iPassengerData> getPassengerData();

    void setPassengerData(List<iPassengerData> passengerData);

    iFlightSearch getFlightSearch();

    void setFlightSearch(iFlightSearch flightSearch);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    HashMap<String, List<List<List<iFlight>>>> getFlightPathResults();

    void setFlightPathResults(HashMap<String, List<List<List<iFlight>>>> flightPathResults);

    HashMap<String, List<List<iFlight>>> getSelectedFlights();

    void setSelectedFlights(HashMap<String, List<List<iFlight>>> selectedFlights);

    String getCardNum();

    void setCardNum(String cardNum);

    String getExpiryDate();

    void setExpiryDate(String expiryDate);

    String getCvv();

    void setCvv(String cvv);

    String getCardholderName();

    void setCardholderName(String cardholderName);

    String getBillingAddress();

    void setBillingAddress(String billingAddress);
}
