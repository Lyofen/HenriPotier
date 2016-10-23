package com.remytabardel.henripotier;

import com.remytabardel.henripotier.activities.MainActivityTest;
import com.remytabardel.henripotier.services.AppComponent;
import com.remytabardel.henripotier.services.AppModule;
import com.remytabardel.henripotier.services.ShoppingCartTest;
import com.remytabardel.henripotier.services.cart.CartModule;
import com.remytabardel.henripotier.services.database.DatabaseModule;
import com.remytabardel.henripotier.services.event.EventModule;
import com.remytabardel.henripotier.services.image.ImageModule;
import com.remytabardel.henripotier.services.job.JobModule;
import com.remytabardel.henripotier.services.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Remy Tabardel
 *         register all test modules
 */

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,// TODO: 18/10/2016 mock this module for json api test
        EventModule.class,
        JobModule.class,
        ImageModule.class,
        DatabaseModule.class,
        CartModule.class})
public interface TestAppComponent extends AppComponent {
    void inject(ShoppingCartTest injectedClass);

    void inject(MainActivityTest injectedClass);
}
