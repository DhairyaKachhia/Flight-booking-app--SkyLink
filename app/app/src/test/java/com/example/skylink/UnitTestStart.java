package com.example.skylink;

import com.example.skylink.UnitTest.PassengerDataManagerUnit;
import com.example.skylink.UnitTest.AirportPathUnit;
import com.example.skylink.UnitTest.PaymentHandlerUnit;
import com.example.skylink.UnitTest.PlaneConfigurationUnit;
import com.example.skylink.UnitTest.FlightSortingUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AirportPathUnit.class,
        PassengerDataManagerUnit.class,
        PlaneConfigurationUnit.class,
        FlightSortingUnit.class,
        PaymentHandlerUnit.class
})
public class UnitTestStart {
    // This class can remain empty
    // It's used only as a container for the suite
}
