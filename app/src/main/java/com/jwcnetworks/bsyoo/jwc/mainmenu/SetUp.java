package com.jwcnetworks.bsyoo.jwc.mainmenu;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

public class SetUp extends LoginInformation {

    private Switch sw_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("환경설정");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sw_push = (Switch) findViewById(R.id.sw_push);
        if(ispush == 1){
            sw_push.setChecked(true);
        }else {
            sw_push.setChecked(false);
        }


        sw_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = pref.edit();
                if(sw_push.isChecked()==true){
                    editor.putInt("push_Set", 1);
                }else {
                    editor.putInt("push_Set", 2);
                }
                editor.apply();
            }
        });
    }
}
