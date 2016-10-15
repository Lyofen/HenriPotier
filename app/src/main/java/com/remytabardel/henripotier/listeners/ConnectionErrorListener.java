package com.remytabardel.henripotier.listeners;

/**
 * @author Remy Tabardel
 */

public interface ConnectionErrorListener {
    void onRetryConnection();
    void onQuitApplication();
}
