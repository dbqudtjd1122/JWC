package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.bsyoo.jwc.MainActivity;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.Http_SignUp;
import com.example.bsyoo.jwc.hppt.Http_User;
import com.example.bsyoo.jwc.model.Model_User;
import com.example.bsyoo.jwc.user.Login.LoginActivity;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.util.List;

public class MypageActivity extends LoginInformation {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("마이페이지");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.mypage_tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.addTab(tabLayout.newTab().setText("회원정보관리"));
        tabLayout.addTab(tabLayout.newTab().setText("시리얼넘버관리"));

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.mypage_view_pager);

        // PagerAdater 생성
        MypageTabPagerAdapter pagerAdapter = new MypageTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter(pagerAdapter);

        // 탭 시작지점 정하는부분
        viewPager.setCurrentItem(0);

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
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(MypageActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
