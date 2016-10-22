package com.remytabardel.henripotier.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Remy Tabardel
 *         if we need common feature between activities we can use this class
 */

public abstract class AbstractActivity extends AppCompatActivity {
    //stock in static field because we need this in espresso test
    public static final String TAG_CURRENT_FRAGMENT = "CURRENT_FRAGMENT";

    /**
     * replace fragment in the indicated fragment
     *
     * @param fragmentToDisplay
     * @param fragmentContainerId
     */
    public void replaceFragment(Fragment fragmentToDisplay, int fragmentContainerId) {
        if (fragmentToDisplay != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragmentDisplayed = fragmentManager.findFragmentByTag(TAG_CURRENT_FRAGMENT);

            //si on affiche pas déjà de fragment ou que le fragment que l'on souhaite afficher est différent de l'actuel
            if (currentFragmentDisplayed == null || !currentFragmentDisplayed.getClass().equals(fragmentToDisplay.getClass())) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(fragmentContainerId, fragmentToDisplay, TAG_CURRENT_FRAGMENT);
                fragmentTransaction.commit();
            }
        }
    }

    public Fragment getCurrentFragmentDisplayed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(TAG_CURRENT_FRAGMENT);
    }
}
