package com.remytabardel.henripotier.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.fragments.CartFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * @author Remy Tabardel
 */

public class CartFragmentEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        //CHANGER DE FRAGMENT ICI
    }

    @After
    public void tearDown() {

    }

    @Test
    public void test_1_ensureTextChangesWork() {

        onView(withId(R.id.action_cart)).perform(click());

        android.support.v4.app.Fragment fragment = mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag("CURRENT_FRAGMENT");
        assertEquals(CartFragment.class.getName(), fragment.getClass().getName());
        //onView(withId(R.id.fragment_container)).check(ViewAssertions.matches(withChild(withClassName(is(CartFragment.class.getName())))));

        Log.i("ESPRESSO", "nom du fragment 1 :" + fragment.getClass().getName());

       /* onView(withId(R.id.button_look_collection)).perform(click());
        fragment = mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag("CURRENT_FRAGMENT");

        Log.i("ESPRESSO", "nom du fragment 2 :" + fragment.getClass().getName());

        assertEquals(BooksFragment.class.getName(), fragment.getClass().getName());
        /*
        // Type text and then press the button.
        onView(withId(R.id.inputField))
                .perform(typeText("HELLO"), closeSoftKeyboard());
        onView(withId(R.id.changeText)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.inputField)).check(matches(withText("Lalala")));*/
    }

    @Test
    public void test_2_ensureTextChangesWork() {
        Fragment fragment = mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag("CURRENT_FRAGMENT");
        Log.i("ESPRESSO", "nom du fragment 3 :" + fragment.getClass().getName());

        /*onView(withId(R.id.button_look_collection)).perform(click());


        assertEquals(BooksFragment.class.getName(), fragment.getClass().getName());
        //onView(withId(R.id.fragment_container)).check(ViewAssertions.matches(withChild(withClassName(is(CartFragment.class.getName())))));

        /*
        // Type text and then press the button.
        onView(withId(R.id.inputField))
                .perform(typeText("HELLO"), closeSoftKeyboard());
        onView(withId(R.id.changeText)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.inputField)).check(matches(withText("Lalala")));*/

        /**
         *     onData(anything()) // We are using the position so don't need to specify a data matcher
         .inAdapterView(withId(R.id.lvItems)) // Specify the explicit id of the ListView
         .atPosition(1) // Explicitly specify the adapter item to use
         .perform(click());
         */
    }
}
