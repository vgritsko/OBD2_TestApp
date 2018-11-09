package com.spa.obd2_testapp.obd.currentDataCommands;

import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.12.2017.
 */

public class VinCommand2 extends OBDCommand {

    private String vin;

    public  VinCommand2 () {
        super ("09 02", "vin command");
    }

    @Override
    protected void valuesCalculation() {
       vin="empty" ;
    }

    @Override
    public String getCalculatedResult() {
        return vin;
    }
}
