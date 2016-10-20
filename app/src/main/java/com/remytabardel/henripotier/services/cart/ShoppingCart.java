package com.remytabardel.henripotier.services.cart;

import android.content.Context;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import android.widget.Toast;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.database.CartItemDao;
import com.remytabardel.henripotier.utils.ToastUtils;

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
    private int mCurrentItemsQuantity;//avoid compute quantity every time, we can keep value
    private CartItemDao mCartItemDao;
    private Object mMutex;//we need to check add and remove, cant use both at the same time
    private Context mContext;

    public ShoppingCart(Context context, CartItemDao cartItemDao, BookDao bookDao) {
        QUANTITY_LIMIT = context.getResources().getInteger(R.integer.shopping_cart_quantity_limit);
        mContext = context;
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

        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();

            //if book added in cart refer to deleted book, we delete item
            if (bookDao.exists(cartItem.getIsbn()) == false) {
                mCartItemDao.delete(cartItem);
                iterator.remove();
            } else {
                mCurrentItemsQuantity += cartItem.getQuantity();
            }
        }
    }

    public int getTotalQuantity() {
        return mCurrentItemsQuantity;
    }

    public boolean isCartFull() {
        return mCurrentItemsQuantity == QUANTITY_LIMIT;
    }

    public List<CartItem> getItems() {
        return mItems;
    }

    /**
     * add book to list cart, if already in, add 1 to quantity
     *
     * @param isbn
     * @return true if added, false if cant add because exceed QUANTITY_LIMIT
     */
    @MainThread
    public boolean addItem(String isbn) {
        boolean itemAdded = false;

        if (TextUtils.isEmpty(isbn) == false) {
            synchronized (mMutex) {
                //if we dont exceed limit we can add
                if ((mCurrentItemsQuantity + 1) <= QUANTITY_LIMIT) {
                    CartItem cartItem = researchItem(isbn);

                    itemAdded = true;
                    mCurrentItemsQuantity += 1;

                    //we need to create item and insert in database
                    if (cartItem == null) {
                        cartItem = new CartItem(isbn, 1);
                        mCartItemDao.insert(cartItem);
                        mItems.add(cartItem);
                    }
                    //item already in cart, just add 1 quantity
                    else {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        mCartItemDao.update(cartItem);
                    }
                }
                //we exceed limit we can't add
                else {
                    ToastUtils.show(mContext, mContext.getString(R.string.shopping_cart_exceed_limit, Integer.toString(QUANTITY_LIMIT)), Toast.LENGTH_SHORT);
                }
            }
        }

        return itemAdded;
    }

    public boolean containsItem(String isbn) {
        return researchItem(isbn) != null;
    }

    /**
     * remove book from list cart, only work if quantity of item > 1
     * use deleteItem method if you want remove item from list
     *
     * @param isbn
     * @return true if removed, false if not found in list
     */
    @MainThread
    public boolean subtractItem(String isbn) {
        boolean itemRemoved = false;

        if (TextUtils.isEmpty(isbn) == false) {
            synchronized (mMutex) {
                CartItem cartItem = researchItem(isbn);

                //we can remove item only if quandtity > 2, if want delete use deleteItem method
                if (cartItem != null && cartItem.getQuantity() > 1) {
                    itemRemoved = true;
                    mCurrentItemsQuantity -= 1;

                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    mCartItemDao.update(cartItem);

                }
            }
        }

        return itemRemoved;
    }

    /**
     * remove item from cart list
     *
     * @param isbn
     * @return true if deleted, false if not found in list
     */
    public boolean deleteItem(String isbn) {
        boolean itemDeleted = false;

        if (TextUtils.isEmpty(isbn) == false) {
            synchronized (mMutex) {
                CartItem cartItem = researchItem(isbn);

                if (cartItem != null) {
                    itemDeleted = true;
                    mCurrentItemsQuantity -= cartItem.getQuantity();
                    mCartItemDao.delete(cartItem);
                    mItems.remove(cartItem);
                }
            }
        }

        return itemDeleted;
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

    public float getAmount() {
        float price = 0;

        for (CartItem item : mItems) {
            price += item.getQuantity() * item.getBook().getPrice();
        }

        return price;
    }
}
