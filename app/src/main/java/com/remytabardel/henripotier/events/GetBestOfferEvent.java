package com.remytabardel.henripotier.events;

import android.support.annotation.IntDef;

import com.remytabardel.henripotier.models.Offer;

/**
 * @author Remy Tabardel
 */

public class GetBestOfferEvent {
    @IntDef({OFFER_RESULT_OK, OFFER_RESULT_ERR_INTERNET, OFFER_RESULT_ERR_UNKNOWN})
    public @interface OfferResult {
    }

    public static final int OFFER_RESULT_OK = 1;
    public static final int OFFER_RESULT_ERR_INTERNET = 2;
    public static final int OFFER_RESULT_ERR_UNKNOWN = 3;

    private int mOfferResult;
    private Offer mBestOffer;

    public GetBestOfferEvent(@OfferResult int offerResult) {
        this(offerResult, null);
    }

    public GetBestOfferEvent(@OfferResult int offerResult, Offer bestOffer) {
        mOfferResult = offerResult;
        mBestOffer = bestOffer;
    }

    public @OfferResult int getResult() {
        return mOfferResult;
    }

    public Offer getBestOffer() {
        return mBestOffer;
    }
}
