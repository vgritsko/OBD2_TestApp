package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 19.02.2017.
 */

public class TimeOutCommand extends OBDATCommand {
    Integer timeout=62;

    public TimeOutCommand() {



        super("AT ST " + Integer.toHexString(0xFF & 62),"Timeout command");
    }

    @Override
    protected void valuesCalculation() {

    }
}
