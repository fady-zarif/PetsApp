package com.example.fady.uspets.USPetsMain;

import android.util.Log;

public class PetsLogClass {
    public static boolean showLogs = true;

    public static void showLogError(String tag, String message) {
        if (showLogs)
            Log.e(tag, message);
    }

    public static void showLogDebug(String tag, String message) {
        if (showLogs)
            Log.d(tag, message);
    }
}
