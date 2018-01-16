package com.example.bsyoo.jwc.mainimage.camera;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CameraTabPagerAdapter  extends FragmentPagerAdapter{

    // tab 갯수
    private int tabCount = 0;

    public CameraTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new CameraTabFragment1();
                break;
            case 1:
                fragment = new CameraTabFragment2();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
