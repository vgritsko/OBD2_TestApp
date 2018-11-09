package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class OxygenSensor2 extends OBDCommand {

    private float value = -1000.0f;

    public OxygenSensor2() {

        super ("01 25","Oxygen Sensor 2");
    }


    @Override
    protected void valuesCalculation() {

        value = (2/65536)*(256*buffer.get(2)+buffer.get(3));

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }
}
