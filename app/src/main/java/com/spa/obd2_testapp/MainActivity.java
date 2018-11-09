package com.spa.obd2_testapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.spa.obd2_testapp.helpers.SharedPreferncesHelper;
import com.spa.obd2_testapp.obd.InitOBDRequest;
import com.spa.obd2_testapp.obd.OBDCommand;
import com.spa.obd2_testapp.obd.OBDRequest;
import com.spa.obd2_testapp.obd.OBDRequestProcessor;
import com.spa.obd2_testapp.obd.PIDDataOBDRequest;
import com.spa.obd2_testapp.osc.OSCMessage;
import com.spa.obd2_testapp.osc.OSCPort;
import com.spa.obd2_testapp.osc.OSCPortOut;
import com.spa.obd2_testapp.ui.BondedDevicesDialog;
import com.spa.obd2_testapp.ui.BondedDevicesDialogListener;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private BluetoothAdapter bluetoothAdapter;
    private String selectedBondedDeviceName;
    private BluetoothDevice bondedDevice;
    private UUID bondedDeviceUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//UUID.randomUUID();
    private BluetoothSocket bluetoothSocket;
    private final int REQUEST_ENABLE_BT = 1;


    //multi-threading
    private Handler handler;
    private OBDRequestProcessor runnable;
    private Thread t;


    //ui
    Button startButton;
    Button stopButton;
    // TextView voltageTextView;
    TextView rpmTextView;
    TextView speedTextView;
    //TextView coolantTextView;
    TextView throttleTextView;
    //TextView fuelPressureTextView;
    //TextView engineLoadTextView;
    //TextView inAirTempTextView;
    ImageView connectionStatusImageView;

    EditText ipAddress1EditText;
    EditText ipAddress2EditText;

    EditText port1EditText;
    EditText port2EditText;

    //osc
    OSCPortOut oscPortOut1;
    OSCPortOut oscPortOut2;


    //broadcast reciever
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        connectionStatusImageView.setBackgroundResource(R.drawable.bullet_red);

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        connectionStatusImageView.setBackgroundResource(R.drawable.bullet_red);

                        break;
                    case BluetoothAdapter.STATE_ON:
                        showBondedDevicesDialog();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:

                        break;
                }
            }

            if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Fabric.with(this, new Crashlytics());


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        showBondedDevicesDialog();


        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        rpmTextView = (TextView) findViewById(R.id.rpmTvValue);
        speedTextView = (TextView) findViewById(R.id.speedTvValue);
        //  coolantTextView = (TextView)findViewById(R.id.coolantTvValue);
        throttleTextView = (TextView) findViewById(R.id.throtlleTvValue);
        //  fuelPressureTextView = (TextView)findViewById(R.id.fuelPressTvValue);
        //  engineLoadTextView = (TextView)findViewById(R.id.engineLoadTvValue);
        //  inAirTempTextView = (TextView)findViewById(R.id.inAirFlowTvValue);
        //  voltageTextView = (TextView)findViewById(R.id.mafTvValue);
        connectionStatusImageView = (ImageView) findViewById(R.id.conectionStatusImageView);

        ipAddress1EditText = (EditText) findViewById(R.id.editTextIp1);
        ipAddress2EditText = (EditText) findViewById(R.id.editTextIp2);

        ipAddress1EditText.setText(SharedPreferncesHelper.getIPAddress1Value(this));
        ipAddress2EditText.setText(SharedPreferncesHelper.getIPAddress2Value(this));


        port1EditText = (EditText) findViewById(R.id.edittextPort1);
        port2EditText = (EditText) findViewById(R.id.edittextPort2);

        port1EditText.setText(SharedPreferncesHelper.getPort1Value(this));
        port2EditText.setText(SharedPreferncesHelper.getPort2Value(this));


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                OBDRequest tel = (PIDDataOBDRequest) msg.obj;

                OBDCommand rpmCmd = (OBDCommand) tel.getCommandsList().get(0);
                rpmTextView.setText(rpmCmd.getCalculatedResult());

                OBDCommand speedCmd = (OBDCommand) tel.getCommandsList().get(1);
                speedTextView.setText(speedCmd.getCalculatedResult());


                OBDCommand throttleCmd = (OBDCommand) tel.getCommandsList().get(2);
                throttleTextView.setText(throttleCmd.getCalculatedResult());


                if ((oscPortOut1 != null) && (oscPortOut2 != null)) {


                    new SendOscMessageTask(oscPortOut1,oscPortOut2).execute(tel);
                    // Creating the message
                   /* Object[] thingsToSend = new Object[3];
                    thingsToSend[0] = rpmCmd.getCalculatedResult();
                    thingsToSend[1] = throttleCmd.getCalculatedResult();
                    thingsToSend[2] = speedCmd.getCalculatedResult();


                    OSCMessage messageRPM = new OSCMessage();
                    messageRPM.setAddress("/rpm");
                    messageRPM.addArgument(thingsToSend[0]);

                    OSCMessage messageGasPedal = new OSCMessage();
                    messageGasPedal.setAddress("/gasPedal");
                    messageGasPedal.addArgument(thingsToSend[1]);

                    OSCMessage messageSpeed = new OSCMessage();
                    messageSpeed.setAddress("/speed");
                    messageSpeed.addArgument(thingsToSend[2]);


                    try {

                        oscPortOut1.send(messageRPM);
                        oscPortOut1.send(messageGasPedal);
                        oscPortOut1.send(messageSpeed);

                        oscPortOut2.send(messageRPM);
                        oscPortOut2.send(messageGasPedal);
                        oscPortOut2.send(messageSpeed);

                    } catch (Exception e) {

                        Log.v("OSC Sending", "Error");

                    }*/


                }


            }
        };


    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        enableBluetooth();

        // create instance of IntentFilters
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);

        IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        registerReceiver(mReceiver, filter2);


    }


    @Override
    protected void onPause() {
       if(handler != null) handler.removeCallbacks(runnable);
       if (runnable != null) runnable.stop();


        super.onPause();
    }

    @Override
    protected void onStop() {


        runnable.stop();


        // close thread
        if (t != null) {

            Thread temp = t;
            temp.interrupt();
            t = null;
            handler.removeCallbacksAndMessages(null);
        }

        //close bluetooth
        if (bluetoothSocket != null) {
            try {

                bluetoothSocket.close();

            } catch (IOException e) {

                Log.e(getClass().getSimpleName(), e.getMessage());

            }
        }


        unregisterReceiver(mReceiver);
        super.onStop();

    }


    private void enableBluetooth() {
        if (bluetoothAdapter.isEnabled() == false) {

            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }


    public void showBondedDevicesDialog() {

        BondedDevicesDialog bondedDevicesDialog = new BondedDevicesDialog(this, bluetoothAdapter.getBondedDevices());
        bondedDevicesDialog.setDialogListener(new BondedDevicesDialogListener() {
            public void UserSelectedValue(String value) {
                selectedBondedDeviceName = value;


            }
        });

        bondedDevicesDialog.CreateDialog().show();
    }


    private void createOscPort() {

        try {
            // Connect to some IP address and port
            //oscPortOut1 = new OSCPortOut(InetAddress.getByName(ipAddress1EditText.getText().toString()), Integer.getInteger(port1EditText.getText().toString()));
            //oscPortOut2 = new OSCPortOut(InetAddress.getByName(ipAddress2EditText.getText().toString()), Integer.getInteger(port2EditText.getText().toString()));
            String ip1 = ipAddress1EditText.getText().toString();
            int port1 =  Integer.parseInt(port1EditText.getText().toString());
            oscPortOut1 = new OSCPortOut(InetAddress.getByName(ip1), port1);

            String ip2 = ipAddress2EditText.getText().toString();
            int port2 = Integer.parseInt(port2EditText.getText().toString());
            oscPortOut2 = new OSCPortOut(InetAddress.getByName(ip2), port2);

        } catch (UnknownHostException e) {

            Log.e("OSC Error", e.getMessage());
            return;
        } catch (Exception e) {
            Log.e("OSC Error", e.getMessage());
            return;
        }
    }


    public void onButtonStart_Click(View view) {


        String ip1 = ipAddress1EditText.getText().toString();
        SharedPreferncesHelper.setIPAddress1Value(this, ip1);

        String ip2 = ipAddress2EditText.getText().toString();
        SharedPreferncesHelper.setIPAddress2Value(this, ip2);

        String port1 = port1EditText.getText().toString();
        SharedPreferncesHelper.setPort1Value(this, port1);

        String port2 = port2EditText.getText().toString();
        SharedPreferncesHelper.setPort2Value(this, port2);


        if (connectToSelectedDevice(selectedBondedDeviceName)) {

            createOscPort();


            OBDRequest initRequest = new InitOBDRequest();
            OBDRequest dataRequest = new PIDDataOBDRequest();

            runnable = new OBDRequestProcessor(bluetoothSocket, handler, initRequest, dataRequest);
            t = new Thread(runnable);
            t.start();
            Toast.makeText(this, R.string.obd_sending_data, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please wait...", Toast.LENGTH_LONG).show();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            connectionStatusImageView.setBackgroundResource(R.drawable.bullet_green);

        }


    }

    public void onButtonStop_Click(View view) {

        runnable.stop();


        // close thread
        if (t != null) {

            Thread temp = t;
            temp.interrupt();
            t = null;
            handler.removeCallbacksAndMessages(null);
        }

        //close bluetooth
        if (bluetoothSocket != null) {
            try {

                bluetoothSocket.close();

            } catch (IOException e) {

                Log.e(getClass().getSimpleName(), e.getMessage());

            }
        }
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        clearTextViewValues();
        connectionStatusImageView.setBackgroundResource(R.drawable.bullet_red);
        Toast.makeText(this, "OBD reading stopped!", Toast.LENGTH_SHORT).show();

    }


    private boolean connectToSelectedDevice(String bondedDeviceName) {

        String bondedDeviceAddress = null;

        for (BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {

            if (device.getName().equalsIgnoreCase(bondedDeviceName)) {

                bondedDeviceAddress = device.getAddress();
            }
        }

        boolean connected = false;

        if (bondedDeviceAddress != null) {

            bondedDevice = bluetoothAdapter.getRemoteDevice(bondedDeviceAddress);

            //bluetoothSocket = bondedDevice.createInsecureRfcommSocketToServiceRecord(bondedDevice.getUuids()[0].getUuid()); // support only API 15
            try {
                Method m = bondedDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});  // connect only reflection
                bluetoothSocket = (BluetoothSocket) m.invoke(bondedDevice, 1);
            } catch (NoSuchMethodException e) {

                Log.e(getClass().getSimpleName(), e.getMessage());
            } catch (InvocationTargetException e) {

                Log.e(getClass().getSimpleName(), e.getMessage());
            } catch (IllegalAccessException e) {

                Log.e(getClass().getSimpleName(), e.getMessage());
            }


            try {

                bluetoothSocket.connect();

                if (bluetoothSocket.isConnected()) {
                    connected = true;
                    Toast.makeText(this, R.string.bluetooth_connection_success, Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {

                connected = false;
                Toast.makeText(this, R.string.bluetooth_connection_failed, Toast.LENGTH_SHORT).show();
                Log.e(getClass().getSimpleName(), e.getMessage());

            }

        }
        return connected;
    }


    private void clearTextViewValues() {

        String defaultValue = "0";

        //  voltageTextView.setText(defaultValue);
        rpmTextView.setText(defaultValue);
        speedTextView.setText(defaultValue);
        //  coolantTextView.setText(defaultValue);
        throttleTextView.setText(defaultValue);
        // fuelPressureTextView.setText(defaultValue);
        // engineLoadTextView.setText(defaultValue);
        // inAirTempTextView.setText(defaultValue);
    }


    class SendOscMessageTask extends AsyncTask<OBDRequest, Void, Void> {

        private OSCPortOut port1;
        private OSCPortOut port2;

        public SendOscMessageTask(OSCPortOut port1, OSCPortOut port2) {

            this.port1 = port1;
            this.port2 = port2;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(OBDRequest... obdRequests) {

            if ((oscPortOut1 != null) && (oscPortOut2 != null)) {

                OBDCommand rpmCmd = (OBDCommand) obdRequests[0].getCommandsList().get(0);
                OBDCommand speedCmd = (OBDCommand) obdRequests[0].getCommandsList().get(1);
                OBDCommand throttleCmd = (OBDCommand) obdRequests[0].getCommandsList().get(2);


                // Creating the message
                Object[] thingsToSend = new Object[3];
                thingsToSend[0] = rpmCmd.getCalculatedResult();
                thingsToSend[1] = throttleCmd.getCalculatedResult();
                thingsToSend[2] = speedCmd.getCalculatedResult();


                OSCMessage messageRPM = new OSCMessage();
                messageRPM.setAddress("/RPM");
                messageRPM.addArgument(thingsToSend[0]);

                OSCMessage messageGasPedal = new OSCMessage();
                messageGasPedal.setAddress("/gasPedal");
                messageGasPedal.addArgument(thingsToSend[1]);

                OSCMessage messageSpeed = new OSCMessage();
                messageSpeed.setAddress("/speed");
                messageSpeed.addArgument(thingsToSend[2]);


                try {

                    oscPortOut1.send(messageRPM);
                    oscPortOut1.send(messageGasPedal);
                    oscPortOut1.send(messageSpeed);

                    oscPortOut2.send(messageRPM);
                    oscPortOut2.send(messageGasPedal);
                    oscPortOut2.send(messageSpeed);

                } catch (Exception e) {

                    Log.v("OSC Sending", "Error");

                }
            }

            return null;
        }
    }
}


