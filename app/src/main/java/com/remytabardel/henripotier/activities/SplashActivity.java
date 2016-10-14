package com.remytabardel.henripotier.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.events.RecoverBooksEvent;
import com.remytabardel.henripotier.jobs.RecoverBooksJob;
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
        mJobScheduler.addInBackground(new RecoverBooksJob());
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
    public void onRecoverBooksEvent(RecoverBooksEvent event) {
        //books have been inserted in database, we can continue
        if (event.isSuccess()) {

        } else {
            //here we should adapt message with internet connection, error inserting etc..
            //but we will consider only network error here

            //DIALOG HOOK
        }
    }
}
