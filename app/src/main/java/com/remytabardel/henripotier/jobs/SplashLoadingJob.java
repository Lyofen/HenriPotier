package com.remytabardel.henripotier.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.events.SplashLoadingEvent;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.database.Database;
import com.remytabardel.henripotier.services.database.Transaction;
import com.remytabardel.henripotier.services.event.EventPublisher;
import com.remytabardel.henripotier.services.image.ImageLoader;
import com.remytabardel.henripotier.services.job.jobqueue.JobQueuePriority;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.BookJson;
import com.remytabardel.henripotier.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Remy Tabardel
 *         Call HenriPotierApi to recover books
 */

public class SplashLoadingJob extends Job {
    //stock in static field because henri potier api doesnt want this isbn..
    public final static String BONUS_BOOK_ISBN = "le-dev-android";

    @Inject HenriPotierApi mHenriPotierApi;
    @Inject BookDao mBookDao;
    @Inject EventPublisher mEventPublisher;
    @Inject Database mDatabase;
    @Inject ImageLoader mImageLoader;

    public SplashLoadingJob() {
        //we dont use requireNetwork because we want handle exception
        super(new Params(JobQueuePriority.HIGH));

        MyApplication.getInstance().getComponent().inject(this);
    }

    @Override
    public void onAdded() {

    }

    /**
     * we need to post event for each exception and treat it in SplashActivity
     */
    private class SplashLoadingException extends Exception {
        private final SplashLoadingEvent mSplashLoadingEvent;

        public SplashLoadingException(SplashLoadingEvent splashLoadingEvent) {
            mSplashLoadingEvent = splashLoadingEvent;
        }

        public SplashLoadingEvent getEvent() {
            return mSplashLoadingEvent;
        }
    }

    @Override
    public void onRun() throws Throwable {
        List<BookJson> jsonBooks = recoverJsonBooks();

        LogUtils.i("HenriPotierApi.getBooks return " + jsonBooks.size() + " books");

        //i need to sell myself...
        addBonusBookData(jsonBooks);

        saveJsonInDatabase(jsonBooks);

        LogUtils.i("books insertBook in database successfull");

        //everything is ok, we can continue in SplashActivity
        mEventPublisher.post(new SplashLoadingEvent(SplashLoadingEvent.LOADING_RESULT_OK));
    }

    private void addBonusBookData(List<BookJson> jsonBooks) {
        jsonBooks.add(new BookJson(BONUS_BOOK_ISBN,
                "Rémy Tabardel",
                45,
                "android.resource://com.remytabardel.henripotier/drawable/cv_remytabardel"));
    }

    private void saveJsonInDatabase(List<BookJson> jsonBooks) throws Throwable {
        //we will validate transaction only if no error
        Transaction transaction = mDatabase.newTransaction();
        transaction.begin();

        //we need to delete all old books to replace with new data
        mBookDao.deleteAll();

        for (int i = 0; i < jsonBooks.size(); i++) {
            BookJson json = jsonBooks.get(i);

            //we recover cover's palette to personnalize books list
            Palette palette = mImageLoader.getPalette(getApplicationContext(), json.getCover());

            if (mBookDao.insertBook(json, i) == false
                    || mBookDao.insertBookTheme(getApplicationContext(), json.getIsbn(), palette) == false) {
                //if error, we end transaction before setSuccessful, so modifications will not be apply
                transaction.end();
                LogUtils.e("impossible to insertBook json book : " + json.toString());
                throw new SplashLoadingException(new SplashLoadingEvent(SplashLoadingEvent.LOADING_RESULT_ERR_INSERT));
            }
        }

        //no error, we can validate modifications
        transaction.setSuccessful();
        transaction.end();
    }

    private List<BookJson> recoverJsonBooks() throws Throwable {
        List<BookJson> jsonBooks = mHenriPotierApi.getBooks();

        if (jsonBooks == null || jsonBooks.isEmpty()) {
            LogUtils.e("no data from HenriPotierApi.getBooks");
            throw new SplashLoadingException(new SplashLoadingEvent(SplashLoadingEvent.LOADING_RESULT_ERR_INTERNET));
        }

        return jsonBooks;
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        SplashLoadingEvent splashLoadingEvent;

        //if we known exception, we recover event
        if (throwable instanceof SplashLoadingException) {
            splashLoadingEvent = ((SplashLoadingException) throwable).getEvent();
        } else {
            LogUtils.e("unknown error during SplashLoadingJob", throwable);
            splashLoadingEvent = new SplashLoadingEvent(SplashLoadingEvent.LOADING_RESULT_ERR_UNKNOWN);
        }

        mEventPublisher.post(splashLoadingEvent);
    }

    /**
     * we dont want to retry job, user choose to retry or quit application
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
