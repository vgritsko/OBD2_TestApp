package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class PIDSSupported extends OBDATCommand {

    public PIDSSupported (){

        super ("01 00","Pids Supported");
    }

    @Override
    protected void valuesCalculation() {

    }
}
