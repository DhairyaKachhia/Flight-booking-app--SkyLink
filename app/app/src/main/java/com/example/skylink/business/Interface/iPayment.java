package com.example.skylink.business.Interface;

import java.util.HashMap;

public interface iPayment {
    boolean makePayment(String cardNumber, String expirationDate, String cvv, double amount);
    HashMap<String, Object> generateInvoice();
}
