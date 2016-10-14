package com.remytabardel.henripotier.services.job;

import com.birbit.android.jobqueue.Job;

/**
 * @author Remy Tabardel
 */

public interface JobScheduler{
    void addInBackground(Job job);
}
