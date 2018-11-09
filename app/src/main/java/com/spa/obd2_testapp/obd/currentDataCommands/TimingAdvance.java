package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 14.01.2017.
 */

public class TimingAdvance extends OBDCommand {

    private float timingAdvance=-1000;

    public TimingAdvance () {

        super ("01 0E","timing advance");
    }

    @Override
    protected void valuesCalculation() {

        timingAdvance=(buffer.get(2)/2)-64;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(timingAdvance);
    }
}
