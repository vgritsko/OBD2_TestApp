package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class       EchoOffCommand extends OBDATCommand {

    public EchoOffCommand (){

        super ("AT E0","EchoOff Command");
    }

    @Override
    protected void valuesCalculation() {

    }
}
