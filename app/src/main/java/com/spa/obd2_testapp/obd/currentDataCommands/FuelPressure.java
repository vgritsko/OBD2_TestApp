package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class FuelPressure extends OBDCommand {

    private int pressure = -1;

    public FuelPressure () {

        super ("01 0A","fuel pressure");
    }

    @Override
    protected void valuesCalculation() {

        pressure=buffer.get(2) * 3;
    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(pressure);
    }
}
