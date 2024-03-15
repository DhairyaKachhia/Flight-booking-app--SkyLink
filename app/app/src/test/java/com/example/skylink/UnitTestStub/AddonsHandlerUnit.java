package com.example.skylink.UnitTestStub;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.example.skylink.business.Implementations.AddonsHandler;
import com.example.skylink.business.Interface.IAddonsHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;

import java.util.ArrayList;
import java.util.List;

public class AddonsHandlerUnit {


    private Session sessionMock;
    private iFlightInfo flightInfoMock;

    @Before
    public void setUp() {
        sessionMock = mock(Session.class);
        flightInfoMock = mock(iFlightInfo.class);

        flightInfoMock.setEconOrBus("");
        flightInfoMock.setSeatSelected(null);
        flightInfoMock.setFlight(null);

    }

    @Test
    public void testStoreAddons() {
        // Mock flightInfoList
        List<iFlightInfo> flightInfoListMock = new ArrayList<>();
        flightInfoListMock.add(flightInfoMock);

        // Mock Session
        when(sessionMock.getFlightInfoCompleted()).thenReturn(flightInfoListMock);

        // Create an instance of your class
        IAddonsHandler addonsHandler = new AddonsHandler();


        // Call the method
        addonsHandler.storeAddons(1, 2, 1, 0); // Pass sample values for bagNumber, petNumber, wifiOption, wheelchairOption

        // Verify that the method sets the attributes of flightInfo
        verify(flightInfoMock).setBagCount(1);
        verify(flightInfoMock).setPetCount(2);
        verify(flightInfoMock).setWifiOption(1);
        verify(flightInfoMock).setWheelchairOption(0);
    }
}