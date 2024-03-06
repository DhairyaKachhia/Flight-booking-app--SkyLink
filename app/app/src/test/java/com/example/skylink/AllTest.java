package com.example.skylink;

import com.example.skylink.IntegrationTest.AirportPathIntegrated;
import com.example.skylink.IntegrationTest.FlightSortingIntegratedTest;
import com.example.skylink.IntegrationTest.PassengerDataManagerIntegrated;
import com.example.skylink.IntegrationTest.PaymentHandlerIntegrated;
import com.example.skylink.IntegrationTest.PlaneConfigurationIntegrated;
import com.example.skylink.IntegrationTest.UserHandlerIntegrated;
import com.example.skylink.UnitTest.PassengerDataManagerUnit;
import com.example.skylink.UnitTest.AirportPathUnit;
import com.example.skylink.UnitTest.PaymentHandlerUnit;
import com.example.skylink.UnitTest.PlaneConfigurationUnit;
import com.example.skylink.UnitTest.FlightSortingUnit;
import com.example.skylink.UnitTest.UserHandlerUnit;
import com.example.skylink.UnitTest.ValidatePassgnDataUnit;
import com.example.skylink.UnitTest.ValidatePaymentUnit;
import com.example.skylink.UnitTest.ValidateSearchInputUnit;
import com.example.skylink.UnitTest.ValidateUserAuthUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Add all the classes from IntegratedTest and UnitTestStart
        AirportPathIntegrated.class,
        PassengerDataManagerIntegrated.class,
        PlaneConfigurationIntegrated.class,
        PaymentHandlerIntegrated.class,
        UserHandlerIntegrated.class,
        AirportPathUnit.class,
        PassengerDataManagerUnit.class,
        PlaneConfigurationUnit.class,
        FlightSortingUnit.class,
        PaymentHandlerUnit.class,
        FlightSortingIntegratedTest.class,
        UserHandlerUnit.class,
        ValidatePassgnDataUnit.class,
        ValidatePaymentUnit.class,
        ValidateSearchInputUnit.class,
        ValidateUserAuthUnit.class
})
public class AllTest {
    // This class can remain empty
    // It's used only as a container for the suite
}
