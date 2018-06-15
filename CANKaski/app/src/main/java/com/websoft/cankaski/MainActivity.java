package com.websoft.cankaski;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigation;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigation = findViewById(R.id.nav);

        navigation.setNavigationItemSelectedListener(this);
    }

    public void clickEvents(View view) {
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    public void clickStalls(View view) {
        Intent intent = new Intent(this, StallsActivity.class);
        startActivity(intent);
    }

    public void clickOffers(View view) {
        Intent intent = new Intent(this, OffersActivity.class);
        startActivity(intent);
    }

    public void clickLocation(View view) {
        Snackbar.make(view, "Under Construction", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                Snackbar.make(navigation, "home", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                drawer.closeDrawer(Gravity.START);
                return true;
            case R.id.settings:
                Snackbar.make(navigation, "settings", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                drawer.closeDrawer(Gravity.START);
                return true;
            case R.id.about:
                Snackbar.make(navigation, "about", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                drawer.closeDrawer(Gravity.START);
                return true;

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START))
            drawer.closeDrawer(Gravity.START);
        else
            super.onBackPressed();
    }
}
