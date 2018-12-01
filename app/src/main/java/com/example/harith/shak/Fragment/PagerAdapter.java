package com.example.harith.shak.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentOne one = new FragmentOne();
                return one;
            case 1:
                FragmentTow tow = new FragmentTow();
                return tow;
            case 2:
                FragmentMap Map = new FragmentMap();
                return Map;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
