package com.remytabardel.henripotier.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.job.jobqueue.Priority;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;

import javax.inject.Inject;

/**
 * @author Remy Tabardel
 */

public class CommercialOfferJob extends Job {
    @Inject ShoppingCart mShoppingCart;
    @Inject HenriPotierApi mHenriPotierApi;

    public CommercialOfferJob() {
        //we dont use requireNetwork because we want handle exception
        super(new Params(Priority.HIGH));

        MyApplication.getInstance().getComponent().inject(this);
    }

    @Override public void onAdded() {

    }

    @Override public void onRun() throws Throwable {
        CommercialOffersJson commercialOffersJson = mHenriPotierApi.getCommercialOffers(mShoppingCart.getItems());

    }

    @Override protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
