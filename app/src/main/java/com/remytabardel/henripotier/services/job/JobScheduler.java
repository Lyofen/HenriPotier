package com.remytabardel.henripotier.services.job;

import com.birbit.android.jobqueue.Job;

/**
 * @author Remy Tabardel
 *         interface to avoid link between AndroidJobQueue and UI
 */

public interface JobScheduler {
    void addInBackground(Job job);
}
