package com.example.skylink.IntegrationTest;

import static org.junit.Assert.*;

import com.example.skylink.TestUtils.TestUtils;
import com.example.skylink.business.Implementations.PaymentHandler;
import com.example.skylink.objects.Implementations.TripInvoice;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class PaymentHandlerIntegrated {

    private PaymentHandler paymentHandler;
    private File tempDB;
    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for Passenger Database");
        this.tempDB = TestUtils.copyDB();
        this.paymentHandler = new PaymentHandler(true);
        assertNotNull(this.paymentHandler);
    }

    @Test
    public void testAddPayment_Successful() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(1, 100);

        // When
        boolean result = paymentHandler.addPayment(tripInvoice, 1);

        // Then
        assertTrue(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_InvalidSessionUserID() {
        // Given
        TripInvoice tripInvoice = new TripInvoice(2L, 150);

        // When
        boolean result = paymentHandler.addPayment(tripInvoice, 1L);    // Different session ID

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

    @Test
    public void testAddPayment_NullTripInvoice() {
        // When
        boolean result = paymentHandler.addPayment(null, 1L);

        // Then
        assertFalse(result);
        // Add additional assertions or verifications if needed
    }

}

