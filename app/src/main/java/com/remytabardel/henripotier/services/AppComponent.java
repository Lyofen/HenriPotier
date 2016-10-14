package com.remytabardel.henripotier.services;

import com.remytabardel.henripotier.services.event.EventModule;
import com.remytabardel.henripotier.services.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Remy Tabardel
 */

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,
        EventModule.class})
public interface AppComponent {
}
