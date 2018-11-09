package com.spa.obd2_testapp.exceptions;


/**
 * Created by Vadim on 09.01.2017.
 */

public class OBDIIRuntimeException extends RuntimeException {

    String command;
    String response;


    public OBDIIRuntimeException(String command, String response) {

        this.command=command;
        this.response=response;
    }

    public String getMessage () {

        return  "Error OBDII protocol, command: "+command+" response: "+response;
    }
}