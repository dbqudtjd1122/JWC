package com.example.bsyoo.jwc.camera;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;

import java.util.List;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("CCTV 카메라");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#ff0000"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

        tabLayout.addTab( tabLayout.newTab().setText("돔") );
        tabLayout.addTab( tabLayout.newTab().setText("뷸렛") );
        tabLayout.addTab( tabLayout.newTab().setText("하우징") );

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // PagerAdater 생성
        CameraTabPagerAdapter pagerAdapter = new CameraTabPagerAdapter( getSupportFragmentManager(), tabLayout.getTabCount() );

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter( pagerAdapter );

        // 탭 시작지점 정하는부분
        viewPager.setCurrentItem(0);


        // ViewPager의 OnPageChangeListener 리스너 설정 : TabLayout과 ViewPager
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ));

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
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}
