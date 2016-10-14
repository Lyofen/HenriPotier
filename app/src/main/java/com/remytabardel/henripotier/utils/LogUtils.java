package com.remytabardel.henripotier.utils;

import android.util.Log;

/**
 * @author Remy Tabardel
 *         Redefine log class if we need to add file logs, report to analytics, etc..
 */

public class LogUtils {
    private static final String TAG = "HenriPotierLogs";

    public static void error(String msg) {
        Log.d(TAG, msg);
    }

    public static void error(String msg, Throwable tr) {
        Log.d(TAG, msg, tr);
    }
}
