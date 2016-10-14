package com.remytabardel.henripotier.services.network.retrofit.calls;

import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Remy Tabardel
 *         Define services to call with HenriPotierApi
 */

public interface RetrofitHenriPotierCalls {
    @GET("books")
    Call<List<BookJson>> getBooks();

}

