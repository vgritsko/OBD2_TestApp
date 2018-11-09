package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 09.01.2017.
 */

public class SpeedCommand extends OBDCommand {

    private int speed = 0;

    public  SpeedCommand () {
        super ("01 0D", "Speed");
    }

    @Override
    protected void valuesCalculation() {

        speed=buffer.get(2);
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(speed);
    }

}
