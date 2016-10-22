package com.remytabardel.henripotier.services.image;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

/**
 * @author Remy Tabardel
 *         Interface to avoid link between lib Picasso/Glide and UI
 *         Used to load image in ImageView
 */

public interface ImageLoader {
    void load(String path, ImageView imageView);
    Palette getPalette(Context context, String path);
}
