package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */
public class MainfoldAbsolutePressureCommand extends OBDCommand {

    float pressure=-1.0f;

    public MainfoldAbsolutePressureCommand() {
        super("01 0B","Mainfold absolute pressure");
    }


    @Override
    protected void valuesCalculation() {
          pressure=buffer.get(2);
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(pressure);
    }
}
