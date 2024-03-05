package com.example.skylink;

import static org.junit.Assert.*;

import com.example.skylink.business.Implementations.PaymentHandler;
import com.example.skylink.business.Interface.IPaymentHandler;
import com.example.skylink.objects.Implementations.TripInvoice;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.persistence.Implementations.hsqldb.PaymentStub;
import com.example.skylink.persistence.Interfaces.IPaymentDB;

import org.junit.Before;
import org.junit.Test;

public class PaymentHandlerTest {

    private IPaymentDB paymentDB;
    private IPaymentHandler paymentHandler;
    private ITripInvoice tripInvoice;
    @Before
    public void setUp() throws Exception {
        paymentDB = new PaymentStub();
        paymentHandler = new PaymentHandler(paymentDB);
    }

    @Test
    public void testAddPayment_Success() {
        long sessionUserID = 1;
        tripInvoice = new TripInvoice(1, 500);

        // When userID stored in session and userID stored in TripInvoice are equal, the data should
        // be saved in the database.
        assertTrue(paymentHandler.addPayment(tripInvoice, sessionUserID));

    }

    @Test
    public void testAddPayment_Fail() {
        long sessionUserID = 2;
        tripInvoice = new TripInvoice(5, 1400);

        // When userID stored in session and userID stored in TripInvoice are not equal, the data
        // should not be saved in the database.
        assertFalse(paymentHandler.addPayment(tripInvoice, sessionUserID));

    }
}