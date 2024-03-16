package com.example.skylink.UnitTestStub;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.example.skylink.business.Implementations.AddonsHandler;
import com.example.skylink.business.Interface.IAddonsHandler;
import com.example.skylink.objects.Implementations.FlightInfo;
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
        flightInfoMock = mock(FlightInfo.class);

    }

    @Test
    public void testStoreAddonsSuccess() {
        // Mock flightInfoList
        List<iFlightInfo> flightInfoListMock = new ArrayList<>();
        flightInfoListMock.add(flightInfoMock);

        // Mock Session
        when(sessionMock.getFlightInfoCompleted()).thenReturn(flightInfoListMock);

        // Create an instance of addonsHandler with mock session
        IAddonsHandler addonsHandler = new AddonsHandler(sessionMock);


        // Call the method
        addonsHandler.storeAddons(1, 2, 1, 0);

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

        // Mock Session
        when(sessionMock.getFlightInfoCompleted()).thenReturn(flightInfoListMock);

        // Create an instance of addonsHandler with mock session
        IAddonsHandler addonsHandler = new AddonsHandler(sessionMock);


        // Call the method
        addonsHandler.storeAddons(1, 2, 1, 0);

        // verify set methods are not executed since flightInfo list is null
        verifyNoMoreInteractions(flightInfoMock);

    }
}