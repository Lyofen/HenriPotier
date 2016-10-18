package com.remytabardel.henripotier.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.remytabardel.henripotier.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class QuantitySelectorView extends LinearLayout {
    @BindView(R.id.imagebutton_quantity_less)
    ImageButton mImageButtonQuantityLess;
    @BindView(R.id.imagebutton_quantity_more)
    ImageButton mImageButtonQuantityMore;
    @BindView(R.id.textview_quantity)
    TextView mTextViewQuantity;

    public QuantitySelectorView(Context context) {
        super(context);
        init(context);
    }

    public QuantitySelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public QuantitySelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public QuantitySelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_quantity_selector, this, true);

        ButterKnife.bind(this);
    }

    public void setQuantity(int quantity) {
        mTextViewQuantity.setText(Integer.toString(quantity));
        //indicate we can't subtract when 1 quantity
        mImageButtonQuantityLess.setAlpha(quantity > 1 ? 1.0f : 0.2f);
    }

    public void setOnClickMoreListener(OnClickListener onClickListener) {
        mImageButtonQuantityMore.setOnClickListener(onClickListener);
    }

    public void setOnClickLessListener(OnClickListener onClickListener) {
        mImageButtonQuantityLess.setOnClickListener(onClickListener);
    }

}
