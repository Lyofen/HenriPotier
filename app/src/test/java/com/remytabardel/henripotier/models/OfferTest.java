package com.remytabardel.henripotier.models;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Remy Tabardel
 */

public class OfferTest {
    @Test
    public void check_minus_offer_discount() {
        float amount = 300f;
        Offer offer = new Offer(Offer.TYPE.MINUS, 63, 0, amount);
        assertEquals(63f, offer.getDiscount());
        assertEquals(237f, amount - offer.getDiscount());
    }

    @Test
    public void check_percentage_offer_discount() {
        float amount = 300f;
        Offer offer = new Offer(Offer.TYPE.PERCENTAGE, 30, 0, amount);
        assertEquals(90f, offer.getDiscount());
        assertEquals(210f, amount - offer.getDiscount());
    }

    @Test
    public void check_slice_offer_discount() {
        float amount = 300f;
        Offer offer = new Offer(Offer.TYPE.SLICE, 12, 70, amount);
        assertEquals(48f, offer.getDiscount());
        assertEquals(252f, amount - offer.getDiscount());
    }
}
