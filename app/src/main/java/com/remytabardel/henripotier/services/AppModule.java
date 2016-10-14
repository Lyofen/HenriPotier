package com.remytabardel.henripotier.services;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 */

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.mApplication;
    }
}