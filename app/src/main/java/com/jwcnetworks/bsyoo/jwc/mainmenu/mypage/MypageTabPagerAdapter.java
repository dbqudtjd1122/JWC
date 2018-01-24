package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MypageTabPagerAdapter extends FragmentPagerAdapter{

    // tab 갯수
    private int tabCount = 0;

    public MypageTabPagerAdapter(FragmentManager fm,  int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new MypageTabFragment1();
                break;
            case 1:
                fragment = new MypageTabFragment2();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
