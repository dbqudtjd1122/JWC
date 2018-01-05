package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bsyoo.jwc.MainActivity;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpUser;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MypageActivity extends LoginInformation {

    private ModelUser user = new ModelUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("마이페이지");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        user.setUser_Number(pref.getInt("number_Set", -1));
        new MypageActivity.getLoginInfomation().execute(user);

        // TabLayout 초기화
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.mypage_tab_layout);
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
                // 텍스트 컬러값 지정
                // tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent));

                try {
                    // FIXME: 20.7.2015 WORKAROUND: https://code.google.com/p/android/issues/detail?id=175182 change indicator color
                    Field field = TabLayout.class.getDeclaredField("mTabStrip");
                    field.setAccessible(true);
                    Object value = field.get(tabLayout);

                    Method method = value.getClass().getDeclaredMethod("setSelectedIndicatorColor", Integer.TYPE);
                    method.setAccessible(true);
                    method.invoke(value, getResources().getColor(R.color.colorred));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                tabTextView.setText(tab.getText());

                if (i == 0) {
                    tabTextView.setTextSize(16);
                }
            }
        }*/
        // TabSelectedListener 설정 : 화면에서 tab을 클릭할때
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                // 탭 Text 사이즈 및 컬러 변경
                /*ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTextSize(16);
                        ((TextView) tabViewChild).setTextColor(0xFF000000);

                    }
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 탭 Text 사이즈 및 컬러 변경
                /*ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTextSize(14);
                        ((TextView) tabViewChild).setTextColor(0xFF000000);
                    }
                }*/
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

        // 회원 탈퇴에서 오는 Intent
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

        // 시리얼 등록에서 넘어오는 Intent
        if (requestCode == 7437) {
            if (resultCode == RESULT_OK) {
                setValueFragment();
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 회원정보 가져오기
    public class getLoginInfomation extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(MypageActivity.this);
            waitDlg.setMessage("회원정보를 가져오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpUser().getLoginInfomation(user);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser s) {
            super.onPostExecute(s);

            user = s;
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            setValueFragment();
        }
    }

    public void setValueFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    ((MypageFragment) fragment).setOrderuser(user);
            }
        }
    }
}
