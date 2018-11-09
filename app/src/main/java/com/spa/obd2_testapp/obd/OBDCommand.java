package com.spa.obd2_testapp.obd;

import android.util.Log;


import com.spa.obd2_testapp.exceptions.OBDIIRuntimeException;
import com.spa.obd2_testapp.globals.Globals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Vadim on 09.01.2017.
 */

public abstract class OBDCommand {

    protected String cmd=null;
    protected Long responseDelayMs=null;

    protected String rawData=null;
    protected ArrayList<Integer> buffer=null;
    protected String cmdDescription=null;

    protected String[] ERRORS = {
            "UNABLETOCONNECT",
            "BUS INIT... ERROR",
            "?",
            "NODATA",
            "STOPPED",
            "ERROR"};

    public OBDCommand (String command, String commandDescription){

        cmd=command;
        cmdDescription=commandDescription;
        buffer = new ArrayList<>();
        responseDelayMs= Globals.RESPONSE_DELAY_MS;
    }

    private  OBDCommand (){

    }




    public void Run (InputStream inStream, OutputStream outStream) throws IOException,InterruptedException,InputMismatchException,OBDIIRuntimeException {

        sendCommand(outStream);
        readResult(inStream);
    }


    protected void sendCommand (OutputStream outStream) throws IOException,InterruptedException {

        outStream.write((cmd+"\r").getBytes());
        outStream.flush();
        if ((responseDelayMs!=null) && (responseDelayMs>0)){

            Thread.sleep(responseDelayMs);
        }

    }


    protected  void readResult (InputStream inStream) throws IOException, InterruptedException,InputMismatchException,OBDIIRuntimeException {

        readByteData(inStream);
        checkErrors();
        fillBuffer();
        valuesCalculation();
    }


    protected  void  fillBuffer () throws InputMismatchException {

        Log.i("rawData",rawData);
        rawData = rawData.replaceAll("\\s", ""); //removes all [ \t\n\x0B\f\r]
        rawData = rawData.replaceAll("(BUS INIT)|(BUSINIT)|(\\.)", "");

        if (!rawData.matches("([0-9A-F])+")) {

            throw new InputMismatchException(rawData);

        }


        buffer.clear();
        int begin=0;
        int end=2;
        while (end<=rawData.length()) {
            buffer.add(Integer.decode("0x"+rawData.substring(begin,end)));
            begin=end;
            end+=2;
        }

        checkBufferLength();

        Log.i("buffer",rawData);

    }


    protected  void checkBufferLength () {

        if (buffer.size()<=1) {
            throw  new OBDIIRuntimeException(cmd,rawData);
        }
    }

    protected abstract void valuesCalculation ();

    protected void  readByteData (InputStream inStream) throws IOException {

        byte b=0;
        StringBuilder response=new StringBuilder();

        char c;
        while (((b = (byte)inStream.read())>-1)) {
            c=(char) b;
            if (c == '>')
            {
                break;
            }
            response.append(c);

        }
        Log.i("RESPNSE",response.toString());

        rawData = response.toString().replaceAll("SEARCHING", "");

        rawData = rawData.replaceAll("\\s", "");
    }


    protected void checkErrors (){

        for (String errorResponse : ERRORS){

            if (rawData.contains(errorResponse)){

                throw new OBDIIRuntimeException(cmd,rawData);
            }
        }
    }



    public String getCmdDescription () {

        return cmdDescription;

    }

    public String getRawResult() {

        return rawData;
    }


    public abstract String getCalculatedResult ();

    public String getStringMessageForMQTT () {

        String result = cmd+";"+getCalculatedResult();

        return result;
    }





}