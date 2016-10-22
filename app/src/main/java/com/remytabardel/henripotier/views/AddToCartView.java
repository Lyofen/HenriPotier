package com.remytabardel.henripotier.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ViewSwitcher;

import com.remytabardel.henripotier.R;

/**
 * @author Remy Tabardel
 * View show in item_book to indicate if u can add to cart or if book is already in cart
 */

public class AddToCartView extends ViewSwitcher {
    private static final int CHILD_ADD = 0;
    private static final int CHILD_DONE = 1;

    public AddToCartView(Context context) {
        super(context);
        init(context);
    }

    public AddToCartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        int padding = 10;
        setPadding(padding, padding, padding, padding);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_add_to_cart, this, true);

        setInAnimation(context, R.anim.set_flip_in);
        setOutAnimation(context, R.anim.set_flip_out);
    }


    public void setIcon(boolean isBookInCart) {
        this.setDisplayedChild(isBookInCart ? CHILD_DONE : CHILD_ADD);
    }
}
