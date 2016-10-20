package com.remytabardel.henripotier.models;

import java.util.List;

/**
 * @author Remy Tabardel
 *         dont need to create offer table, so we dont use dbflow annotations here
 *         we copy items list to link
 */

public class CartSummary {
    private String offerType;
    private double bestOfferValue;
    private double totalCartPriceValue;
    private List<CartItem> items;

    public CartSummary(String offerType, double bestOfferValue, double totalCartPriceValue, List<CartItem> items) {
        this.offerType = offerType;
        this.bestOfferValue = bestOfferValue;
        this.totalCartPriceValue = totalCartPriceValue;
        this.items = items;
    }
}
