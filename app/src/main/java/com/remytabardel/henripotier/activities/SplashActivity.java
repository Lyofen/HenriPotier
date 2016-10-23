package com.remytabardel.henripotier.activities;

import android.content.Intent;
import android.os.Bundle;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.dialogs.ConnectionErrorDialog;
import com.remytabardel.henripotier.events.SplashLoadingEvent;
import com.remytabardel.henripotier.jobs.SplashLoadingJob;
import com.remytabardel.henripotier.listeners.ConnectionErrorListener;
import com.remytabardel.henripotier.services.event.EventPublisher;
import com.remytabardel.henripotier.services.job.JobScheduler;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * @author Remy Tabardel
 *         activity to recover books content from henri potier api and load palette colors
 */

public class SplashActivity extends AbstractActivity implements ConnectionErrorListener {
    @Inject JobScheduler mJobScheduler;
    @Inject EventPublisher mEventPublisher;

    private ConnectionErrorDialog mConnectionErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyApplication.getInstance().getComponent().inject(this);

        mConnectionErrorDialog = new ConnectionErrorDialog(this, this);

        /*we look on HenriPotierApi for new data
        * we could avoid this job if we had already launch app because we had save in database
        * but it means we should check price etc.. for changements, so we forvce to update at launch
        */
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

    @Override
    public void onBackPressed() {
        //we disable back in this screen, wait the job
    }

    /**
     * Event sent by RecoverBooksJob to recover books from HenriPotierApi
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecoverBooksEvent(SplashLoadingEvent event) {
        switch (event.getResult()) {
            case SplashLoadingEvent.LOADING_RESULT_OK:
                //all is ok, we can start main activity
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            //we should render message for every situation, but we will render only internet pb for the moment (most likely)
            case SplashLoadingEvent.LOADING_RESULT_ERR_INSERT:
            case SplashLoadingEvent.LOADING_RESULT_ERR_INTERNET:
            case SplashLoadingEvent.LOADING_RESULT_ERR_UNKNOWN:
            default:
                mConnectionErrorDialog.show();
                break;
        }
    }

    @Override
    public void onRetryConnection() {
        //retry to recover data
        mJobScheduler.addInBackground(new SplashLoadingJob());
    }

    @Override
    public void onQuitApplication() {
        finish();
    }
}
