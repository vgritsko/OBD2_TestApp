package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */

public class EngineCoolantTemperature extends OBDCommand {

    private  int temperature=-100;

    public EngineCoolantTemperature () {
        super("01 05","Coolant temperature");
    }

    @Override
    protected void valuesCalculation() {

        temperature=buffer.get(2)-40;
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(temperature);
    }
}
