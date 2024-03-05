package com.example.skylink.business.Implementations;

import android.util.Log;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IPaymentHandler;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.persistence.Interfaces.IPaymentDB;

public class PaymentHandler implements IPaymentHandler {

    private IPaymentDB paymentDB;

    public PaymentHandler(IPaymentDB paymentDB) {
        this.paymentDB = paymentDB;
    }

    @Override
    public boolean addPayment(ITripInvoice tripInvoice, long sessionUserID) {

        boolean addSuccess = false;

        long userID = tripInvoice.getUserID();

        if (sessionUserID == userID) {
            addSuccess = paymentDB.addPayment(tripInvoice);
        }

        return addSuccess;
    }
}
