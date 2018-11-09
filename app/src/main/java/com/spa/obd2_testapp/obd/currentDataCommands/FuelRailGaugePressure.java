package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class FuelRailGaugePressure extends OBDCommand {

    private int pressure = -1;

    public FuelRailGaugePressure () {

        super ("01 23","Fuel Rail Gauge Pressure");
    }

    @Override
    protected void valuesCalculation() {

        pressure=((buffer.get(2)*256)+buffer.get(3))*10;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(pressure);
    }
}
