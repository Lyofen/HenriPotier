package com.remytabardel.henripotier.services.job.jobqueue;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.config.Configuration;
import com.remytabardel.henripotier.services.job.JobScheduler;

/**
 * @author Remy Tabardel
 *         JobQueue implementation of JobScheduler
 */

public class JobQueueScheduler extends com.birbit.android.jobqueue.JobManager implements JobScheduler {
    public JobQueueScheduler(Configuration configuration) {
        super(configuration);
    }

    @Override public void addInBackground(Job job) {
        super.addJobInBackground(job);
    }
}