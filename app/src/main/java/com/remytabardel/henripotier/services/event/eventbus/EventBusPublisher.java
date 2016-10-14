package com.remytabardel.henripotier.services.event.eventbus;

import com.remytabardel.henripotier.services.event.EventPublisher;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Remy Tabardel
 *         EventBus implementation of EventPublisher
 */

public class EventBusPublisher implements EventPublisher {
    private EventBus eventBus;

    public EventBusPublisher() {
        eventBus = EventBus.getDefault();
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }
}
