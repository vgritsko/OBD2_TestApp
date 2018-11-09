package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class CatalystTemperature2 extends OBDCommand {

    private float temperature = -1000.0f;

    public CatalystTemperature2() {

        super ("01 3D","Catalyst Temperature 2");
    }

    @Override
    protected void valuesCalculation() {

        temperature =(256*buffer.get(2)+buffer.get(3))/10-40;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(temperature);
    }
}
