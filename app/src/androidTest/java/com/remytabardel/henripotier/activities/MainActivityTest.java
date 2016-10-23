package com.remytabardel.henripotier.activities;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.remytabardel.henripotier.MyMockApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.TestAppComponent;
import com.remytabardel.henripotier.fragments.AboutFragment;
import com.remytabardel.henripotier.fragments.BooksFragment;
import com.remytabardel.henripotier.fragments.CartFragment;
import com.remytabardel.henripotier.services.cart.ShoppingCart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * @author Remy Tabardel
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends AbstractActivityTest<MainActivity> {

    @Inject ShoppingCart mShoppingCart;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void before() {
        ((TestAppComponent) MyMockApplication.getInstance().getComponent()).inject(this);
        mShoppingCart.deleteAll();
    }

    @Test
    public void clickBooksMenu() {
        //test to open books screen
        clickMenu(R.id.nav_books);
        assertEquals(BooksFragment.class, getActivity().getCurrentFragmentDisplayed().getClass());
    }

    @Test
    public void clickCartMenu() {
        clickMenu(R.id.nav_cart);
        assertEquals(CartFragment.class, getActivity().getCurrentFragmentDisplayed().getClass());
    }

    @Test
    public void clickAboutMenu() {
        clickMenu(R.id.nav_about);
        assertEquals(AboutFragment.class, getActivity().getCurrentFragmentDisplayed().getClass());
    }

    private void clickMenu(int menuId) {
        // Open Drawer to click on navigation
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        //click on menu
        onView(withId(R.id.nav_view)).perform(navigateTo(menuId));
        //wait fragment changement
        sleep(200);
    }

    @Test
    public void clickCartAction() {
        onView(withId(R.id.action_cart)).perform(click());
        assertEquals(CartFragment.class, getActivity().getCurrentFragmentDisplayed().getClass());
    }

    @Test
    public void addBookToShoppingCart() {
        int itemPosition = 0;
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(itemPosition, ItemViewAction.clickChildViewWithId(R.id.addtocardview)));

        assertEquals(1, mShoppingCart.getItems().size());
    }

    @Test
    public void removeBookFromShoppingCart() {
        int itemPosition = 0;
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(itemPosition, ItemViewAction.clickChildViewWithId(R.id.addtocardview)));

        clickMenu(R.id.nav_cart);
        //click to remove item
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(itemPosition, ItemViewAction.clickChildViewWithId(R.id.imagebutton_delete)));

        assertEquals(0, mShoppingCart.getItems().size());
    }
}
