package com.remytabardel.henripotier.services.image;

import android.widget.ImageView;

/**
 * @author Remy Tabardel
 *         Interface to avoid link between lib Picasso/Glide and UI
 *         Used to load image in ImageView
 */

public interface ImageLoader {
    void load(String path, ImageView imageView);
    void loadGif(int resId, ImageView imageView);
}
