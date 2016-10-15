package com.remytabardel.henripotier.services.image.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.remytabardel.henripotier.services.image.ImageLoader;

/**
 * @author Remy Tabardel
 *         Glide implementation of ImageLoader
 */

public class GlideImageLoader implements ImageLoader {
    private final Context mContext;

    public GlideImageLoader(Context context) {
        mContext = context;
    }

    @Override public void load(String path, ImageView imageView) {
        Glide.with(mContext).load(path).into(imageView);
    }

    @Override public void loadGif(int resId, ImageView imageView) {
        //picasso dont support gif
        Glide.with(mContext).load(resId).asGif().into(imageView);
    }

    @Override public Palette getPalette(Context context, String path) {
        Palette palette = null;
        Bitmap bitmap = null;

        try {
            bitmap = Glide.with(context).load(path).asBitmap().into(100, 100).get();
            if (bitmap != null && !bitmap.isRecycled()) {
                palette = Palette.from(bitmap).generate();
            }
        } catch (Exception e) {

        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }

        return palette;
    }
}