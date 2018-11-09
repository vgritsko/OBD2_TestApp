package com.spa.obd2_testapp.obd;



import com.spa.obd2_testapp.obd.currentDataCommands.BarometricPressure;
import com.spa.obd2_testapp.obd.currentDataCommands.CatalystTemperature1;
import com.spa.obd2_testapp.obd.currentDataCommands.CatalystTemperature2;
import com.spa.obd2_testapp.obd.currentDataCommands.CatalystTemperature3;
import com.spa.obd2_testapp.obd.currentDataCommands.CatalystTemperature4;
import com.spa.obd2_testapp.obd.currentDataCommands.CommandedEGR;
import com.spa.obd2_testapp.obd.currentDataCommands.CommandedEvaporativePurgeCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.DistanceTraveledCodesCleared;
import com.spa.obd2_testapp.obd.currentDataCommands.DistanceTravelledWithMILCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.EGRErrorCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.EngineCoolantTemperature;
import com.spa.obd2_testapp.obd.currentDataCommands.EngineLoadCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.EngineOilTemperature;
import com.spa.obd2_testapp.obd.currentDataCommands.FuelPressure;
import com.spa.obd2_testapp.obd.currentDataCommands.FuelRailGaugePressure;
import com.spa.obd2_testapp.obd.currentDataCommands.FuelTankLevelCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.FuelTankLevelInput;
import com.spa.obd2_testapp.obd.currentDataCommands.IntakeAirTemperatureCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.MainfoldAbsolutePressureCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.MassAirFlowCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.ModuleVoltageCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.OxygenSensor1;
import com.spa.obd2_testapp.obd.currentDataCommands.OxygenSensor2;
import com.spa.obd2_testapp.obd.currentDataCommands.OxygenSensor3;
import com.spa.obd2_testapp.obd.currentDataCommands.OxygenSensor4;
import com.spa.obd2_testapp.obd.currentDataCommands.RPMCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.RuntimeEngineStartCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.SpeedCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.ThrottlePositionCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.TimingAdvance;
import com.spa.obd2_testapp.obd.currentDataCommands.VinCommand;
import com.spa.obd2_testapp.obd.currentDataCommands.VinCommand2;
import com.spa.obd2_testapp.obd.currentDataCommands.WarmupsSinceCodesCleared;

import java.util.ArrayList;

/**
 * Created by Vadim on 09.01.2017.
 */

public class PIDDataOBDRequest extends OBDRequest {

    public PIDDataOBDRequest() {

        commandsCollection = new ArrayList<>();
        commandsCollection.add(new RPMCommand());
        commandsCollection.add(new SpeedCommand());
        commandsCollection.add(new ThrottlePositionCommand());

    }
}
