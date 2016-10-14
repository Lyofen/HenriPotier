package com.remytabardel.henripotier.services.network;

import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 *         Interface to avoid link between lib Retrofit and UI
 *         Used to recover data about Henri Potier
 */

public interface HenriPotierApi {
    /**
     * Get all Henri Potier's books data
     *
     * @return list of books, or null if not working
     */
    List<BookJson> getBooks();
}
