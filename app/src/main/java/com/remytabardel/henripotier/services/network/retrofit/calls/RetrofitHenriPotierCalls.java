package com.remytabardel.henripotier.services.network.retrofit.calls;

import com.remytabardel.henripotier.services.network.json.BookJson;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Remy Tabardel
 *         Define services to call with HenriPotierApi
 */

public interface RetrofitHenriPotierCalls {
    @GET("books")
    Call<List<BookJson>> getBooks();

    @GET("books/{list_isbn}/commercialOffers")
    Call<CommercialOffersJson> getCommercialOffers(@Path("list_isbn") String listIsbn);
}

