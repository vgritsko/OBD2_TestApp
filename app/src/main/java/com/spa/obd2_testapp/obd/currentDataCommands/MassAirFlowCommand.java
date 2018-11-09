package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */
public class MassAirFlowCommand extends OBDCommand {

    private float maf = -1.0f;

    public MassAirFlowCommand() {
        super("01 10","Mass air flow");
    }

    @Override
    protected void valuesCalculation() {

        maf = (buffer.get(2) * 256 + buffer.get(3)) / 100.0f;

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(maf);
    }
}
