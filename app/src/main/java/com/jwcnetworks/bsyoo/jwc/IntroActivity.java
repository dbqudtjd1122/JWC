package com.jwcnetworks.bsyoo.jwc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IntroActivity extends AppCompatActivity {

    public SharedPreferences shortcutSharedPref;
    public boolean isInstalled;

    // 버전체크
    public String rtn, verSion;
    public AlertDialog.Builder alt_bld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //  32번줄까지 앱실행시 아이콘 바로가기생성
        shortcutSharedPref = getSharedPreferences("what", MODE_PRIVATE);
        isInstalled = shortcutSharedPref.getBoolean("isInstalled", false);

        if (!isInstalled) {
            addAppIconToHomeScreen(getApplicationContext());
        }

        FirebaseMessaging.getInstance().subscribeToTopic("notice"); // 푸시 주제
        FirebaseInstanceId.getInstance().getToken();

        // 최신버전 체크후 업데이트 권장
        /*alt_bld = new AlertDialog.Builder(this);
        new Version().execute();*/

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    // 어플 바로가기 아이콘 생성
    private void addAppIconToHomeScreen(Context context) {
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClassName(context, getClass().getName());
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher));

        intent.putExtra("duplicate", false);
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

        sendBroadcast(intent);

        SharedPreferences.Editor editor = shortcutSharedPref.edit();
        editor.putBoolean("isInstalled", true);
        editor.commit();

    }

    /*// 앱 현재 버전, 최신버전 체크
    private class Version extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // Confirmation of market information in the Google Play Store
            try {
                Document  doc = Jsoup.connect("https://play.google.com/store/apps/details?id=com.jwcnetworks.bsyoo.jwc").get();
                Elements Version = doc.select(".content");
                for (Element v : Version) {
                    if (v.attr("itemprop").equals("softwareVersion")) {
                        rtn = v.text();
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
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            verSion = pi.versionName;
            rtn = result;

            if (!verSion.equals(rtn)) { // 최신버전이 아닐경우
                Toast.makeText(getApplicationContext(), "최신버전으로 업데이트 해주세요.", Toast.LENGTH_SHORT).show();
                //데이터 담아서 팝업(액티비티) 호출
                Intent intent_ = new Intent(getApplicationContext(), PopupActivity.class);
                intent_.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
                getApplicationContext().startActivity(intent_);
            }
            super.onPostExecute(result);
        }
    }*/
}
