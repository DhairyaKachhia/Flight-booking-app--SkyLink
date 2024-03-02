package com.example.skylink.persistence.Interfaces;

public interface IPaymentDB {

    boolean addPayment(long userID, int totalAmount);
    IPaymentDB initialize();

    IPaymentDB drop();
}
