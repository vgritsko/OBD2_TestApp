package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class EGRErrorCommand extends OBDCommand {

    private float value = -1000.0f;

    public EGRErrorCommand() {

        super ("01 2D","egr error");
    }

    @Override
    protected void valuesCalculation() {

        value=(100*buffer.get(2))/128.0f-100;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }
}
