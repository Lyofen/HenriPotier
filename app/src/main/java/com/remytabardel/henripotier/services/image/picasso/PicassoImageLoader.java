package com.remytabardel.henripotier.services.image.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.remytabardel.henripotier.services.image.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * @author Remy Tabardel
 *         Picasso implementation of ImageLoader
 */

public class PicassoImageLoader implements ImageLoader {
    private final Context mContext;

    public PicassoImageLoader(Context context) {
        mContext = context;
    }

    @Override public void load(String path, ImageView imageView) {
        Picasso.with(mContext).load(path).into(imageView);
    }
}
