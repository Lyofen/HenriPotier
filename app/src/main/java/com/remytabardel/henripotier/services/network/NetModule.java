package com.remytabardel.henripotier.services.network;

import android.content.Context;

import com.remytabardel.henripotier.BuildConfig;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.services.network.retrofit.api.RetrofitHenriPotierApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Remy Tabardel
 *         Provide api to inject
 */

@Module
public class NetModule {
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return logging;
    }

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(6000, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * We can provide here HenriPotierApi with any implementation (retrofit, volley..), just change instantiation
     *
     * @param context      provided by AppModule
     * @param okHttpClient provided by NetModule, we can specify name to use specific configuration of httpClient (timeout etc..)
     * @return
     */
    @Provides
    @Singleton
    HenriPotierApi provideHenriPotierApi(Context context, OkHttpClient okHttpClient) {
        String baseUrl = context.getString(R.string.api_henri_potier_url);
        return new RetrofitHenriPotierApi(baseUrl, okHttpClient);
    }
}
