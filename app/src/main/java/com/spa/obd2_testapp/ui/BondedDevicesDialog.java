package com.spa.obd2_testapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.spa.obd2_testapp.R;

import java.util.Set;

/**
 * Created by Vadim on 10.01.2017.
 */

public class BondedDevicesDialog extends Dialog implements View.OnClickListener  {

    private Activity activityInstance;
    private Dialog dialog;
    private Button connect;
    private Set<BluetoothDevice> pairedBluetoothDevices;
    private String[] pairedBluetoothDeviceNames;
    private ArrayAdapter<BluetoothDevice> adapter;

    private BondedDevicesDialogListener dialogListener;

    public BondedDevicesDialogListener getDialogListener() {
        return dialogListener;
    }

    public void setDialogListener(BondedDevicesDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }



    public BondedDevicesDialog(Activity activity, Set<BluetoothDevice> pairedBluetoothDevices)
    {
        super(activity);
        this.activityInstance=activity;
        this.pairedBluetoothDevices = pairedBluetoothDevices;

        if (this.pairedBluetoothDevices.size()>0)
        {
            this.pairedBluetoothDeviceNames = new String[this.pairedBluetoothDevices.size()];

            int counter =0;
            for (BluetoothDevice device : pairedBluetoothDevices)
            {
                pairedBluetoothDeviceNames[counter] = device.getName();
                counter++;
            }

        }



    }

    public Dialog CreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activityInstance);
        builder.setTitle(R.string.bluetooth_dialog_title);
        builder.setItems(pairedBluetoothDeviceNames, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                dialogListener.UserSelectedValue(pairedBluetoothDeviceNames[item]);

            }
        });

        return  builder.create();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}
