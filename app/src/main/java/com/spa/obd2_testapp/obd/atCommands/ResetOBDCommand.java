package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 19.02.2017.
 */

public class ResetOBDCommand extends OBDATCommand {

    public ResetOBDCommand() {

        super("AT Z", "Reset OBD");

    }

    @Override
    protected void valuesCalculation() {

    }
}
