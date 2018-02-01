package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.MainActivity;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpSignUp;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpUser;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

public class PwCheckActivity extends LoginInformation {

    private EditText et_id,et_pw;
    private Button btn_pwcheck;
    private ModelUser user = new ModelUser();
    private String account = "";

    private Boolean netcheck = true;  // 네트워크 연결확인

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

        byid();

        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        user.setUser_Number(pref.getInt("number_Set", -1));

        netcheck = networkcheck();
        if (netcheck == true) {
            new PwCheckActivity.getLoginInfomation().execute(user);
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        btn_pwcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPW(et_pw.getText().toString());
                netcheck = networkcheck();
                if (netcheck == true) {
                    new PwCheckActivity.PwCheck().execute(user);
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivity(intent2);
                }
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
                Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "비밀번호가 확인 되었습니다.", Toast.LENGTH_SHORT).show();
                if(account.equals("수정")) {
                    Intent intent = new Intent(getApplicationContext(), MypageModifiedActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 777);
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeleteUserActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 888);
                }
            }
        }
    }

    // 회원정보 가져오기
    public class getLoginInfomation extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(PwCheckActivity.this);
            waitDlg.setMessage("회원정보를 가져오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpUser().getLoginInfomation(user);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser s) {
            super.onPostExecute(s);

            user = s;
            settext();
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 시리얼 등록
        if (requestCode == 777 ) {
            if (resultCode == RESULT_OK) {
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
        // 회원탈퇴
        if (requestCode == 888) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                new PwCheckActivity.getLoginInfomation().execute(user);
            }
            //리턴값이 없을때
            else {
            }
        }
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

}
