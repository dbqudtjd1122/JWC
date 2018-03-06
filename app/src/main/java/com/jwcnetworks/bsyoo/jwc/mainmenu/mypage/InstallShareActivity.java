package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

public class InstallShareActivity extends AppCompatActivity {

    private ModelUser user = new ModelUser();
    private TextView tv_name;
    private Button btn_finish;
    private CheckBox chbox_share, chbox_install;

    private EditText edt_email, edt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_share);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("견적 문의");

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        settext();


        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chbox_share.isChecked() == false){
                    Toast.makeText(getApplicationContext(), "개인정보 제공 동의를 체크해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "견적신청이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void settext(){
        tv_name = (TextView) findViewById(R.id.tv_name);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        chbox_share = (CheckBox) findViewById(R.id.chbox_share);
        chbox_install = (CheckBox) findViewById(R.id.chbox_install);

        tv_name.setText(user.getName().toString());
        edt_email.setText(user.getEmail().toString());
        edt_phone.setText(user.getPhone().toString());
    }
}
