package com.remytabardel.henripotier.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.jobs.GetBestOfferJob;
import com.remytabardel.henripotier.services.job.JobScheduler;
import com.remytabardel.henripotier.utils.AnimUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class CartSummaryActivity extends AbstractActivity {

    @Inject
    JobScheduler mJobScheduler;

    @BindView(R.id.view_content)
    View mViewContent;
    @BindView(R.id.view_loading)
    View mViewLoading;
    @BindView(R.id.view_error)
    View mViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_summary);
        setTitle(R.string.activity_order_summary_title);

        ButterKnife.bind(this);
        MyApplication.getInstance().getComponent().inject(this);

        //we try to recover best commercial offer
        mJobScheduler.addInBackground(new GetBestOfferJob());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimUtils.crossfade(getApplicationContext(), mViewLoading, mViewContent);
            }
        }, 5000);
    }


}
