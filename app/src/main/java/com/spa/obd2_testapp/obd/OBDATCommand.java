package com.spa.obd2_testapp.obd;


import com.spa.obd2_testapp.globals.Globals;

/**
 * Created by Vadim on 09.01.2017.
 */

public class OBDATCommand extends OBDCommand {
    public OBDATCommand (String command, String commandDescription){
        super(command,commandDescription);
        responseDelayMs= Globals.RESPONSE_DELAY_MS_AT_CMD;
    }

    public OBDATCommand(String command) {
        super(command,"");
    }





    @Override
    protected void valuesCalculation() {

    }

    @Override
    public String getCalculatedResult() {

        return getRawResult();
    }

    protected void fillBuffer() {}
}

