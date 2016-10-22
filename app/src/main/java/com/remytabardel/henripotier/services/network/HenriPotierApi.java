package com.remytabardel.henripotier.services.network;

import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.network.json.BookJson;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;

import java.util.List;

/**
 * @author Remy Tabardel
 *         Interface to avoid link between lib Retrofit and UI
 *         Used to recover data about Henri Potier
 */

public interface HenriPotierApi {
    //used for getCommercialOffers service
    String ISBN_SEPARATOR = ",";

    /**
     * Get all Henri Potier's books data
     *
     * @return list of books, or null if not working
     */
    List<BookJson> getBooks();

    /**
     * Get commercial offers corresponding to items
     *
     * @param items
     * @return commercial offers, or null if not working
     */
    CommercialOffersJson getCommercialOffers(List<CartItem> items);
}
