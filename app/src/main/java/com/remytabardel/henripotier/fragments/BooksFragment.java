package com.remytabardel.henripotier.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.adapters.BookAdapter;
import com.remytabardel.henripotier.services.database.BookDao;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class BooksFragment extends AbstractFragment {

    @Inject BookDao mBookDao;

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

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
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication.getInstance().getComponent().inject(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        BookAdapter myAdapter = new BookAdapter(getContext(), mBookDao.selectAll());
        mRecyclerView.setAdapter(myAdapter);
    }
}
