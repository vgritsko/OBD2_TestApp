package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class SpacesOffCommand extends OBDATCommand {

    public SpacesOffCommand() {

        super("AT S0", "Spaces off");
    }

    @Override
    protected void valuesCalculation() {

    }
}
