package com.remytabardel.henripotier.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.activities.CartSummaryActivity;
import com.remytabardel.henripotier.activities.MainActivity;
import com.remytabardel.henripotier.adapters.CartAdapter;
import com.remytabardel.henripotier.listeners.CartAdapterListener;
import com.remytabardel.henripotier.services.cart.ShoppingCart;
import com.remytabardel.henripotier.services.image.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 */

public class CartFragment extends AbstractFragment implements CartAdapterListener {
    @Inject
    ShoppingCart mShoppingCart;
    @Inject
    ImageLoader mImageLoader;

    @BindView(R.id.view_empty)
    View mEmptyView;
    @BindView(R.id.view_full)
    View mFullView;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication.getInstance().getComponent().inject(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CartAdapter adapter = new CartAdapter(getContext(), mShoppingCart, mImageLoader, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemCountChanged(int count) {
        boolean isListEmpty = (count < 1);//== 0 should be ok but.. security

        //we show empty view if adapter is empty
        mEmptyView.setVisibility(isListEmpty ? View.VISIBLE : View.GONE);
        //we need full view visibility to render button under recyclerview
        mFullView.setVisibility(isListEmpty ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.button_look_collection)
    public void onClickButtonLookCollection() {
        //we simulate click on books menu
        ((MainActivity) getActivity()).performNavigationClick(R.id.nav_books);
    }

    @Override
    public void onTotalQuantityChanged(int totalQuantity) {
        getActivity().setTitle(getString(R.string.fragment_cart_title, totalQuantity));
    }

    @OnClick(R.id.button_purchase)
    public void onClickButtonPurchase() {
        startActivity(new Intent(getActivity(), CartSummaryActivity.class));
    }
}