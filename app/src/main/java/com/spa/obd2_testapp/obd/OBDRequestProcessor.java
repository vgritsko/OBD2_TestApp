package com.spa.obd2_testapp.obd;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Vadim on 09.01.2017.
 */

public class OBDRequestProcessor implements Runnable {

    private final BluetoothSocket socket;
    private final Handler handler;

    private final InputStream inStream;
    private final OutputStream outStream;

    private OBDRequest initRequest;
    private OBDRequest pidDataRequest;





    public OBDRequestProcessor(BluetoothSocket socket, Handler handler, OBDRequest initRequest, OBDRequest pidDataRequest) {

        this.socket=socket;
        this.handler=handler;
        this.initRequest =initRequest;
        this.pidDataRequest =pidDataRequest;

        InputStream tmpInStream=null;
        OutputStream tmpOutStream=null;

        try {

            tmpInStream=socket.getInputStream();
            tmpOutStream=socket.getOutputStream();
        }

        catch (IOException e) {

            Log.e(getClass().getSimpleName(),e.getMessage());
        }

        inStream=tmpInStream;
        outStream=tmpOutStream;
    }

    @Override
    public void  run () {

        try {

            initRequest.Run(inStream, outStream);


        }

        catch (IOException e) {

            Log.e(getClass().getSimpleName(),e.getMessage());
        }


        while (!Thread.currentThread().isInterrupted()) {

            if (Thread.currentThread().isInterrupted()) {

                break;
            }

            try {
                pidDataRequest.Run(inStream, outStream);
                Message m = handler.obtainMessage(0, pidDataRequest);
                handler.sendMessage(m);
            }


            catch (IOException e) {

                Log.e(getClass().getSimpleName(),e.getMessage());
                Thread.currentThread().interrupt();

            }

        }

    }


    public void stop () {

        if (inStream!=null) {

            try {
                inStream.close();
            }
            catch ( IOException e) {

            }
        }

        if (outStream!=null) {
            try {
                //outStream.flush();
                outStream.close();
            }

            catch (IOException e) {

            }
        }
    }



}
