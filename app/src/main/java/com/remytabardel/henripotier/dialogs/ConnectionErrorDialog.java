package com.remytabardel.henripotier.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.listeners.ConnectionErrorListener;
import com.remytabardel.henripotier.services.image.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 */

public class ConnectionErrorDialog extends AbstractDialog {
    @Inject ImageLoader mImageLoader;

    private final ConnectionErrorListener mListener;

    public ConnectionErrorDialog(Context context, ConnectionErrorListener listener) {
        super(context);
        mListener = listener;
    }

    @Override public void onShow() {

    }

    @Override public void onDismiss() {

    }

    @Override public void onViewCreated(Context context, View view) {
        ButterKnife.bind(this, view);
    }

    @Override public int getLayoutId() {
        return R.layout.dialog_connection_error;
    }

    @OnClick(R.id.button_retry)
    public void onClickButtonRetry() {
        dismiss();
        mListener.onRetryConnection();
    }

    @OnClick(R.id.button_quit)
    public void onClickButtonQuit() {
        dismiss();
        mListener.onQuitApplication();
    }
}
