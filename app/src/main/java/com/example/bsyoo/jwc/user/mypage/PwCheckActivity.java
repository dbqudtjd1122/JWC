package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpSignUp;
import com.example.bsyoo.jwc.hppt.HttpUser;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

public class PwCheckActivity extends LoginInformation {

    private EditText et_id,et_pw;
    private Button btn_pwcheck;
    private ModelUser user = new ModelUser();
    private String account = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_check);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("비밀번호 확인");

        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        user = (ModelUser) intent.getSerializableExtra("user");

        byid();
        settext();

        btn_pwcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPW(et_pw.getText().toString());
                new PwCheckActivity.PwCheck().execute(user);
            }
        });
    }

    private void byid(){
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        btn_pwcheck = (Button) findViewById(R.id.btn_pwcheck);
    }

    private void settext(){
        et_id.setText(user.getID().toString());
    }

    // 비밀번호 확인
    public class PwCheck extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(PwCheckActivity.this);
            waitDlg.setMessage("비밀번호 확인중 입니다.");
            waitDlg.show();
        }
        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpSignUp().Login(user);

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(ModelUser s) {
            super.onPostExecute(s);
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s == null) {
                Toast.makeText(PwCheckActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PwCheckActivity.this, "비밀번호가 확인 되었습니다.", Toast.LENGTH_SHORT).show();
                if(account.equals("수정")) {
                    Intent intent = new Intent(PwCheckActivity.this, MypageModifiedActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 777);
                } else {
                    Intent intent = new Intent(PwCheckActivity.this, DeleteUserActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 888);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 777 ) {
            if (resultCode == RESULT_OK) {
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
        if (requestCode == 888) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(PwCheckActivity.this, MypageActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
