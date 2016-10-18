package com.remytabardel.henripotier.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Calendar;

/**
 * @author Remy Tabardel
 *         this class avoid toast spam, because stay on screen a long time
 *         we should set an interval for each type of toast, but here we consider the same interval for all
 */

public class ToastUtils {
    private static long mLastTimeToastedMs = 0;
    private static final long TOAST_INTERVAL_MS = 2000;

    public static void show(Context context, int textId, int duration) {
        show(context, context.getString(textId), duration);
    }

    public static void show(Context context, String text, int duration) {
        long currentTimeMs = Calendar.getInstance().getTimeInMillis();

        //avoid spam click toast, because if lot of toasts stay on screen long time
        if (currentTimeMs - mLastTimeToastedMs > TOAST_INTERVAL_MS) {
            mLastTimeToastedMs = currentTimeMs;
            Toast.makeText(context, text, duration).show();
        }
    }
}
