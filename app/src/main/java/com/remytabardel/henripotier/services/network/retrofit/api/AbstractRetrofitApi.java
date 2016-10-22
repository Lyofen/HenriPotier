package com.remytabardel.henripotier.services.network.retrofit.api;

import com.remytabardel.henripotier.utils.LogUtils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Remy Tabardel
 *         Extends this class to use retrofit calls
 */

abstract class AbstractRetrofitApi<C> {

    private C mCalls;

    public AbstractRetrofitApi(String baseUrl, OkHttpClient okHttpClient, Class<C> callsClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mCalls = retrofit.create(callsClass);
    }

    public C getCalls() {
        return mCalls;
    }

    /**
     * @param call
     * @param <T>
     * @return object corresponding or null if not working
     */
    public <T> T execute(Call<T> call) {
        T result = null;

        if (call != null) {
            try {
                Response<T> response = call.execute();

                if (response.isSuccessful()) {
                    result = response.body();
                }
            } catch (Exception e) {
                LogUtils.e("impossible to call " + call.request().url().toString(), e);
                //we will return null for result
            }
        }

        return result;
    }
}

