package com.example.skylink.business.Implementations;

import android.util.Log;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IPaymentHandler;
import com.example.skylink.persistence.Interfaces.IPaymentDB;

public class PaymentHandler implements IPaymentHandler {
    @Override
    public boolean addPayment(long userID, int totalAmount) {

        boolean addSuccess = false;

        if (Session.getInstance().getUser_id() == userID) {
            addSuccess = Services.getPaymentDatabase().addPayment(userID, totalAmount);
        } else {
            Log.d("UserID incorrect", "curr userID: " + Session.getInstance().getUser_id() + ", given: " + userID);
        }

        return addSuccess;
    }
}
