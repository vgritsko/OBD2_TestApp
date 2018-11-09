package com.spa.obd2_testapp.obd.atCommands;

import com.spa.obd2_testapp.obd.OBDATCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class DescribeProtocolCommand extends OBDATCommand {

    public DescribeProtocolCommand() {
        super("AT DP", "describe protocol");
    }
}
