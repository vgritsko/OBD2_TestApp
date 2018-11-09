package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class WarmupsSinceCodesCleared extends OBDCommand {

    private  int distance=-100;

    public  WarmupsSinceCodesCleared () {

        super ("01 30","Warm-ups since codes cleared");
    }

    @Override
    protected void valuesCalculation() {

        distance=buffer.get(2);

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(distance);
    }
}
