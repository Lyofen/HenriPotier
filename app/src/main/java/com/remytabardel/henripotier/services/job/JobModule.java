package com.remytabardel.henripotier.services.job;

import android.content.Context;

import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.remytabardel.henripotier.services.job.jobqueue.JobQueueLogger;
import com.remytabardel.henripotier.services.job.jobqueue.JobQueueScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 *         Provide JobScheduler to inject
 */

@Module
public class JobModule {

    /**
     * We can provide here JobScheduler with any implementation (JobQueue, Rx..), just change instantiation
     *
     * @param configuration
     * @return
     */
    @Provides
    @Singleton
    JobScheduler provideJobManager(Configuration configuration) {
        return new JobQueueScheduler(configuration);
    }

    @Provides
    Configuration provideConfiguration(Context context, CustomLogger customLogger) {
        return new Configuration.Builder(context)
                .customLogger(customLogger)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
    }

    @Provides
    CustomLogger proviCustomLogger() {
        return new JobQueueLogger();
    }
}

