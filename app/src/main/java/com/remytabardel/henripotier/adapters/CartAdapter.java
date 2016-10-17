package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.image.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title)
        TextView mTextViewTitle;
        @BindView(R.id.textview_isbn)
        TextView mTextViewIsbn;
        @BindView(R.id.textview_price)
        TextView mTextViewPrice;
        @BindView(R.id.imageview_cover)
        ImageView mImageViewCover;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;
    private ImageLoader mImageLoader;
    private List<CartItem> mDataset;

    public CartAdapter(Context context, ImageLoader imageLoader, List<CartItem> items) {
        mContext = context;
        mImageLoader = imageLoader;
        mDataset = items;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        CartItem currentItem = mDataset.get(position);

        mImageLoader.load(currentItem.getBook().getCover(), holder.mImageViewCover);
        holder.mTextViewTitle.setText(currentItem.getBook().getTitle());
        holder.mTextViewIsbn.setText(mContext.getString(R.string.fragment_cart_item_isbn, currentItem.getIsbn()));
        holder.mTextViewPrice.setText(mContext.getString(R.string.fragment_cart_item_price, Double.toString(currentItem.getBook().getPrice())));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}