package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.listeners.CartAdapterListener;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.image.ImageLoader;
import com.remytabardel.henripotier.utils.ToastUtils;
import com.remytabardel.henripotier.views.QuantitySelectorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title) TextView mTextViewTitle;
        @BindView(R.id.textview_isbn) TextView mTextViewIsbn;
        @BindView(R.id.textview_price) TextView mTextViewPrice;
        @BindView(R.id.imageview_cover) ImageView mImageViewCover;
        @BindView(R.id.quantityselectorview) QuantitySelectorView mQuantitySelectorView;
        @BindView(R.id.imagebutton_delete) ImageButton mImageButtonDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;
    private ImageLoader mImageLoader;
    private ShoppingCart mShoppingCart;
    private List<CartItem> mDataset;
    private CartAdapterListener mCartAdapterListener;

    public CartAdapter(Context context, ShoppingCart shoppingCart, ImageLoader imageLoader, CartAdapterListener cartAdapterListener) {
        mContext = context;
        mImageLoader = imageLoader;
        mShoppingCart = shoppingCart;
        mDataset = mShoppingCart.getItems();
        mCartAdapterListener = cartAdapterListener;
        //we notify on initialisation too
        mCartAdapterListener.onItemCountChanged(mDataset.size());
        mCartAdapterListener.onTotalQuantityChanged(mShoppingCart.getCurrentItemsQuantity());
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        CartItem currentItem = mDataset.get(position);

        mImageLoader.load(currentItem.getBook().getCover(), holder.mImageViewCover);
        holder.mTextViewTitle.setText(currentItem.getBook().getTitle());
        holder.mTextViewIsbn.setText(mContext.getString(R.string.item_cart_isbn, currentItem.getIsbn()));
        holder.mTextViewPrice.setText(mContext.getString(R.string.item_cart_price, currentItem.getBook().getPrice()));
        holder.mQuantitySelectorView.setQuantity(currentItem.getQuantity());

        //we notify on listener when quantity change
        setQuantitySelectorViewListeners(holder.mQuantitySelectorView, currentItem);
        //when we delete an item we must notify on listener quantity and nb item change
        setButtomDeleteListener(holder.mImageButtonDelete, currentItem);
    }

    private void setButtomDeleteListener(ImageButton imageButtonRemove, final CartItem cartItem) {
        imageButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShoppingCart.deleteItem(cartItem.getIsbn())) {
                    mCartAdapterListener.onItemCountChanged(mDataset.size());
                    mCartAdapterListener.onTotalQuantityChanged(mShoppingCart.getCurrentItemsQuantity());
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void setQuantitySelectorViewListeners(final QuantitySelectorView quantitySelectorView, final CartItem cartItem) {
        quantitySelectorView.setOnClickMoreListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShoppingCart.addItem(cartItem.getIsbn())) {
                    quantitySelectorView.setQuantity(cartItem.getQuantity());
                    mCartAdapterListener.onTotalQuantityChanged(mShoppingCart.getCurrentItemsQuantity());
                } else {
                    ToastUtils.show(mContext, mContext.getString(R.string.shopping_cart_exceed_limit, mShoppingCart.getQuantityLimit()), Toast.LENGTH_SHORT);
                }
            }
        });

        quantitySelectorView.setOnClickLessListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShoppingCart.subtractItem(cartItem.getIsbn())) {
                    quantitySelectorView.setQuantity(cartItem.getQuantity());
                    mCartAdapterListener.onTotalQuantityChanged(mShoppingCart.getCurrentItemsQuantity());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}