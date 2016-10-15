package com.remytabardel.henripotier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.events.SplashLoadingEvent;
import com.remytabardel.henripotier.jobs.SplashLoadingJob;
import com.remytabardel.henripotier.services.event.EventPublisher;
import com.remytabardel.henripotier.services.job.JobScheduler;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {
    @Inject JobScheduler mJobScheduler;
    @Inject EventPublisher mEventPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyApplication.getInstance().getComponent().inject(this);

        //we look on HenriPotierApi for new data
        mJobScheduler.addInBackground(new SplashLoadingJob());
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
     * Event sent by RecoverBooksJob to recover books from HenriPotierApi
     *
     * @param event
     */
    @Subscribe
    public void onRecoverBooksEvent(SplashLoadingEvent event) {
        switch (event.getLoadingResult()) {
            case SplashLoadingEvent.LOADING_RESULT_OK:
                startActivity(new Intent(this, MainActivity.class));
                break;
            //we should render message for every situation, but we will render only internet pb for the moment (most likely)
            case SplashLoadingEvent.LOADING_RESULT_ERR_INSERT:
            case SplashLoadingEvent.LOADING_RESULT_ERR_INTERNET:
            case SplashLoadingEvent.LOADING_RESULT_ERR_UNKNOWN:
            default:
                break;
        }
    }
}
