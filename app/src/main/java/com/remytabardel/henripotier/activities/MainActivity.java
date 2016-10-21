package com.remytabardel.henripotier.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.remytabardel.henripotier.BuildConfig;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.fragments.AboutFragment;
import com.remytabardel.henripotier.fragments.BooksFragment;
import com.remytabardel.henripotier.fragments.CartFragment;
import com.remytabardel.henripotier.fragments.DebugFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 */

public class MainActivity extends AbstractActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int FRAGMENT_CONTAINER_ID = R.id.fragment_container;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();

        initNavigation();

        //we set the first fragment only on the first onCreate
        if (savedInstanceState == null) {
            //we perform click to set first screen and set menu books checked
            performNavigationClick(R.id.nav_books);
        }
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigation() {
        mNavigationView.setNavigationItemSelectedListener(this);

        //we can add dev menu in debug for help features (export database, look version for testers..)
        if (BuildConfig.DEBUG) {
            Menu menu = mNavigationView.getMenu();
            MenuItem menuItem = menu.add(R.id.group_more, R.id.nav_debug, 2, R.string.nav_main_item_debug);
            menuItem.setIcon(android.R.drawable.ic_menu_more);
            menuItem.setVisible(true);
            menuItem.setCheckable(true);
        }
    }

    @OnClick(R.id.fab)
    public void onClickFloatingButton(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void performNavigationClick(int navId) {
        mNavigationView.getMenu().performIdentifierAction(navId, 0);
        //we need to check item because perform dont do it
        mNavigationView.setCheckedItem(navId);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //we show floating button only on books fragment
        setFloatingButtonVisibility(item.getItemId());

        switch (item.getItemId()) {
            case R.id.nav_books:
                replaceFragment(new BooksFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_cart:
                replaceFragment(new CartFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_about:
                replaceFragment(new AboutFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_debug:
                replaceFragment(new DebugFragment(), FRAGMENT_CONTAINER_ID);
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFloatingButtonVisibility(int currentItemId) {
        if (currentItemId == R.id.nav_about) {
            mFloatingActionButton.show();
        } else {
            mFloatingActionButton.hide();
        }
    }

}
