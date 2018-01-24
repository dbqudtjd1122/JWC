package com.jwcnetworks.bsyoo.jwc.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragments = new Fragment[4];

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new ViewPager1();
        fragments[1] = new ViewPager2();
        fragments[2] = new ViewPager3();
        fragments[3] = new ViewPager4();
    }

    //아래의 메서드들의 호출 주체는 ViewPager이다.
    //ListView와 원리가 같다.

    /*
     * 여러 프레그먼트 중 어떤 프레그먼트를 보여줄지 결정
     */
    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    /*
     * 보여질 프레그먼트가 몇개인지 결정
     */
    public int getCount() {
        return fragments.length;
    }
}
