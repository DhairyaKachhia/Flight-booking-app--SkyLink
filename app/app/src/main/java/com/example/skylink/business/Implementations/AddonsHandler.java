package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.IAddonsHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;

import java.util.List;

public class AddonsHandler implements IAddonsHandler {

    public AddonsHandler() { }

    @Override
    public void storeAddons(int bagNumber, int petNumber, int wifiOption, int wheelchairOption) {

        List<iFlightInfo> flightInfoList = Session.getInstance().getFlightInfoCompleted();

        if (flightInfoList != null && !flightInfoList.isEmpty()) {
            for (iFlightInfo flightInfo : flightInfoList) {

                if (flightInfo != null) {

                    flightInfo.setBagCount(bagNumber);
                    flightInfo.setPetCount(petNumber);
                    flightInfo.setWifiOption(wifiOption);
                    flightInfo.setWheelchairOption(wheelchairOption);

                }
            }
        }
    }
}
