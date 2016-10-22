package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.models.Offer;
import com.remytabardel.henripotier.services.cart.ShoppingCart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private Context mContext;
    private ShoppingCart mShoppingCart;
    private Offer mBestOffer;

    public OrderAdapter(Context context, ShoppingCart shoppingCart, Offer bestOffer) {
        mContext = context;
        mShoppingCart = shoppingCart;
        mBestOffer = bestOffer;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title) TextView mTextViewTitle;
        @BindView(R.id.textview_description) TextView mTextViewDescription;
        @BindView(R.id.textview_total_price) TextView mTextViewTotalPrice;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * view to resume cart purchase
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_total_price) TextView mTextViewTotalPrice;
        @BindView(R.id.textview_discount) TextView mTextViewDiscount;
        @BindView(R.id.textview_final_price) TextView mTextViewFinalPrice;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override public int getItemViewType(int position) {
        //check for footer view
        return isFooterPosition(position) ? TYPE_FOOTER : TYPE_ITEM;
    }

    private boolean isFooterPosition(int position) {
        return (position == mShoppingCart.getItems().size());
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_footer, parent, false);
            FooterViewHolder viewHolder = new FooterViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooterPosition(position)) {
            onBindViewFooter((FooterViewHolder) holder);
        } else {
            onBindViewItem((ItemViewHolder) holder, mShoppingCart.getItems().get(position));
        }
    }

    private void onBindViewItem(ItemViewHolder holder, CartItem cartItem) {
        holder.mTextViewTitle.setText(cartItem.getBook().getTitle());
        holder.mTextViewDescription.setText(mContext.getString(R.string.item_order_description, cartItem.getQuantity(), cartItem.getBook().getPrice()));
        holder.mTextViewTotalPrice.setText(mContext.getString(R.string.item_order_total_price, (cartItem.getQuantity() * cartItem.getBook().getPrice())));
    }

    private void onBindViewFooter(FooterViewHolder holder) {
        float shoppingCartAmount = mShoppingCart.getAmount();
        holder.mTextViewTotalPrice.setText(mContext.getString(R.string.item_order_footer_total_price, shoppingCartAmount));
        holder.mTextViewDiscount.setText(mContext.getString(R.string.item_order_footer_discount, mBestOffer.getDiscount()));
        holder.mTextViewFinalPrice.setText(mContext.getString(R.string.item_order_footer_final_price, shoppingCartAmount - mBestOffer.getDiscount()));
    }

    @Override public int getItemCount() {
        //+ 1 for footer view
        return mShoppingCart.getItems().size() + 1;
    }
}
