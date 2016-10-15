package com.remytabardel.henripotier.services.image;

import android.content.Context;

import com.remytabardel.henripotier.services.image.glide.GlideImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Remy Tabardel
 *         Provide ImageLoader to inject
 */

@Module
public class ImageModule {

    /**
     * We can provide here ImageLoader with any implementation (Picasso, Glide..), just change instantiation
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    ImageLoader provideImagesService(Context context) {
        return new GlideImageLoader(context);
        //return new PicassoImageLoader(context);
    }
}
