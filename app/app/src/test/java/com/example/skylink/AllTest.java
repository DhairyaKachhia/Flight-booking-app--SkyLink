package com.example.skylink;

import com.example.skylink.IntegrationTest.AirportPathIntegrated;
import com.example.skylink.IntegrationTest.FlightBookingHandlerIntegrated;
import com.example.skylink.IntegrationTest.PassengerDataManagerIntegrated;
import com.example.skylink.IntegrationTest.PaymentHandlerIntegrated;
import com.example.skylink.IntegrationTest.PlaneConfigurationIntegrated;
import com.example.skylink.IntegrationTest.UserHandlerIntegrated;
import com.example.skylink.UnitTestStub.AddonsHandlerUnit;
import com.example.skylink.UnitTestStub.AirportPathUnit;
import com.example.skylink.UnitTestStub.PassengerDataManagerUnit;
import com.example.skylink.UnitTestStub.PaymentHandlerUnit;
import com.example.skylink.UnitTestStub.PlaneConfigurationUnit;
import com.example.skylink.UnitTestStub.FlightSortingUnit;
import com.example.skylink.UnitTestStub.UserHandlerUnit;
import com.example.skylink.UnitTestStub.ValidatePassgnDataUnit;
import com.example.skylink.UnitTestStub.ValidatePaymentUnit;
import com.example.skylink.UnitTestStub.ValidateSearchInputUnit;
import com.example.skylink.UnitTestStub.ValidateUserAuthUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Add all the classes from IntegratedTest and UnitTestStart
        AirportPathIntegrated.class,
        FlightBookingHandlerIntegrated.class,
        PassengerDataManagerIntegrated.class,
        PlaneConfigurationIntegrated.class,
        PaymentHandlerIntegrated.class,
        UserHandlerIntegrated.class,

        PassengerDataManagerUnit.class,
        PlaneConfigurationUnit.class,
        FlightSortingUnit.class,
        PaymentHandlerUnit.class,
        UserHandlerUnit.class,
        AddonsHandlerUnit.class,
        AirportPathUnit.class,
        ValidateUserAuthUnit.class,
        ValidateSearchInputUnit.class,
        ValidatePassgnDataUnit.class,
        ValidatePaymentUnit.class
})
public class AllTest {
    // This class can remain empty
    // It's used only as a container for the suite
}
