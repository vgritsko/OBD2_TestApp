package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class CatalystTemperature3 extends OBDCommand {

    private float temperature = -1000.0f;

    public CatalystTemperature3() {

        super ("01 3E","Catalyst Temperature 3");
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
