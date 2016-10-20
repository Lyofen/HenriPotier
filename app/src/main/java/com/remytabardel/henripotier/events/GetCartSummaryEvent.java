package com.remytabardel.henripotier.events;

import android.support.annotation.IntDef;

import com.remytabardel.henripotier.models.CartSummary;

/**
 * @author Remy Tabardel
 */

public class GetCartSummaryEvent {
    @IntDef({SUMMARY_RESULT_OK, SUMMARY_RESULT_ERR_INTERNET, SUMMARY_RESULT_ERR_UNKNOWN})
    public @interface SummaryResult {
    }

    public static final int SUMMARY_RESULT_OK = 1;
    public static final int SUMMARY_RESULT_ERR_INTERNET = 2;
    public static final int SUMMARY_RESULT_ERR_UNKNOWN = 3;

    private int mSummaryResult;
    private CartSummary mCartSummary;

    public GetCartSummaryEvent(@SummaryResult int summaryResult) {
        this(summaryResult, null);
    }

    public GetCartSummaryEvent(@SummaryResult int summaryResult, CartSummary cartSummary) {
        mSummaryResult = summaryResult;
        mCartSummary = cartSummary;
    }

    public int getSummaryResult() {
        return mSummaryResult;
    }

    public CartSummary getCartSummary() {
        return mCartSummary;
    }
}
