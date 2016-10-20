package com.remytabardel.henripotier.services;

import com.remytabardel.henripotier.activities.OrderSummaryActivity;
import com.remytabardel.henripotier.activities.SplashActivity;
import com.remytabardel.henripotier.dialogs.ConnectionErrorDialog;
import com.remytabardel.henripotier.fragments.BooksFragment;
import com.remytabardel.henripotier.fragments.CartFragment;
import com.remytabardel.henripotier.fragments.DebugFragment;
import com.remytabardel.henripotier.jobs.GetBestOfferJob;
import com.remytabardel.henripotier.jobs.SplashLoadingJob;
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
 */

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,
        EventModule.class,
        JobModule.class,
        ImageModule.class,
        DatabaseModule.class,
        CartModule.class})
public interface AppComponent {
    void inject(SplashLoadingJob injectedClass);

    void inject(SplashActivity injectedClass);

    void inject(ConnectionErrorDialog injectedClass);

    void inject(BooksFragment injectedClass);

    void inject(CartFragment injectedClass);

    void inject(DebugFragment injectedClass);

    void inject(GetBestOfferJob injectedClass);

    void inject(OrderSummaryActivity inhectedClass);
}
