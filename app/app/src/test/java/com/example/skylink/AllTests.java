package com.example.skylink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AirportPathTest.class,
        PassengerDataManagerTest.class,
        PlaneConfigurationTest.class,
        UserHandlerTest.class,
        FlightSortingTest.class
})
public class AllTests {
    // This class can remain empty
    // It's used only as a container for the suite
}
