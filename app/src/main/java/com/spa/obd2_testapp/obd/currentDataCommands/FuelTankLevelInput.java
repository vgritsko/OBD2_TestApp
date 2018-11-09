package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class FuelTankLevelInput extends OBDCommand {

    private float value=-100.0f;

    public FuelTankLevelInput () {

        super ("01 2F","fuel tank level input");
    }

    @Override
    protected void valuesCalculation() {

        value=(100*buffer.get(2))/255.0f;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }
}
