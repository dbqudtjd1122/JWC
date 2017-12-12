package com.example.bsyoo.jwc.mainimage.Technology;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TechnologyTabPagerAdapter extends FragmentPagerAdapter {

    // tab 갯수
    private int tabCount = 0;

    public TechnologyTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new TechnologyTabFragment1();
                break;
            case 1:
                fragment = new TechnologyTabFragment2();
                break;
            case 2:
                fragment = new TechnologyTabFragment3();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
