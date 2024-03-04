package com.example.skylink.business.Implementations;

import android.util.Log;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IPaymentHandler;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.persistence.Interfaces.IPaymentDB;

public class PaymentHandler implements IPaymentHandler {
    @Override
    public boolean addPayment(ITripInvoice tripInvoice) {

        boolean addSuccess = false;

        long userID = tripInvoice.getUserID();

        if (Session.getInstance().getUser_id() == userID) {
            addSuccess = Services.getPaymentDatabase().addPayment(tripInvoice);
        } else {
            Log.d("UserID incorrect", "curr userID: " + Session.getInstance().getUser_id() + ", given: " + userID);
        }

        return addSuccess;
    }
}
