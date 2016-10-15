package com.remytabardel.henripotier.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.models.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title) TextView mTextViewTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Context mContext;
    private List<Book> mDataset;

    public BookAdapter(Context context, List<Book> books) {
        mContext = context;
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

        holder.mTextViewTitle.setText(currentBook.getTitle());
    }

    @Override public int getItemCount() {
        return mDataset.size();
    }
}
