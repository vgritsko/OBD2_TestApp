package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class RuntimeEngineStartCommand extends OBDCommand {

    private int runtimeEngine = 0;

    public RuntimeEngineStartCommand() {

        super ("01 1F","runtime engine start");
    }

    @Override
    protected void valuesCalculation() {

        runtimeEngine = buffer.get(2) * 256 + buffer.get(3);

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(runtimeEngine);
    }
}
