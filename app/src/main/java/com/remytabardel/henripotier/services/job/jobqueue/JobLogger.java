package com.remytabardel.henripotier.services.job.jobqueue;

import com.birbit.android.jobqueue.log.CustomLogger;
import com.remytabardel.henripotier.BuildConfig;
import com.remytabardel.henripotier.utils.LogUtils;

/**
 * @author Remy Tabardel
 */

public class JobLogger implements CustomLogger {
    @Override
    public boolean isDebugEnabled() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void d(String text, Object... args) {
        LogUtils.d(String.format(text, args));
    }

    @Override
    public void e(Throwable t, String text, Object... args) {
        LogUtils.e(String.format(text, args), t);
    }

    @Override
    public void e(String text, Object... args) {
        LogUtils.e(String.format(text, args));
    }

    @Override
    public void v(String text, Object... args) {
        LogUtils.v(String.format(text, args));
    }
}
