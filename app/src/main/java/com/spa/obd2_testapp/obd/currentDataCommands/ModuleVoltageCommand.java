package com.spa.obd2_testapp.obd.currentDataCommands;

import com.spa.obd2_testapp.obd.OBDATCommand;
import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 15.12.2017.
 */

public class ModuleVoltageCommand extends OBDCommand {

    private double voltage = 0.00;
    public ModuleVoltageCommand ( ) {super ("01 42", "Voltage");}

    @Override
    protected void valuesCalculation() {

        int a = buffer.get(2);
        int b = buffer.get(3);
        voltage = (a * 256 + b) / 1000;

    }

    @Override
    public String getCalculatedResult() {

        return String.valueOf(voltage);
    }
}
