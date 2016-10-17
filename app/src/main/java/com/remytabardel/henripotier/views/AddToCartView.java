package com.remytabardel.henripotier.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.remytabardel.henripotier.R;

/**
 * @author Remy Tabardel
 */

public class AddToCartView extends LinearLayout {
    public AddToCartView(Context context) {
        super(context);
        init(context);
    }

    public AddToCartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddToCartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public AddToCartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(HORIZONTAL);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_add_to_cart, this, true);


    }
}
