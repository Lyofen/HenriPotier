package com.remytabardel.henripotier.services.image.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.remytabardel.henripotier.services.image.ImageLoader;
import com.remytabardel.henripotier.utils.LogUtils;
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
        Picasso.with(mContext).load(path).fit().centerInside().into(imageView);
    }

    @Override public Palette getPalette(Context context, String path) {
        Palette palette = null;
        Bitmap bitmap;

        try {
            bitmap = Picasso.with(context).load(path).get();
            palette = Palette.from(bitmap).generate();
        } catch (Exception e) {
            //we will return null
            LogUtils.e("getPalette error", e);
        }

        return palette;
    }
}
