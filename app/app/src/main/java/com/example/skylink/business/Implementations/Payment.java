package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.iPayment;

import java.util.HashMap;

public class Payment implements iPayment {
    private String cardNumber;
    private String expirationDate;
    private String cvv;


    @Override
    public boolean makePayment(String cardNumber, String expirationDate, String cvv, double amount) {
        return true;
    }

    @Override
    public HashMap<String, Object> generateInvoice() {

        return null;
    }
}
