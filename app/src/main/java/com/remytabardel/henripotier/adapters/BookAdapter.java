package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.services.image.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title) TextView mTextViewTitle;
        @BindView(R.id.textview_price) TextView mTextViewPrice;
        @BindView(R.id.textview_isbn) TextView mTextViewIsbn;
        @BindView(R.id.imageview_cover) ImageView mImageViewCover;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;
    private ImageLoader mImageLoader;
    private List<Book> mDataset;

    public BookAdapter(Context context, ImageLoader imageLoader, List<Book> books) {
        mContext = context;
        mImageLoader = imageLoader;
        mDataset = books;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Book currentBook = mDataset.get(position);

        mImageLoader.load(currentBook.getCover(), holder.mImageViewCover);
        holder.mTextViewTitle.setText(currentBook.getTitle());
        holder.mTextViewIsbn.setText(mContext.getString(R.string.fragment_books_item_isbn, currentBook.getIsbn()));
        holder.mTextViewPrice.setText(mContext.getString(R.string.fragment_books_item_price, currentBook.getPrice()));

        //set theme colors
        holder.mTextViewTitle.setTextColor(currentBook.getBookTheme().getColorTextTitle());
        holder.mTextViewIsbn.setTextColor(currentBook.getBookTheme().getColorTextBody());
       // holder.mTextViewPrice.setTextColor(currentBook.getBookTheme().getColorTextBody());
        holder.itemView.setBackgroundColor(currentBook.getBookTheme().getColorBackground());

    }

    @Override public int getItemCount() {
        return mDataset.size();
    }
}
