package com.example.bsyoo.jwc.user;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class LoginInformation extends AppCompatActivity {

    protected SharedPreferences pref = null;
    protected String isid, islevel, isemail;
    protected Integer isuserno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. 공유 프레퍼런스 객체를 얻어온다. /data/data/패키지명/shared_prefs/Login.xml
        pref = getSharedPreferences("Login", Context.MODE_PRIVATE);

        // 2. 공유 프레퍼런스를 이용하여 로그인 on/off 설정값을 얻어온다.
        isid = pref.getString("id_Set", "").toString();
        islevel = pref.getString("level_Set", "").toString();
        isuserno = pref.getInt("number_Set", -1);
        isemail = pref.getString("email_Set", "" ).toString();

    }
}
