package com.remytabardel.henripotier.services.event;

/**
 * @author Remy Tabardel
 *         Interface to avoid link between lib EventBus and UI
 */

public interface EventPublisher {
    /**
     * Publish an event, subscribers will receive event
     *
     * @param event
     */
    void post(Object event);

    /**
     * Needed to receive event notification
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * We must release inscription after use
     *
     * @param subscriber
     */
    void unregister(Object subscriber);
}
