package com.remytabardel.henripotier.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;

/**
 * @author Remy Tabardel
 */

public class AnimUtils {
    /**
     * crossfade 2 views
     * source url : https://developer.android.com/training/animation/crossfade.html#animate
     *
     * @param viewToHide
     * @param viewToSee
     */
    public static void crossfade(Context context, final View viewToHide, final View viewToSee) {
        int shortAnimationDuration = context.getResources().getInteger(android.R.integer.config_shortAnimTime);

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        viewToSee.setAlpha(0f);
        viewToSee.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        viewToSee.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        viewToHide.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewToHide.setVisibility(View.GONE);
                        viewToHide.setAlpha(1f);
                    }
                });
    }
}
