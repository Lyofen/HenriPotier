package com.remytabardel.henripotier.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.events.GetCartSummaryEvent;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.job.jobqueue.Priority;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;

import javax.inject.Inject;

/**
 * @author Remy Tabardel
 */

public class GetBestOfferJob extends Job {
    @Inject
    ShoppingCart mShoppingCart;
    @Inject
    HenriPotierApi mHenriPotierApi;

    public GetBestOfferJob() {
        //we dont use requireNetwork because we want handle exception
        super(new Params(Priority.HIGH));

        MyApplication.getInstance().getComponent().inject(this);
    }

    /**
     * we need to post event for each exception and treat it in CartSummaryActivity
     */
    private class GetBestOfferException extends Exception {
        private final GetCartSummaryEvent mGetCartSummaryEvent;

        public GetBestOfferException(GetCartSummaryEvent getCartSummaryEvent) {
            mGetCartSummaryEvent = getCartSummaryEvent;
        }

        public GetCartSummaryEvent getEvent() {
            return mGetCartSummaryEvent;
        }
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        CommercialOffersJson commercialOffersJson = mHenriPotierApi.getCommercialOffers(mShoppingCart.getItems());

    }


    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected int getRetryLimit() {
        return 0;
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return new RetryConstraint(false);
    }
}
