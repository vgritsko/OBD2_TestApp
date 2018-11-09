package com.spa.obd2_testapp.obd.currentDataCommands;


import com.spa.obd2_testapp.obd.OBDCommand;

/**
 * Created by Vadim on 12.01.2017.
 */
public class FuelTankLevelCommand extends OBDCommand {

    private float fuelTankLevel=-1.0f;

    public FuelTankLevelCommand () {

        super ("01 2F","Fuel tank level");
    }

    @Override
    protected void valuesCalculation() {

        fuelTankLevel=100.0f*buffer.get(2)/255.0f;

    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelTankLevel);
    }


}
