package com.example.bsyoo.jwc.user.terms;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.bsyoo.jwc.R;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("이용약관");

        Intent intent = getIntent();
        int i = intent.getIntExtra("순서", 0);

        // 뒤로가기 버튼
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.terms_tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.addTab(tabLayout.newTab().setText("회원가입 이용약관"));
        tabLayout.addTab(tabLayout.newTab().setText("개인정보 취급방침"));

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.terms_view_pager);

        // PagerAdater 생성
        TermsTabPagerAdapter pagerAdapter = new TermsTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter(pagerAdapter);

        // 탭 시작지점 정하는부분
        if(i==0) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }

        // ViewPager의 OnPageChangeListener 리스너 설정 : TabLayout과 ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // TabSelectedListener 설정 : 화면에서 tab을 클릭할때
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition());
            }
        });
    }
}
