package com.remytabardel.henripotier.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.remytabardel.henripotier.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Remy Tabardel
 */

public class AboutFragment extends AbstractFragment {
    @BindView(R.id.textview_libs)
    TextView mTextViewUsedLibs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle(getString(R.string.fragment_about_title));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextViewUsedLibs.setText(getLibsText());
    }

    private String getLibsText() {
        String text = "";
        String[] libs = getResources().getStringArray(R.array.fragment_about_libs);

        for (String lib : libs) {
            text += lib + "\n";
        }

        return text;
    }
}