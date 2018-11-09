package com.spa.obd2_testapp.obd;



import com.spa.obd2_testapp.obd.atCommands.DescribeProtocolNumberCommand;
import com.spa.obd2_testapp.obd.atCommands.EchoOffCommand;
import com.spa.obd2_testapp.obd.atCommands.LineFeedOffCommand;
import com.spa.obd2_testapp.obd.atCommands.ProtocolCommand;
import com.spa.obd2_testapp.obd.atCommands.ResetOBDCommand;
import com.spa.obd2_testapp.obd.atCommands.SpacesOffCommand;
import com.spa.obd2_testapp.obd.atCommands.TimeOutCommand;

import java.util.ArrayList;

/**
 * Created by Vadim on 09.01.2017.
 */

public class InitOBDRequest extends  OBDRequest {

    DescribeProtocolNumberCommand protocolNumberCommand;

    public InitOBDRequest () {

        protocolNumberCommand=new DescribeProtocolNumberCommand();

        commandsCollection = new ArrayList<>();
        commandsCollection.add(new ResetOBDCommand());
        //commandsCollection.add(protocolNumberCommand);
        //commandsCollection.add(new ProtocolCommand(protocolNumberCommand.getProtocol()));
        commandsCollection.add(new EchoOffCommand());
        commandsCollection.add(new LineFeedOffCommand());
         commandsCollection.add(new TimeOutCommand());
        commandsCollection.add(new SpacesOffCommand());
        commandsCollection.add(new ProtocolCommand(OBDProtocols.AUTO));

    }
}
