package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.IAddonsHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;

import java.util.List;

public class AddonsHandler implements IAddonsHandler {

    private int bagNumber, petNumber, wifiOption, wheelchairOption;

    public AddonsHandler() {
        bagNumber = 0;
        petNumber = 0;
        wifiOption = 0;
        wheelchairOption = 0;
    }

    @Override
    public void storeAddons(int bagNumber, int petNumber, int wifiOption, int wheelchairOption) {
        this.bagNumber = bagNumber;
        this.petNumber = petNumber;
        this.wifiOption = wifiOption;
        this.wheelchairOption = wheelchairOption;

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
