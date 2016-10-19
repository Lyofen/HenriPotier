package com.remytabardel.henripotier.listeners;

/**
 * @author Remy Tabardel
 */

public interface CartAdapterListener {
    void onItemCountChanged(int count);

    void onTotalQuantityChanged(int totalQuantity);
}
