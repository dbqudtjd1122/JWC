package com.jwcnetworks.bsyoo.jwc.mainimage.camera;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.List;

public class CameraActivity extends AppCompatActivity {

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        setTitle("JWC");
        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.addTab(tabLayout.newTab().setText("카메라"));
        tabLayout.addTab(tabLayout.newTab().setText("녹화기"));

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // PagerAdater 생성
        CameraTabPagerAdapter pagerAdapter = new CameraTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter(pagerAdapter);

        // 탭 시작지점 정하는부분
        if(type.equals("카메라")) {
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

        netcheck = networkcheck();
        if (netcheck == true) {
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }
    }

    // 네트워크 체크
    private boolean networkcheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if (wifi == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                setValueFragment();
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    public void setValueFragment(){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    ((CameraFragment)fragment).setNet(true);
            }
        }
    }
}
