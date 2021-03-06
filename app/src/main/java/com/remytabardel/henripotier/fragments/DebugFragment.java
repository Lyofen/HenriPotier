package com.remytabardel.henripotier.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.remytabardel.henripotier.BuildConfig;
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.services.database.Database;
import com.remytabardel.henripotier.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 */

public class DebugFragment extends AbstractFragment {
    @BindView(R.id.textview_app_config) TextView mTextViewAppConfig;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debug, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle(getString(R.string.fragment_debug_title));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextViewAppConfig.setText(getAppConfifDescription());
    }

    private String getAppConfifDescription() {

        return "Version name : " + BuildConfig.VERSION_NAME + "\n"
                + "Version code : " + BuildConfig.VERSION_CODE + "\n"
                + "HenriPotierApi url : " + getString(R.string.api_henri_potier_url);
    }
}