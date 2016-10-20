package com.remytabardel.henripotier.events;

import android.support.annotation.IntDef;

/**
 * @author Remy Tabardel
 */

public class SplashLoadingEvent {
    @IntDef({LOADING_RESULT_OK, LOADING_RESULT_ERR_INTERNET, LOADING_RESULT_ERR_INSERT, LOADING_RESULT_ERR_UNKNOWN})
    public @interface LoadingResult {
    }

    public static final int LOADING_RESULT_OK = 1;
    public static final int LOADING_RESULT_ERR_INTERNET = 2;
    public static final int LOADING_RESULT_ERR_INSERT = 3;
    public static final int LOADING_RESULT_ERR_UNKNOWN = 4;

    private int mLoadingResult;

    public SplashLoadingEvent(@LoadingResult int loadingResult) {
        mLoadingResult = loadingResult;
    }

    public int getLoadingResult() {
        return mLoadingResult;
    }
}
