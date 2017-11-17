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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.bsyoo.jwc.adapter.BackCloseHandler;
import com.example.bsyoo.jwc.camera.CameraActivity;
import com.example.bsyoo.jwc.user.LoginActivity;
import com.example.bsyoo.jwc.user.mypage.MypageActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {

    private LinearLayout ModelSearch;
    private ImageView main_img_left, main_img_right;
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
        viewflipper.setOnTouchListener(this);

        // Frame레이아웃 이미지뷰 제일 위로
        main_img_left = (ImageView) findViewById(R.id.main_img_left);
        main_img_left.bringToFront();
        main_img_right = (ImageView) findViewById(R.id.main_img_right);
        main_img_right.bringToFront();


    }
    public void onclick(View view){
        switch (view.getId()){
            case R.id.main_img_left:
                // 왼쪽으로 넘기기
                viewflipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in));
                viewflipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out));
                viewflipper.showPrevious();

                // 우측으로 3초 자동 넘기기
                viewflipper.stopFlipping();
                viewflipper.setAutoStart(true);
                viewflipper.setFlipInterval(3000);
                viewflipper.startFlipping();
                viewflipperrigth();
                viewflipper.startFlipping();
                break;
            case R.id.main_img_right:
                viewflipperrigth();
                viewflipper.showNext();

                viewflipper.stopFlipping();
                viewflipper.setAutoStart(true);
                viewflipper.setFlipInterval(3000);
                viewflipper.startFlipping();

                break;

            case R.id.viewpage1:
                Toast.makeText(MainActivity.this, "1번", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(this, BannerActivity.class);
                intent5.putExtra("0", 0);
                intent5.putExtra("title", "JDO-4008B");
                startActivity(intent5);
                break;
            case R.id.viewpage2:
                Toast.makeText(MainActivity.this, "2번", Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(this, BannerActivity.class);
                intent6.putExtra("0", 1);
                intent6.putExtra("title", "2017 컨퍼런스");
                startActivity(intent6);
                break;
            case R.id.viewpage3:
                Toast.makeText(MainActivity.this, "3번", Toast.LENGTH_SHORT).show();
                Intent intent7 = new Intent(this, BannerActivity.class);
                intent7.putExtra("0", 2);
                intent7.putExtra("title", "CCTV 렌탈 서비스");
                startActivity(intent7);
                break;
            case R.id.viewpage4:
                Toast.makeText(MainActivity.this, "4번", Toast.LENGTH_SHORT).show();
                Intent intent8 = new Intent(this, BannerActivity.class);
                intent8.putExtra("0", 3);
                intent8.putExtra("title", "협력업체 특별혜택");
                startActivity(intent8);
                break;
           /* case R.id.image_camera:
                Intent intent = new Intent(this, SeriesListActivity.class);
                intent.putExtra("type", "카메라");
                startActivity(intent);
                break;
            case R.id.image_record:
                Intent intent2 = new Intent(this, SeriesListActivity.class);
                intent2.putExtra("type", "녹화기");
                startActivity(intent2);
                break;*/
            case R.id.image_new:
                Intent intent3 = new Intent(this, SeriesActivity.class);
                intent3.putExtra("series", "신제품");
                startActivity(intent3);
                break;
            case R.id.image_camera:
                Intent intent4 = new Intent(this, CameraActivity.class);
                intent4.putExtra("type", "카메라");
                startActivity(intent4);
                break;
            case R.id.image_record:
                Intent intent2 = new Intent(this, CameraActivity.class);
                intent2.putExtra("type", "녹화기");
                startActivity(intent2);
                break;

        }
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

        if (id == R.id.menu_camera) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            intent.putExtra("type", "카메라");
            startActivity(intent);
        }else if (id == R.id.menu_record) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            intent.putExtra("type", "녹화기");
            startActivity(intent);
        }else if (id == R.id.menu_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_mypage) {
            Intent intent = new Intent(MainActivity.this, MypageActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_notice) {
            Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_event) {
            Intent intent = new Intent(MainActivity.this, EventActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_company) {
            Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_reference) {
            Toast.makeText(this, "준비중 입니다.", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.menu_logout) {
            Toast.makeText(this, "준비중 입니다.", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.menu_setting) {
            Toast.makeText(this, "준비중 입니다.", Toast.LENGTH_SHORT).show();
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

        // 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return
        if (viewflipper != viewflipper)
            return false;

        /*if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
        }*/
        return true;
    }

    // 뷰페이퍼 우측으로 넘기기
    public void viewflipperrigth() {
        viewflipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in));
        viewflipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out));
    }
}
