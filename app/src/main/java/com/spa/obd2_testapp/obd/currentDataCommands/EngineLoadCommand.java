package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class EngineLoadCommand extends OBDCommand {

    private float engineLoadPercentage = -1.0f;

    public EngineLoadCommand() {

        super ("01 04", "engine load calculated");
    }

    @Override
    protected void valuesCalculation() {

        engineLoadPercentage=buffer.get(2)/2.55f;

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(engineLoadPercentage);
    }
}
