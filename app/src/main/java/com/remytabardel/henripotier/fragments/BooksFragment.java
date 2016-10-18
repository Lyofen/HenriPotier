package com.remytabardel.henripotier.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.activities.MainActivity;
import com.remytabardel.henripotier.adapters.BookAdapter;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.image.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class BooksFragment extends AbstractFragment {

    @Inject
    BookDao mBookDao;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    ShoppingCart mShoppingCart;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_books, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_books, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            //we simulate click on cart menu
            ((MainActivity) getActivity()).performNavigationClick(R.id.nav_cart);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication.getInstance().getComponent().inject(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        //in landscape we can render more columns
        int nbColumns = getResources().getInteger(R.integer.fragment_books_nb_columns);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), nbColumns));

        BookAdapter adapter = new BookAdapter(getContext(), mShoppingCart, mImageLoader, mBookDao.selectAll());
        mRecyclerView.setAdapter(adapter);
    }
}
