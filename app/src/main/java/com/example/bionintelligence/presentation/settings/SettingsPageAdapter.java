package com.example.bionintelligence.presentation.settings;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.bionintelligence.presentation.settings.one.SettingsFragmentOne;
import com.example.bionintelligence.presentation.settings.two.SettingsFragmentTwo;

public class SettingsPageAdapter extends FragmentPagerAdapter {
    public SettingsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SettingsFragmentOne();
            case 1:
                return new SettingsFragmentTwo();
        }
        return null;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if(((Fragment) object).getView()!=null){
            ((Fragment) object).getView().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}