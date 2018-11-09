package com.spa.obd2_testapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public   class SharedPreferncesHelper {



    private final static String PREF = "OBD_PREF";
    private final static String IP1_KEY = "IP1_KEY";
    private final static String IP2_KEY = "IP2_KEY";

    private final static String PORT1_KEY = "PORT1_KEY";
    private final static String PORT2_KEY = "PORT2_KEY";

    private final static String IP_DEFAULT_VALUE = "10.1.1.1";
    private final static String PORT_DEFAULT_VALUE = "1000";



    private static SharedPreferences.Editor getEditor (Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        return  sp.edit();
    }

    public static void setIPAddress1Value(Context context,String value) {


        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(IP1_KEY,value);
        editor.apply();
    }


    public static void setIPAddress2Value (Context context, String value) {


        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(IP2_KEY,value);
        editor.apply();
    }


    public static void setPort1Value (Context context, String value) {

        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PORT1_KEY,value);
        editor.apply();
    }


    public  static void setPort2Value (Context context, String value) {

        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PORT2_KEY,value);
        editor.apply();
    }


    public static String getIPAddress1Value (Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String ip1 = sp.getString(IP1_KEY,IP_DEFAULT_VALUE);

        return ip1;
    }

    public static String getIPAddress2Value (Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String ip2 = sp.getString(IP2_KEY,IP_DEFAULT_VALUE);

        return ip2;
    }

    public static String getPort1Value(Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String port1 = sp.getString(PORT1_KEY,PORT_DEFAULT_VALUE);

        return port1;
    }


    public static String getPort2Value (Context context) {

        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String port2 = sp.getString(PORT2_KEY,PORT_DEFAULT_VALUE);

        return  port2;
    }
}
