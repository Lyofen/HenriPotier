package com.remytabardel.henripotier.services.cart;

import android.content.Context;
import android.text.TextUtils;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.database.CartItemDao;

import java.util.Iterator;
import java.util.List;

/**
 * @author Remy Tabardel
 *         Manage shopping cart to add, remove and save in database
 */

public class ShoppingCart {
    //we cant purchase lot of quantity because of get limitation
    private final int QUANTITY_LIMIT;

    private List<CartItem> mItems;
    private int mCurrentItemsQuantity;
    private CartItemDao mCartItemDao;
    private Object mMutex;//we need to check add and remove, cant use both at the same time

    public ShoppingCart(Context context, CartItemDao cartItemDao, BookDao bookDao) {
        QUANTITY_LIMIT = context.getResources().getInteger(R.integer.shopping_cart_quantity_limit);
        mCartItemDao = cartItemDao;
        mItems = cartItemDao.selectAll();
        mMutex = new Object();
        mCurrentItemsQuantity = 0;

        //we need to check if book registered in cart already exist, if not we must remove it
        checkItems(bookDao);
    }

    /**
     * check in cart list if books already exist since last session
     *
     * @param bookDao
     */
    private void checkItems(BookDao bookDao) {
        Iterator<CartItem> iterator = mItems.iterator();

        if (iterator.hasNext()) {
            CartItem cartItem = iterator.next();

            if (bookDao.exists(cartItem.getIsbn()) == false) {
                mCartItemDao.delete(cartItem);
                iterator.remove();
            } else {
                mCurrentItemsQuantity += cartItem.getQuantity();
            }
        }
    }

    /**
     * add book to list cart, if already in, add 1 to quantity
     *
     * @param isbn
     * @return true if added, false if cant add because exceed QUANTITY_LIMIT
     */
    public boolean addItem(String isbn) {
        boolean itemAdded = false;

        if (TextUtils.isEmpty(isbn) == false) {
            synchronized (mMutex) {
                CartItem cartItem = researchItem(isbn);

                //we need to create item and insert in database
                if (cartItem == null) {
                    itemAdded = true;
                    cartItem = new CartItem(isbn, 1);
                    mCartItemDao.insert(cartItem);
                    mItems.add(cartItem);
                }
                //if we dont exceed limit
                else if ((mCurrentItemsQuantity + 1) <= QUANTITY_LIMIT) {
                    itemAdded = true;
                    mCurrentItemsQuantity += 1;
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    mCartItemDao.update(cartItem);
                }
            }
        }

        return itemAdded;
    }

    public boolean containsItem(String isbn) {
        return researchItem(isbn) != null;
    }

    /**
     * remove book from list cart, if is the last item, remove entirely from list
     *
     * @param isbn
     * @return true if removed, false if not found in list
     */
    public boolean removeItem(String isbn) {
        boolean itemRemoved = false;

        if (TextUtils.isEmpty(isbn) == false) {
            synchronized (mMutex) {
                CartItem cartItem = researchItem(isbn);

                if (cartItem != null) {
                    itemRemoved = true;

                    //if we remove the last item of book, we need to delete it from list
                    //we can use == but prefer <= for security
                    if (cartItem.getQuantity() <= 1) {
                        mCartItemDao.delete(cartItem);
                        mItems.remove(cartItem);
                    }
                    //we have more than 1 item of this book, so we can just remove 1
                    else {
                        cartItem.setQuantity(cartItem.getQuantity() - 1);
                        mCartItemDao.update(cartItem);
                    }
                }
            }
        }

        return itemRemoved;
    }

    /**
     * research item in cart list with isbn
     *
     * @param isbn
     * @return
     */
    private CartItem researchItem(String isbn) {
        for (CartItem item : mItems) {
            if (item.getIsbn().equals(isbn)) {
                return item;
            }
        }

        return null;
    }
}
