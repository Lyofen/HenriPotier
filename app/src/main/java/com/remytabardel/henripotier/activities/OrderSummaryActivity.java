package com.remytabardel.henripotier.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.adapters.OrderAdapter;
import com.remytabardel.henripotier.adapters.dividers.GrayDividerItemDecoration;
import com.remytabardel.henripotier.events.GetBestOfferEvent;
import com.remytabardel.henripotier.jobs.GetBestOfferJob;
import com.remytabardel.henripotier.models.Offer;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.event.EventPublisher;
import com.remytabardel.henripotier.services.job.JobScheduler;
import com.remytabardel.henripotier.utils.AnimUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 *         activity to resume cart and indicate discount and final price
 */

public class OrderSummaryActivity extends AbstractActivity {

    @Inject JobScheduler mJobScheduler;
    @Inject EventPublisher mEventPublisher;
    @Inject ShoppingCart mShoppingCart;

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;
    @BindView(R.id.progressbar) View mProgressBar;
    @BindView(R.id.view_error) View mViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        setTitle(R.string.activity_order_summary_title);

        ButterKnife.bind(this);
        MyApplication.getInstance().getComponent().inject(this);

        //we try to recover best commercial offer
        mJobScheduler.addInBackground(new GetBestOfferJob());
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventPublisher.register(this);
    }

    @Override
    public void onStop() {
        mEventPublisher.unregister(this);
        super.onStop();
    }

    /**
     * receive best offers in henripotierapi
     *
     * @param getBestOfferEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetBestOfferEvent(GetBestOfferEvent getBestOfferEvent) {
        switch (getBestOfferEvent.getResult()) {
            case GetBestOfferEvent.OFFER_RESULT_OK:
                populateRecyclerView(getBestOfferEvent.getBestOffer());
                AnimUtils.crossfade(this, mProgressBar, mRecyclerView);
                break;
            //we treat all errors with same message, but we can also adapt him
            case GetBestOfferEvent.OFFER_RESULT_ERR_INTERNET:
            case GetBestOfferEvent.OFFER_RESULT_ERR_UNKNOWN:
            default:
                //we can't recover offer, so we render error message
                AnimUtils.crossfade(this, mProgressBar, mViewError);
                break;
        }
    }

    /**
     * init content in recyclerview
     *
     * @param bestoffer
     */
    private void populateRecyclerView(Offer bestoffer) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GrayDividerItemDecoration(this));

        OrderAdapter adapter = new OrderAdapter(this, mShoppingCart, bestoffer);
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button_return)
    public void onClickButtonReturn() {
        finish();
    }

    @OnClick(R.id.button_retry)
    public void onClickButtonRetry() {
        //dont need to show animation here
        mProgressBar.setVisibility(View.VISIBLE);
        mViewError.setVisibility(View.GONE);

        //retry to recover offer
        mJobScheduler.addInBackground(new GetBestOfferJob());
    }
}
