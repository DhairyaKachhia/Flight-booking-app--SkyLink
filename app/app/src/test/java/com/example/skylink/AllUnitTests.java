package com.example.skylink;
import com.example.skylink.business.TestAirportPath;
import com.example.skylink.business.TestBookingManager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestBookingManager.class,
        TestAirportPath.class
})
public class AllUnitTests {
}
