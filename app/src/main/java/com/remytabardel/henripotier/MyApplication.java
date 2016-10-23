package com.remytabardel.henripotier;


import android.app.Application;
import android.os.StrictMode;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.remytabardel.henripotier.services.AppComponent;
import com.remytabardel.henripotier.services.AppModule;
import com.remytabardel.henripotier.services.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author Remy Tabardel
 */

public class MyApplication extends Application {
    //need application instance for injection
    private static MyApplication mInstance;
    protected AppComponent mAppComponent;

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //init component for dependencies injections
        mAppComponent = buildComponent();

        //init DBFlow for database
        FlowManager.init(new FlowConfig.Builder(this).build());

        if (BuildConfig.DEBUG) {
            //detect memory leak
            LeakCanary.install(this);

            //detect bad implementations
            initStrictMode();
        }
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}

