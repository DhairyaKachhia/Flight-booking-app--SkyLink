package com.example.skylink.business.Implementations;

import android.util.Log;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.IPaymentHandler;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.objects.Session;
import com.example.skylink.persistence.Interfaces.IPaymentDB;

public class PaymentHandler implements IPaymentHandler {
    IPaymentDB paymentDB;
    public PaymentHandler(boolean forProduction){
        this.paymentDB = Services.getPaymentDatabase();

    }
    public PaymentHandler(IPaymentDB paymentDB){
        this.paymentDB = paymentDB;
    }
    @Override
    public boolean addPayment(ITripInvoice tripInvoice) {
        if (tripInvoice == null) {
            return false;
        }
        boolean addSuccess = false;

        long userID = tripInvoice.getUserID();

        if (Session.getInstance().getUser_id() == userID) {
            addSuccess = this.paymentDB.addPayment(tripInvoice);
        }

        return addSuccess;
    }
}
