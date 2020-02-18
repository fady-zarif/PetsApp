package com.example.fady.uspets.MainScreenModule;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdFragment;

public class MainScreenPagerAdapter extends FragmentStatePagerAdapter {

    int count;

    public MainScreenPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            AdvertisementFragment advertisementFragment = new AdvertisementFragment();
            return advertisementFragment;
        } else {
            CreateAdFragment createAdFragment = new CreateAdFragment();
            return createAdFragment;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
