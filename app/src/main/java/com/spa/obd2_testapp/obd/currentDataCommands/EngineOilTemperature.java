package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class EngineOilTemperature extends OBDCommand {

    private int temperature = -1000;

    public EngineOilTemperature () {

        super ("01 5C","Engine oil temperature");
    }

    @Override
    protected void valuesCalculation() {

        temperature = buffer.get(2)-40;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(temperature);
    }
}
