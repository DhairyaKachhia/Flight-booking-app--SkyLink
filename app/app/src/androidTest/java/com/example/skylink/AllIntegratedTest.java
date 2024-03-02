package com.example.skylink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AirportPathIntegratedTest.class,
        PassengerDataManagerIntegratedTest.class,
        PlaneConfigurationIntegratedTest.class,
        UserHandlerIntegratedTest.class
})
public class  AllIntegratedTest{
    // This class can remain empty
    // It's used only as a container for the suite
}
