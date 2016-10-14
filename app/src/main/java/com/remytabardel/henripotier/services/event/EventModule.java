package com.remytabardel.henripotier.services.event;

import com.remytabardel.henripotier.services.event.eventbus.EventBusPublisher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 *         Provide EventPublisher to inject
 */

@Module
public class EventModule {
    /*
     * We can provide here EventPublisher with any implementation (eventbus, custom....no idea T_T), just change instantiation
     */
    @Provides
    @Singleton
    EventPublisher provideEventPublisher() {
        return new EventBusPublisher();
    }
}
