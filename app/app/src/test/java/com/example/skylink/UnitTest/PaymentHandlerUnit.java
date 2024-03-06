package com.example.skylink.UnitTest;

import static org.junit.Assert.*;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PaymentHandler;
import com.example.skylink.objects.Implementations.TripInvoice;
import com.example.skylink.objects.Session;
import com.example.skylink.persistence.Implementations.stub.PaymentStub;
import org.junit.Before;
import org.junit.Test;

public class PaymentHandlerUnit {

    private PaymentHandler paymentHandler;

    @Before
    public void setUp() {
        PaymentStub paymentStub = new PaymentStub();
        paymentHandler = new PaymentHandler(paymentStub);
        // Set up any other necessary dependencies
    }

    @Test
    public void testAddPayment_Successful() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(1L, 100);

        Session.getInstance().setUser_id(1L);

        // When
        boolean result = paymentHandler.addPayment(tripInvoice);

        // Then
        assertTrue(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_InvalidUserID() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(2L, 150); // Different user ID

        // When
        boolean result = paymentHandler.addPayment(tripInvoice);

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_InvalidSessionUserID() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(1L, 200);
        // Set a different user ID in the session
        Session.getInstance().setUser_id(2L);

        // When
        boolean result = paymentHandler.addPayment(tripInvoice);

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_NullSessionUser() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(1L, 250);
        // Set session user to null
        Session.getInstance().setUser_id(0);

        // When
        boolean result = paymentHandler.addPayment(tripInvoice);

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_NullTripInvoice() {
        // When
        boolean result = paymentHandler.addPayment(null);

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

    // Add more test cases for various scenarios and edge cases

}

