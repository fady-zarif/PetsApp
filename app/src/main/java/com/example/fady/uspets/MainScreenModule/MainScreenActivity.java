package com.example.fady.uspets.MainScreenModule;

import android.content.Intent;
import android.os.Bundle;

import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferencesClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.ICreateAdwithActivityHolder;
import com.example.fady.uspets.PetApp;
import com.example.fady.uspets.R;
import com.example.fady.uspets.RegistrationModule.RegistrationActivity;
import com.example.fady.uspets.USPetsMain.UsPetsMainView;
import com.example.fady.uspets.myAdvertisement.MyAdvertisementActivity;
import com.example.fady.uspets.profilemodule.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenActivity extends UsPetsMainView implements NavigationView.OnNavigationItemSelectedListener, ICreateAdwithActivityHolder {

    private final int VIEW_PAGER_FIRST_SCREEN = 0;
    private final int VIEW_PAGER_SECOND_SCREEN = 1;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    DrawerLayout drawer;
    @Inject
    FirebaseUserClass firebaseUserClass;
    @Inject
    SharedPreferencesClass sharedPreferencesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_title));
        setSupportActionBar(toolbar);
        ((PetApp) getApplicationContext()).getDaggerApplicationComponent().injectMainScreenActivty(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();

//        RelativeLayout relativeLayout = findViewById(R.id.myRelative);
//        CreateAdFragment createAdFragment = new CreateAdFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.myRelative, createAdFragment).commit();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//

        sharedPreferencesClass.putStringInPref("aaa", "aaaa");
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.viewpager_tab1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.viewpager_tab2)));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        MainScreenPagerAdapter mainScreenPagerAdapter = new MainScreenPagerAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(mainScreenPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navProfile) {
            startActivity(new Intent(MainScreenActivity.this, ProfileActivity.class));
        } else if (id == R.id.navAd) {
            startActivity(new Intent(MainScreenActivity.this, MyAdvertisementActivity.class));
        } else if (id == R.id.navMessage) {

        } else if (id == R.id.navLogOut) {
            this.finish();
            firebaseUserClass.logOutUser();
            startActivity(new Intent(MainScreenActivity.this, RegistrationActivity.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onCreateAdSuccess() {
        viewPager.setCurrentItem(VIEW_PAGER_FIRST_SCREEN);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}
