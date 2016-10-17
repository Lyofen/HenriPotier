package com.remytabardel.henripotier.services.database;

import android.content.Context;

import com.remytabardel.henripotier.services.database.dbflow.DBFlowBookDao;
import com.remytabardel.henripotier.services.database.dbflow.DBFlowCartItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 */

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    Database provideDatabase(Context context) {
        return new Database(context);
    }

    @Provides
    @Singleton
    BookDao provideBookDao() {
        return new DBFlowBookDao();
    }

    @Provides
    @Singleton
    CartItemDao provideCartItemDao() {
        return new DBFlowCartItemDao();
    }
}