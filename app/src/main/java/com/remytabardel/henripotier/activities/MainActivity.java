package com.remytabardel.henripotier.activities;

import android.os.Bundle;
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
import com.remytabardel.henripotier.MyApplication;
import com.remytabardel.henripotier.R;
import com.remytabardel.henripotier.fragments.BooksFragment;
import com.remytabardel.henripotier.fragments.CartFragment;
import com.remytabardel.henripotier.services.event.EventPublisher;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Remy Tabardel
 */

public class MainActivity extends AbstractActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_CONTAINER_ID = R.id.fragment_container;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();

        initNavigation();

        //we set the first fragment to display
        replaceFragment(new BooksFragment(), FRAGMENT_CONTAINER_ID);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_books:
                replaceFragment(new BooksFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_cart:
                replaceFragment(new CartFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_about:
                replaceFragment(new CartFragment(), FRAGMENT_CONTAINER_ID);
                break;
            case R.id.nav_debug:
                replaceFragment(new CartFragment(), FRAGMENT_CONTAINER_ID);
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
