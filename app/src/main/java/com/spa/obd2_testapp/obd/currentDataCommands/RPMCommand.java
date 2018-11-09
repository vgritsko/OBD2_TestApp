package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class  RPMCommand extends OBDCommand {

    private int rpm = -1;

    public  RPMCommand () {

        super("01 0C", "RPM");
    }

    @Override
    protected void valuesCalculation() {

        rpm = (buffer.get(2)*256+buffer.get(3))/4;
    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(rpm);
    }



}
