package com.remytabardel.henripotier.services.network.retrofit.api;

import android.text.TextUtils;

import com.remytabardel.henripotier.jobs.SplashLoadingJob;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.models.Offer;
import com.remytabardel.henripotier.services.network.HenriPotierApi;
import com.remytabardel.henripotier.services.network.json.BookJson;
import com.remytabardel.henripotier.services.network.json.CommercialOffersJson;
import com.remytabardel.henripotier.services.network.json.OfferJson;
import com.remytabardel.henripotier.services.network.retrofit.calls.RetrofitHenriPotierCalls;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * @author Remy Tabardel
 *         Retrofit implementation of HenriPotierApi
 */

public class RetrofitHenriPotierApi extends AbstractRetrofitApi<RetrofitHenriPotierCalls> implements HenriPotierApi {
    public RetrofitHenriPotierApi(String baseUrl, OkHttpClient okHttpClient) {
        super(baseUrl, okHttpClient, RetrofitHenriPotierCalls.class);
    }

    /**
     * @return list of books or null if not working
     */
    @Override public List<BookJson> getBooks() {
        return execute(getCalls().getBooks());
    }

    @Override public CommercialOffersJson getCommercialOffers(List<CartItem> items) {
        String listIsbn = "";

        //loop on cartItem is enough because we need to add distinct isbn, dont need to loop with quantity
        for (int i = 0; i < items.size(); i++) {
            //i'm not sure api like me..
            if (items.get(i).getIsbn().equals(SplashLoadingJob.BONUS_BOOK_ISBN) == false) {
                listIsbn += items.get(i).getIsbn();
                listIsbn += (i < items.size() - 1) ? HenriPotierApi.ISBN_SEPARATOR : "";
            }
        }

        //possibility to have only remy tabardel isbn, so need to return fake offer
        //if list contains books + remy tabardel, best offer apply on all, so remy tabardel is not used to get offer but is used to compute final price
        return (TextUtils.isEmpty(listIsbn) && items.size() == 1) ? fakeCommercialOffers() : execute(getCalls().getCommercialOffers(listIsbn));
    }

    /**
     * need to fake when only remy tabardel is in cart
     *
     * @return
     */
    private CommercialOffersJson fakeCommercialOffers() {
        CommercialOffersJson commercialOffersJson = new CommercialOffersJson();
        commercialOffersJson.getOffers().add(new OfferJson(Offer.TYPE.MINUS.getJsonValue(), 0.01f, 0));

        return commercialOffersJson;
    }
}
