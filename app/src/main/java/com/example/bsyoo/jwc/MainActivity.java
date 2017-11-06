package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.bsyoo.jwc.adapter.BackCloseHandler;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {

    private LinearLayout ModelSearch;
    private Button but_search_close;
    private TextView Login, Event, notice, company;
    private ViewFlipper viewflipper;
    private float down_x, up_x;
    private BackCloseHandler backCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }

        // 액션바 타이틀 및 배경색
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.jwc_logo_red);

        backCloseHandler = new BackCloseHandler(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // 뷰플리퍼 자동 넘김 (광고 배너)
        viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        int[] imageItems = new int[]{};

        for (int i : imageItems) {
            ImageView image = new ImageView(this);
            image.setImageResource(i);
            viewflipper.addView(image, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT));
        }
        viewflipper.setOnTouchListener(this);
        viewflipper.setAutoStart(true);
        viewflipper.setFlipInterval(3000);
        viewflipper.startFlipping();

        // 오른쪽방향으로 진행
        viewflipperrigth();
        viewflipper.startFlipping();

        Login = (TextView) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        Event = (TextView) findViewById(R.id.Event);
        Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });
        notice = (TextView) findViewById(R.id.notice);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        company = (TextView) findViewById(R.id.company);
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                startActivity(intent);
            }
        });
        viewflipper.setOnTouchListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } /*else {
            super.onBackPressed();
        }*/
        // 뒤로가기버튼 2번 누를경우 종료
        backCloseHandler.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.camera) {
            /*Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.jwc_l) {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            intent.putExtra("series", "JWC-L 시리즈");
            startActivity(intent);
        } else if (id == R.id.jwc_ptz) {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            intent.putExtra("series", "JWC-PTZ 시리즈");
            startActivity(intent);
        } else if (id == R.id.jwc_s) {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            intent.putExtra("series", "JWC-S 시리즈");
            startActivity(intent);
        } else if (id == R.id.qhd4mp) {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            intent.putExtra("series", "QHD 4MP ALL - HD DVR");
            startActivity(intent);
        }else if (id == R.id.all_lite) {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            intent.putExtra("series", "ALL-HD DVR LITE 모델");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // 타이틀바 돋보기
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        ModelSearch = (LinearLayout) findViewById(R.id.ModelSearch);

        if (id == R.id.action_button) {
            if (ModelSearch.getVisibility() == View.VISIBLE) {
                ModelSearch.setVisibility(View.GONE);
            } else {
                ModelSearch.setVisibility(View.VISIBLE);
                Toast.makeText(this, "제품을 검색해 주세요.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 스크롤뷰 안에서 ViewFlipper 부드럽게 적용
        v.getParent().requestDisallowInterceptTouchEvent(true);

        /*ImageView viewpage1 = (ImageView) findViewById(R.id.viewpage1);
        ImageView viewpage2 = (ImageView) findViewById(R.id.viewpage2);
        switch (v.getId()) {
            case R.id.viewpage1:
                Toast.makeText(this, "1번", Toast.LENGTH_SHORT).show();
                Intent intent0 = new Intent(this, BannerActivity.class);
                intent0.putExtra("0", 0);
                startActivity(intent0);
                return false;
            case R.id.viewpage2:
                Toast.makeText(this, "2번", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, BannerActivity.class);
                intent1.putExtra("0", 1);
                startActivity(intent1);
                return false;
            case R.id.viewpage3:
                Toast.makeText(this, "3번", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, BannerActivity.class);
                intent2.putExtra("0", 2);
                startActivity(intent2);
                return false;
        }*/

        // 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
        if (viewflipper != viewflipper)
            return false;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 터치 시작지점 x좌표 저장
            down_x = event.getX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 터치 끝난 지점 X좌표 저장
            up_x = event.getX();

            viewflipper.stopFlipping();

            if (up_x < down_x) {
                // 터치 할때 오른쪽방향으로 진행
                viewflipperrigth();
                viewflipper.showNext();
            } else if (up_x > down_x) {
                // 터치 할때 왼쪽방향으로 진행
                viewflipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
                viewflipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out));
                viewflipper.showPrevious();
            }
            viewflipper.startFlipping();

            // 오른쪽방향으로 진행
            viewflipperrigth();
            viewflipper.startFlipping();
        }
        return true;
    }

    public void viewflipperrigth() {
        viewflipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in));
        viewflipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out));
    }

    /*public void pageClick(View v) {
        switch (v.getId()) {
            case R.id.viewpage1:
                Toast.makeText(MainActivity.this, "1번", Toast.LENGTH_SHORT).show();
                Intent intent0 = new Intent(this, BannerActivity.class);
                intent0.putExtra("0", 0);
                startActivity(intent0);
                break;
            case R.id.viewpage2:
                Toast.makeText(MainActivity.this, "2번", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, BannerActivity.class);
                intent1.putExtra("0", 1);
                startActivity(intent1);
                break;
            case R.id.viewpage3:
                Toast.makeText(MainActivity.this, "3번", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, BannerActivity.class);
                intent2.putExtra("0", 2);
                startActivity(intent2);
                break;
        }
    }*/
}
