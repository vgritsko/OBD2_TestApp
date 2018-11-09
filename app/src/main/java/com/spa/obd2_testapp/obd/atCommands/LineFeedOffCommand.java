package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 19.02.2017.
 */

public class LineFeedOffCommand extends OBDATCommand {

    public LineFeedOffCommand() {

        super("AT L0", "LineFeed off");
    }

    @Override
    protected void valuesCalculation() {

    }
}
