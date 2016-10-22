package com.remytabardel.henripotier;

import com.remytabardel.henripotier.services.AppComponent;
import com.remytabardel.henripotier.services.AppModule;

/**
 * @author Remy Tabardel
 */

public class MyMockApplication extends MyApplication {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}