package com.example.bionintelligence.presentation.info;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class InfoPageAdapter extends FragmentPagerAdapter {

    private int mSize;

    InfoPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public InfoPageAdapter(FragmentManager fm, int mSize) {
        super(fm);
        this.mSize = mSize;
    }

    @Override
    public Fragment getItem(int position) {
        return InfoPageFragment.newInstance(position);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if(((InfoPageFragment) object).getView()!=null){
            ((InfoPageFragment) object).getView().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}