package com.example.bsyoo.jwc.mainmenu.Company;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CompanyTabPagerAdapter extends FragmentPagerAdapter{

    // tab 갯수
    private int tabCount = 0;

    public CompanyTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new CompanyTabFragment1();
                break;
            case 1:
                fragment = new CompanyTabFragment2();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
