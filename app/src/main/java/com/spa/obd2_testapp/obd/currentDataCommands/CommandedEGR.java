package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class CommandedEGR extends OBDCommand {

    private float value=-1000.0f;

    public  CommandedEGR () {

        super ("01 2C","commanded EGR");
    }

    @Override
    protected void valuesCalculation() {

        value=(buffer.get(2)*100)/255.0f;

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(value);
    }
}
