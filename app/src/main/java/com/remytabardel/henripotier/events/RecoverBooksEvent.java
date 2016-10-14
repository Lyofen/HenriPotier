package com.remytabardel.henripotier.events;

/**
 * @author Remy Tabardel
 */

public class RecoverBooksEvent {
    private boolean mIsSuccess;

    public RecoverBooksEvent(boolean isSuccess) {
        mIsSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return mIsSuccess;
    }
}
