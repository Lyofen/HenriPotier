package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.image.ImageLoader;
import com.remytabardel.henripotier.views.AddToCartView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title)
        TextView mTextViewTitle;
        @BindView(R.id.textview_price)
        TextView mTextViewPrice;
        @BindView(R.id.imageview_cover)
        ImageView mImageViewCover;
        @BindView(R.id.relative_content)
        RelativeLayout mRelativeContent;
        @BindView(R.id.addtocardview)
        AddToCartView mAddToCartView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;
    private ImageLoader mImageLoader;
    private ShoppingCart mShoppingCart;
    private List<Book> mDataset;

    public BookAdapter(Context context, ShoppingCart shoppingCart, ImageLoader imageLoader, List<Book> books) {
        mContext = context;
        mShoppingCart = shoppingCart;
        mImageLoader = imageLoader;
        mDataset = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Book currentBook = mDataset.get(position);

        mImageLoader.load(currentBook.getCover(), holder.mImageViewCover);
        holder.mTextViewTitle.setText(currentBook.getTitle());
        holder.mTextViewPrice.setText(mContext.getString(R.string.fragment_books_item_price, Double.toString(currentBook.getPrice())));
        setAddToCartView(holder.mAddToCartView, currentBook);

        //set theme colors
        holder.mTextViewTitle.setTextColor(currentBook.getBookTheme().getColorTextTitle());
        holder.mRelativeContent.setBackgroundColor(currentBook.getBookTheme().getColorBackground());
    }

    private void setAddToCartView(final AddToCartView addToCartView, final Book book) {
        boolean isBookInCart = mShoppingCart.containsItem(book.getIsbn());
        addToCartView.setIcon(isBookInCart);

        //if already in cart we cant click
        if (isBookInCart) {
            addToCartView.setOnClickListener(null);
        }
        //if not in cart we can set click listener to add in cart
        else {
            addToCartView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    //if we can add book, not limited with quantity limit
                    if (mShoppingCart.addItem(book.getIsbn()) == true) {
                        addToCartView.showNext();
                        //to add more than 1 book, we must go in cart fragment
                        addToCartView.setOnClickListener(null);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
