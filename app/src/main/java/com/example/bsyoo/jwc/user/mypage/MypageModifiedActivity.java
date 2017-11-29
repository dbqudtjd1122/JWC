package com.example.bsyoo.jwc.user.mypage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_User;

public class MypageModifiedActivity extends AppCompatActivity {

    private Model_User user = new Model_User();
    private EditText et_pw, et_pw2, et_name, et_email, et_email2;
    private TextView tv_id, tv_addr1, tv_addr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_modified);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }

        Intent intent = getIntent();
        user = (Model_User) intent.getSerializableExtra("user");

        byid();
        settext();


    }
    private void byid(){
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_pw2 = (EditText) findViewById(R.id.et_pw2);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_email2 = (EditText) findViewById(R.id.et_email2);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_addr1 = (TextView) findViewById(R.id.tv_addr1);
        tv_addr2 = (TextView) findViewById(R.id.tv_addr2);
    }
    private void settext(){

        tv_id.setText(user.getID().toString());
        et_pw.setText(user.getPW().toString());
        et_pw2.setText(user.getPW().toString());
        et_name.setText(user.getName().toString());

        String str = user.getEmail().toString();
        int idx = str.indexOf("@");
        et_email.setText(str.substring(0, idx));
        et_email2.setText(str.substring(idx+1, str.length()));
    }
}
