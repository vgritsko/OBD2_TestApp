package com.spa.obd2_testapp.obd.atCommands;


import com.spa.obd2_testapp.obd.OBDATCommand;
import com.spa.obd2_testapp.obd.OBDProtocols;

/**
 * Created by Vadim on 09.01.2017.
 */

public class ProtocolCommand extends OBDATCommand {

    private OBDProtocols obdProtocol;

    public  ProtocolCommand  (final OBDProtocols protocol){

        super ("AT SP"+protocol.getValue(),"Select protocol command");
        obdProtocol=protocol;
    }

    @Override
    protected void valuesCalculation() {

    }

}
