package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class DistanceTraveledCodesCleared extends OBDCommand {

    private int distance = -100;

    public DistanceTraveledCodesCleared () {

        super ("01 31","Distance traveled since codes cleared");
    }

    @Override
    protected void valuesCalculation() {

        distance=256*buffer.get(2)+buffer.get(3);

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(distance);
    }
}
