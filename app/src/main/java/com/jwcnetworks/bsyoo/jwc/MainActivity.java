package com.jwcnetworks.bsyoo.jwc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.view.ViewPager;
import android.view.View.OnClickListener;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.adapter.BackCloseHandler;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpNotice;
import com.jwcnetworks.bsyoo.jwc.mainimage.News.NewsActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.banner.MobileLiveActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.camera.CameraActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.cctvinstall.CctvInstallActivity;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpUser;
import com.jwcnetworks.bsyoo.jwc.mainmenu.Company.CompanyActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.agency.AgencyActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.Cases.CasesActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.Technology.TechnologyActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.SetUp;
import com.jwcnetworks.bsyoo.jwc.mainmenu.notice.EventInfoActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.series.ModelSearchActivity;
import com.jwcnetworks.bsyoo.jwc.mainimage.series.SeriesActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.notice.EventActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.notice.NoticeActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.notice.NoticeInfoActivity;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.mainimage.school.SchoolActivity;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.mypage.MypageActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.mypage.PwCheckActivity;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;
import com.jwcnetworks.bsyoo.jwc.viewpager.ViewPagerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


public class MainActivity extends LoginInformation
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    private LinearLayout ModelSearch;
    private BackCloseHandler backCloseHandler;

    // ViewPager
    private ImageView img[] = new ImageView[4];
    private ViewPager viewPager = null;
    private Thread thread = null;
    private Handler handler = null;

    private Toolbar toolbar;
    private Boolean netcheck = true;  // 네트워크 연결확인

    // 버전체크
    public String rtn, verSion;
    public AlertDialog.Builder alt_bld;

    // 메인 이미지
    public ModelNotice imgNotice = new ModelNotice();
    private ImageView main_event2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 액션바 타이틀 및 배경색
        getSupportActionBar().setTitle("");
        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));  // 액션바 컬러지정
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);  // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setLogo(R.drawable.actionbar_logo); // 액션바 이미지


        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
            //getWindow().setStatusBarColor(0x00ff0000);
            //getWindow().setBackgroundDrawable(d);
        }

        /*// 상태 표시 줄 높이와 일치하도록 상태바에 채우기를 설정합니다.
         toolbar.setPadding (0, getStatusBarHeight(), 0, 0);

        // 상태바 투명하게하여 액션바의 백그라운드 같이적용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/

        // 뒤로가기 2번클릭시 종료
        backCloseHandler = new BackCloseHandler(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);

        // 로그인정보가 있는경우
        if (pref.getString("id_Set", "").toString().equals("") || pref.getString("id_Set", "").toString().equals(null)) {
        } else {
            Loginsave();
        }

        netcheck = networkcheck();
        if (netcheck == true) {
            // 메인 이미지 가져오기
            main_event2 = (ImageView) findViewById(R.id.main_event2);
            new MainActivity.getMainImage().execute("메인");

            // 최신버전 체크후 업데이트 권장
            alt_bld = new AlertDialog.Builder(this);
            new Version().execute();
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 4523);
        }

        // 푸시클릭 후..
        Intent fcmintent = getIntent();
        String page = fcmintent.getStringExtra("page");
        String title = fcmintent.getStringExtra("title");
        String message = fcmintent.getStringExtra("message");
        try {
            if(page != null){
                if(page.equals("dvr") && islevel >= 1){ // DVR 시리얼 등록
                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    intent.putExtra("page", "dvr");
                    startActivityForResult(intent, 7823);
                } else if(page.equals("agency") && islevel >= 10){  // 대리점 네트웍스
                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    intent.putExtra("page", "agency");
                    startActivityForResult(intent, 7823);
                } else if(page.equals("wed")){      // 수요쿠폰
                    Intent intent = new Intent(getApplicationContext(), EventInfoActivity.class);
                    ModelNotice event = new ModelNotice();
                    event.setNotice_title("매주 수요일 할인쿠폰!");
                    event.setImg_info("http://jwcnet.godohosting.com/app/jwc_app/img/event/Wednesday_S.jpg");
                    intent.putExtra("event", event);
                    startActivity(intent);
                } else if(page.equals("redfriday")){     // 레드프라이데이
                    Intent intent = new Intent(getApplicationContext(), NoticeInfoActivity.class);
                    ModelNotice notice = new ModelNotice();
                    notice.setNotice_title("매주 금요일 레드프라이데이!");
                    notice.setImg_info("http://jwcnet.godohosting.com/app/jwc_app/img/event/redfriday_S.jpg");
                    intent.putExtra("notice", notice);
                    startActivity(intent);
                } else if(page.equals("normal")){
                    Intent intent = new Intent(getApplicationContext(), FCMPopupActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("message", message);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsStrting = sw.toString();
            Log.e("push error", exceptionAsStrting);
            e.printStackTrace();
        }

        // ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        img[0] = (ImageView) findViewById(R.id.img_viewpager1);
        img[1] = (ImageView) findViewById(R.id.img_viewpager2);
        img[2] = (ImageView) findViewById(R.id.img_viewpager3);
        img[3] = (ImageView) findViewById(R.id.img_viewpager4);
        for (int i = 0; i < img.length; i++) {
            img[i].setOnClickListener(this);
        }

        // 보이는화면이 몇번째 View인지에 따라 설정하는 옵션값
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    img[1].setImageResource(R.drawable.page_not);
                    img[2].setImageResource(R.drawable.page_not);
                    img[3].setImageResource(R.drawable.page_not);
                    img[0].setImageResource(R.drawable.page_select);
                } else if (position == 1) {
                    img[0].setImageResource(R.drawable.page_not);
                    img[2].setImageResource(R.drawable.page_not);
                    img[3].setImageResource(R.drawable.page_not);
                    img[1].setImageResource(R.drawable.page_select);
                } else if (position == 2) {
                    img[0].setImageResource(R.drawable.page_not);
                    img[1].setImageResource(R.drawable.page_not);
                    img[3].setImageResource(R.drawable.page_not);
                    img[2].setImageResource(R.drawable.page_select);
                } else if (position == 3) {
                    img[0].setImageResource(R.drawable.page_not);
                    img[1].setImageResource(R.drawable.page_not);
                    img[2].setImageResource(R.drawable.page_not);
                    img[3].setImageResource(R.drawable.page_select);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(1);
                } else if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2);
                } else if (viewPager.getCurrentItem() == 2) {
                    viewPager.setCurrentItem(3);
                } else if (viewPager.getCurrentItem() == 3) {
                    viewPager.setCurrentItem(0);
                }
            }
        };
        thread = new Thread() {
            //run은 jvm이 쓰레드를 채택하면, 해당 쓰레드의 run메서드를 수행한다.
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(4000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.image_new:
                Intent intent1 = new Intent(getApplicationContext(), SeriesActivity.class);
                intent1.putExtra("new", "1");
                startActivity(intent1);
                break;
            case R.id.image_camera:
                Intent intent2 = new Intent(getApplicationContext(), CameraActivity.class);
                intent2.putExtra("type", "카메라");
                startActivity(intent2);
                break;
            case R.id.image_record:
                Intent intent3 = new Intent(getApplicationContext(), CameraActivity.class);
                intent3.putExtra("type", "녹화기");
                startActivity(intent3);
                break;
            case R.id.img_supporter:
                Intent intnet4 = new Intent(getApplicationContext(), SchoolActivity.class);
                startActivityForResult(intnet4, 489);
                break;
            case R.id.img_company:
                Intent intent5 = new Intent(getApplicationContext(), CompanyActivity.class);
                startActivity(intent5);
                break;
            case R.id.img_agency:
                Intent intent6 = new Intent(getApplicationContext(), AgencyActivity.class);
                startActivity(intent6);
                break;
            case R.id.img_tech:
                Intent intent7 = new Intent(getApplicationContext(), TechnologyActivity.class);
                startActivity(intent7);
                break;
            case R.id.img_cctvinstall:
                Intent intent8 = new Intent(getApplicationContext(), CctvInstallActivity.class);
                startActivity(intent8);
                break;
            case R.id.img_downloads:
                Intent intent9 = new Intent(getApplicationContext(), CasesActivity.class);
                startActivity(intent9);
                break;
            case R.id.img_notice:
                Intent intent11 = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent11);
                break;
            case R.id.img_event:
                Intent intent12 = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent12);
                break;
            case R.id.img_live:
                Intent intent13 = new Intent(getApplicationContext(), MobileLiveActivity.class);
                startActivity(intent13);
                break;
            case R.id.img_news:
                Intent intent14 = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent14);
                break;
            case R.id.btn_modelsearch:
                EditText et_modelsearch = (EditText) findViewById(R.id.et_modelsearch);
                Intent intent10 = new Intent(getApplicationContext(), ModelSearchActivity.class);
                intent10.putExtra("model", et_modelsearch.getText().toString());
                startActivity(intent10);
                break;
            case R.id.main_event2:

                /*ModelNotice event = new ModelNotice();
                event.setImg_info("http://jwcnet.godohosting.com/app/jwc_app/img/notice/Security2_info.jpg");
                event.setNotice_title("시큐리티 월드");*/
                Intent intent = new Intent(getApplicationContext(), EventInfoActivity.class);
                intent.putExtra("event", imgNotice);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_viewpager1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.img_viewpager2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.img_viewpager3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.img_viewpager4:
                viewPager.setCurrentItem(3);
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
            Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
            intent.putExtra("type", "카메라");
            startActivity(intent);
        } else if (id == R.id.menu_record) {
            Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
            intent.putExtra("type", "녹화기");
            startActivity(intent);
        } else if (id == R.id.menu_login) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 999);
        } else if (id == R.id.menu_mypage) {
            Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
            intent.putExtra("page", "");
            startActivityForResult(intent, 7823);
        } else if (id == R.id.menu_notice) {
            Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_event) {
            Intent intent = new Intent(getApplicationContext(), EventActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_company) {
            Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_logout) {
            netcheck = networkcheck();
            if (netcheck == true) {
                Logout();
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                startActivityForResult(intent2, 7777);
            }
        } else if (id == R.id.menu_setup) {
            Intent intent = new Intent(getApplicationContext(), SetUp.class);
            startActivity(intent);
        } else if (id == R.id.menu_userdelete) {
            Intent intent = new Intent(getApplicationContext(), PwCheckActivity.class);
            intent.putExtra("account", "탈퇴");
            startActivityForResult(intent, 555);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // 액션바 왼쪽의 햄버거 아이콘 색상
        int color = Color.parseColor("#FFFFFFFF");
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            final View v = toolbar.getChildAt(i);

            if (v instanceof ImageButton) {
                ((ImageButton) v).setColorFilter(colorFilter);
            }
        }
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
                Toast.makeText(getApplicationContext(), "제품을 검색해 주세요.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 로그인했을때, 로그인정보가 있을때
    public void Loginsave() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.menu_login).setVisible(false);
        menu.findItem(R.id.menu_mypage).setVisible(true);
        menu.findItem(R.id.menu_logout).setVisible(true);
        menu.findItem(R.id.menu_userdelete).setVisible(true);
    }

    // 로그아웃했을때
    public void Logout() {
        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String user_num = pref.getString("id_Set", "").toString();

        ModelUser user = new ModelUser();
        user.setID(user_num);
        user.setToken("");

        // 토큰 초기화
        new MainActivity.TokenUpdate().execute(user);

        editor.remove("id_Set");
        editor.remove("level_Set");
        editor.remove("number_Set");
        editor.remove("email_Set");

        editor.apply();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.menu_login).setVisible(true);
        menu.findItem(R.id.menu_mypage).setVisible(false);
        menu.findItem(R.id.menu_logout).setVisible(false);
        menu.findItem(R.id.menu_userdelete).setVisible(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 로그인 액티비티에서오는 Result
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                if (navigationView != null) {
                    Loginsave();
                }
            }
        }
        // 개인정보 수정 액티비티에서 오는 Result
        if (requestCode == 7823) {
            if (resultCode == RESULT_OK) {
                Logout();
            }
            //리턴값이 없을때
            else {
            }
        }
        // 회원탈퇴 액티비티에서 오는 Result
        if (requestCode == 555) {
            if (resultCode == RESULT_OK) {
                Logout();
            }
            //리턴값이 없을때
            else {
            }
        }
        // 교육신청 액티비티에서 오는 Result
        if (requestCode == 489) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 999);
            }
            //리턴값이 없을때
            else {
            }
        }
        // 로그아웃 네트워크 불량에서 오는 Result
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                Logout();
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
            //리턴값이 없을때
            else {
            }
        }

        // 이미지 불러오기 네트워크 불량에서 오는 Result 2
        if (requestCode == 4523) {
            if (resultCode == RESULT_OK) {
                // 메인 이미지 가져오기
                main_event2 = (ImageView) findViewById(R.id.main_event2);
                new MainActivity.getMainImage().execute("메인");
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 로그아웃 토큰 초기화(업데이트)
    public class TokenUpdate extends AsyncTask<ModelUser, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(MainActivity.this);
            waitDlg.setMessage("로그아웃 중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelUser... params) {

            Integer count = new HttpUser().TokenUpdate(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 메인 이미지 가져오기(Notice)
    public class getMainImage extends AsyncTask<String, Integer, List<ModelNotice>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(MainActivity.this);
            waitDlg.setMessage("인터넷 연결을 확인 중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelNotice> doInBackground(String... params) {

            List<ModelNotice> notice = new HttpNotice().NoticeList(params[0]);

            return notice;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelNotice> s) {
            super.onPostExecute(s);


            try {
                if(s != null) {
                    imgNotice = s.get(0);
                    Glide.with(getApplicationContext()).load(s.get(0).getImg_title().toString()).override(720, 1000).fitCenter().into(main_event2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 앱 현재 버전, 최신버전 체크
    private class Version extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // Confirmation of market information in the Google Play Store
            try {
                Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=com.jwcnetworks.bsyoo.jwc").get();
                Elements Version = doc.select(".content");
                for (Element v : Version) {
                    if (v.attr("itemprop").equals("softwareVersion")) {
                        rtn = v.text();
                        //return v.text().trim();
                    }
                }
                return rtn;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            // Version check the execution application.
            PackageInfo pi = null;
            try {
                pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            verSion = pi.versionName;
            rtn = result;

            if (!verSion.equals(rtn)) { // 최신버전이 아닐경우
                Toast.makeText(getApplicationContext(), "최신버전으로 업데이트 해주세요.", Toast.LENGTH_SHORT).show();
                Intent intent_ = new Intent(getApplicationContext(), PopupActivity.class);
                intent_.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
                getApplicationContext().startActivity(intent_);
            }
            super.onPostExecute(result);
        }
    }

    // 메모리 부족할때 호출되는 메소드. 이후 GC가 수행됨  // 매니페스트에 android:largeHeap="true" 작성
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(getApplicationContext()).trimMemory(level);
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

    // 상태 표시 줄의 높이를 찾는 메소드(액션바 백그라운드를 상태바에 적용시킬때)
    /*public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        result = (result / 10) * 6;
        return result;
    }*/
}
