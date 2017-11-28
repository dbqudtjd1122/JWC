package com.example.bsyoo.jwc.user.Login;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class LoginInformation extends AppCompatActivity {

    public SharedPreferences pref = null;
    public String isid, isemail;
    public Integer isnumber, islevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. 공유 프레퍼런스 객체를 얻어온다. /data/data/패키지명/shared_prefs/Login.xml
        pref = getSharedPreferences("Login", Context.MODE_PRIVATE);

        // 2. 공유 프레퍼런스를 이용하여 로그인 on/off 설정값을 얻어온다.
        isid = pref.getString("id_Set", "").toString();
        islevel = pref.getInt("level_Set", -1);
        isnumber = pref.getInt("number_Set", -1);
        isemail = pref.getString("email_Set", "" ).toString();

    }
}
