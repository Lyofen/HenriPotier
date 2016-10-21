package com.remytabardel.henripotier.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.remytabardel.henripotier.R;

/**
 * @author Remy Tabardel
 *         if we need common feature between activities we can use this class
 */

public abstract class AbstractActivity extends AppCompatActivity {
    /**
     * replace fragment in the indicated fragment
     * @param fragmentToDisplay
     * @param fragmentContainerId
     */
    public void replaceFragment(Fragment fragmentToDisplay, int fragmentContainerId) {
        if (fragmentToDisplay != null) {
            final String TAG_CURRENT_FRAGMENT = "CURRENT_FRAGMENT";
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
}
