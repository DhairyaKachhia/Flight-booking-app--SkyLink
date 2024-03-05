package com.example.skylink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AirportPathUnit.class,
        PassengerDataManagerUnit.class,
        PlaneConfigurationUnit.class,
        FlightSortingTest.class
})
public class UnitTest {
    // This class can remain empty
    // It's used only as a container for the suite
}
