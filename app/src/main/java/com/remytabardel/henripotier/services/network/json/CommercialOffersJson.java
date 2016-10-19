package com.remytabardel.henripotier.services.network.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Remy Tabardel
 *         Used for HenriPotierApi
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommercialOffersJson {

    private List<OfferJson> offers = new ArrayList<>();

    public CommercialOffersJson() {
    }

    public CommercialOffersJson(List<OfferJson> offers) {
        this.offers = offers;
    }

    public List<OfferJson> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferJson> offers) {
        this.offers = offers;
    }
}
