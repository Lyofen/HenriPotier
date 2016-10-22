package com.remytabardel.henripotier.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.events.GetBestOfferEvent;
import com.remytabardel.henripotier.models.Offer;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.event.EventPublisher;
import com.remytabardel.henripotier.services.job.jobqueue.JobQueuePriority;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;
import com.remytabardel.henripotier.services.network.json.OfferJson;
import com.remytabardel.henripotier.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Remy Tabardel
 *         use list of shopping cart isbn to recover best offer from henri potier api
 */

public class GetBestOfferJob extends Job {
    @Inject ShoppingCart mShoppingCart;
    @Inject HenriPotierApi mHenriPotierApi;
    @Inject EventPublisher mEventPublisher;

    public GetBestOfferJob() {
        //we dont use requireNetwork because we want handle exception
        super(new Params(JobQueuePriority.HIGH));

        MyApplication.getInstance().getComponent().inject(this);
    }

    /**
     * we need to post event for each exception and treat it in CartSummaryActivity
     */
    private class GetBestOfferException extends Exception {
        private final GetBestOfferEvent mGetCartSummaryEvent;

        public GetBestOfferException(GetBestOfferEvent getCartSummaryEvent) {
            mGetCartSummaryEvent = getCartSummaryEvent;
        }

        public GetBestOfferEvent getEvent() {
            return mGetCartSummaryEvent;
        }
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        List<OfferJson> jsonOffers = recoverJsonOffers();

        //we dont use json classes directly because api , attribut.. can change easily, not models
        List<Offer> offers = transformJsonToModels(jsonOffers);

        Offer bestOffer = selectBestOffer(offers);

        mEventPublisher.post(new GetBestOfferEvent(GetBestOfferEvent.OFFER_RESULT_OK, bestOffer));
    }

    private Offer selectBestOffer(List<Offer> offers) {
        float bestDiscount = 0;
        Offer bestOffer = null;

        for (Offer offer : offers) {
            float currentDiscount = offer.getDiscount();

            if (currentDiscount > bestDiscount) {
                bestOffer = offer;
            }
        }

        return bestOffer;
    }

    private List<Offer> transformJsonToModels(List<OfferJson> jsonOffers) {
        List<Offer> modelOffers = new ArrayList<>();

        for (OfferJson offerJson : jsonOffers) {
            modelOffers.add(new Offer(offerJson, mShoppingCart.getAmount()));
        }

        return modelOffers;
    }

    private List<OfferJson> recoverJsonOffers() throws Throwable {
        CommercialOffersJson commercialOffersJson = mHenriPotierApi.getCommercialOffers(mShoppingCart.getItems());

        //we consider here it is forced to have an offer
        if (commercialOffersJson == null || commercialOffersJson.getOffers().isEmpty()) {
            throw new GetBestOfferException(new GetBestOfferEvent(GetBestOfferEvent.OFFER_RESULT_ERR_INTERNET));
        }

        return commercialOffersJson.getOffers();
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        GetBestOfferEvent getBestOfferEvent;

        //if we known exception, we recover event
        if (throwable instanceof GetBestOfferException) {
            getBestOfferEvent = ((GetBestOfferException) throwable).getEvent();
        } else {
            LogUtils.e("unknown error during GetBestOfferJob", throwable);
            getBestOfferEvent = new GetBestOfferEvent(GetBestOfferEvent.OFFER_RESULT_ERR_UNKNOWN);
        }

        mEventPublisher.post(getBestOfferEvent);
    }

    /**
     * we dont want to retry job, user choose to retry or return to cart list
     * @return
     */
    @Override
    protected int getRetryLimit() {
        return 0;
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return new RetryConstraint(false);
    }
}
