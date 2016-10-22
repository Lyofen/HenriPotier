package com.remytabardel.henripotier.services;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import com.remytabardel.henripotier.MyMockApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.TestAppComponent;
import com.remytabardel.henripotier.services.cart.ShoppingCart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;

/**
 * @author Remy Tabardel
 */

@RunWith(AndroidJUnit4.class)
public class ShoppingCartTest {

    @Inject ShoppingCart mShoppingCart;
    private int mQuantityLimit;// quantity max defined in config file

    @Before
    public void before() {
        ((TestAppComponent) MyMockApplication.getInstance().getComponent()).inject(this);
        Context appContext = InstrumentationRegistry.getTargetContext();

        mQuantityLimit = appContext.getResources().getInteger(R.integer.shopping_cart_quantity_limit);
        mShoppingCart.deleteAll();
    }

    @Test
    public void addItem() {
        assertEquals(true, mShoppingCart.addItem("isbn"));
        assertEquals(1, mShoppingCart.getItems().size());
    }

    @Test
    public void addItemWithQuantityLimit() {
        for (int i = 0; i < mQuantityLimit + 10; i++) {
            //we cant add if we have already reached quantity limit
            assertEquals((i < mQuantityLimit), mShoppingCart.addItem("isbn"));
        }
    }

    @Test
    public void addTwoSameItems() {
        String isbn = "isbn";
        mShoppingCart.addItem(isbn);
        mShoppingCart.addItem(isbn);

        assertEquals(1, mShoppingCart.getItems().size());//there is 1 sort of item
        assertEquals(2, mShoppingCart.getCurrentItemsQuantity());//but 2 if the same
    }

    @Test
    public void addTwoDifferentsItems() {
        mShoppingCart.addItem("isbn1");
        mShoppingCart.addItem("isbn2");

        assertEquals(2, mShoppingCart.getItems().size());
        assertEquals(2, mShoppingCart.getCurrentItemsQuantity());
    }

    @Test
    public void subtractItem() {
        String isbn = "isbn";
        mShoppingCart.addItem(isbn);
        mShoppingCart.addItem(isbn);

        assertEquals(true, mShoppingCart.subtractItem(isbn));
        assertEquals(1, mShoppingCart.getCurrentItemsQuantity());

    }

    @Test
    public void deleteItem() {
        String isbn = "isbn";

        mShoppingCart.addItem(isbn);

        assertEquals(true, mShoppingCart.deleteItem(isbn));
        assertEquals(0, mShoppingCart.getItems().size());
    }
}
