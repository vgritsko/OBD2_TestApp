package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class BarometricPressure extends OBDCommand {

    private  int pressure = -1;

    public BarometricPressure () {

        super ("01 33","barometric pressure");
    }


    @Override
    protected void valuesCalculation() {

        pressure=buffer.get(2);

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(pressure);
    }
}
