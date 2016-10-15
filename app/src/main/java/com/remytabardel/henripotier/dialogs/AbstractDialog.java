package com.remytabardel.henripotier.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author Remy Tabardel
 */

public abstract class AbstractDialog extends AlertDialog {
    private boolean mIsOpen;
    private View mView;

    public AbstractDialog(Context context) {
        super(context);

        mIsOpen = false;
        setCancelable(false);
        onCreateView(context);
    }

    private void onCreateView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        mView = layoutInflater.inflate(getLayoutId(), null);
        setView(mView);
        onViewCreated(context, mView);
    }

    public void show() {
        if (!mIsOpen) {
            mIsOpen = true;
            onShow();
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (mIsOpen) {
            mIsOpen = false;
            onDismiss();
            super.dismiss();
        }
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public View getView() {
        return mView;
    }

    public abstract void onShow();

    public abstract void onDismiss();

    public abstract void onViewCreated(Context context, View view);

    public abstract int getLayoutId();
}
