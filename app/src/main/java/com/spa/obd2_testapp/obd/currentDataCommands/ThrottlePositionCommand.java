package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class ThrottlePositionCommand extends OBDCommand {

    private float throttlePosition = -1.0f;


    public ThrottlePositionCommand(){

        super ("01 11","throttle position");

    }
    @Override
    protected void valuesCalculation() {

        throttlePosition=(buffer.get(2)*100)/255.0f;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(throttlePosition);
    }
}
