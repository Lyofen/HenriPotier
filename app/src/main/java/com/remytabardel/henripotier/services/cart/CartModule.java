package com.remytabardel.henripotier.services.cart;

import android.content.Context;

import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.database.CartItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 */
@Module
public class CartModule {
    @Provides
    @Singleton
    ShoppingCart provideShoppingCart(Context context, CartItemDao cartItemDao, BookDao bookDao) {
        return new ShoppingCart(context, cartItemDao, bookDao);
    }

}
