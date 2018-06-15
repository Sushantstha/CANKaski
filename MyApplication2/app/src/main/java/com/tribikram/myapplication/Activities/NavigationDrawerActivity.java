package com.tribikram.myapplication.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.tribikram.myapplication.Fragment.CameraFragment;
import com.tribikram.myapplication.Fragment.GalleryFragment;
import com.tribikram.myapplication.Fragment.SettingFragment;
import com.tribikram.myapplication.R;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(Gravity.START);
        
        switch (item.getItemId()) {
            case R.id.nav_camera:
                CameraFragment cameraFragment = new CameraFragment();
                fm.beginTransaction().add(R.id.linear, cameraFragment, "Camera").commit();
                return true;
            case R.id.nav_gallery:
                GalleryFragment galleryFragment = new GalleryFragment();
                fm.beginTransaction().add(R.id.linear, galleryFragment, "Gallery").commit();
                return true;
            case R.id.nav_setting:
                SettingFragment settingFragment = new SettingFragment();
                fm.beginTransaction().replace(R.id.linear, settingFragment, "Setting").commit();
                return true;
        }

        return false;
    }
}
