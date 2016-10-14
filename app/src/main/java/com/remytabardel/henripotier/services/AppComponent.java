package com.remytabardel.henripotier.services;

import com.remytabardel.henripotier.activities.SplashActivity;
import com.remytabardel.henripotier.jobs.RecoverBooksJob;
import com.remytabardel.henripotier.services.database.DatabaseModule;
import com.remytabardel.henripotier.services.event.EventModule;
import com.remytabardel.henripotier.services.image.ImageModule;
import com.remytabardel.henripotier.services.job.JobModule;
import com.remytabardel.henripotier.services.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Remy Tabardel
 */

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,
        EventModule.class,
        JobModule.class,
        ImageModule.class,
        DatabaseModule.class})
public interface AppComponent {
    void inject(RecoverBooksJob injectedClass);

    void inject(SplashActivity injectedClass);
}
