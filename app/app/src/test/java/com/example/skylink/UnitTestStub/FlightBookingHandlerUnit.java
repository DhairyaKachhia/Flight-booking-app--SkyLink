package com.example.skylink.UnitTestStub;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Implementations.FlightInfo;
import com.example.skylink.objects.Interfaces.iFlightInfo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FlightBookingHandlerUnit {

    private iFlightInfo flightInfoMock;

    @Before
    public void setUp() {
        flightInfoMock = mock(FlightInfo.class);

    }

    @Test
    public void testStoreAddonsSuccess() {
        // Mock flightInfoList
        List<iFlightInfo> flightInfoListMock = new ArrayList<>();
        flightInfoListMock.add(flightInfoMock);

        iFlightBookingHandler flightBookingHandler = new FlightBookingHandler();

        // Call the method
        flightBookingHandler.storeAddons(1, 2, 1, 0, flightInfoListMock);

        // Verify that the method sets the attributes of flightInfo
        verify(flightInfoMock).setBagCount(1);
        verify(flightInfoMock).setPetCount(2);
        verify(flightInfoMock).setWifiOption(1);
        verify(flightInfoMock).setWheelchairOption(0);

    }

    @Test
    public void testStoreAddonsFail() {
        // Mock flightInfoList
        List<iFlightInfo> flightInfoListMock = null;

        iFlightBookingHandler flightBookingHandler = new FlightBookingHandler();

        // Call the method
        flightBookingHandler.storeAddons(1, 2, 1, 0, flightInfoListMock);

        // verify set methods are not executed since flightInfo list is null
        verifyNoMoreInteractions(flightInfoMock);

    }

}