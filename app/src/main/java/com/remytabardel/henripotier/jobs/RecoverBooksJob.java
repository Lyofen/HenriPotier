package com.remytabardel.henripotier.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.remytabardel.henripotier.services.job.jobqueue.Priority;
import com.squareup.picasso.Picasso;

/**
 * @author Remy Tabardel
 *         Call HenriPotierApi to recover books
 */

public class RecoverBooksJob extends Job{
    public RecoverBooksJob() {
        super(new Params(Priority.HIGH).requireNetwork());
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {

    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
