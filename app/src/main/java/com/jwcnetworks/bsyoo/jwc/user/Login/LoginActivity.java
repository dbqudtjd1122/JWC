package com.jwcnetworks.bsyoo.jwc.user.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpSignUp;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.IDPWSearch.IDPWSearchActivity;

import java.util.regex.Pattern;

public class LoginActivity extends LoginInformation {

    private TextView SignUp, IDPWSearch;
    private EditText et_login_id, et_login_pw;
    private ModelUser user = new ModelUser();

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        // 액션바 타이틀
        getSupportActionBar().setTitle("로그인");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setbyid();

        // TextView 밑줄 적용
        SpannableString content = new SpannableString("회원가입");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        SignUp.setText(content);
        SpannableString content2 = new SpannableString("아이디/비밀번호 찾기");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        IDPWSearch.setText(content2);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        IDPWSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, IDPWSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                user.setID(et_login_id.getText().toString());
                user.setPW(et_login_pw.getText().toString());
                user.setToken(istoken.toString());
                netcheck = networkcheck();
                if(netcheck == true) {
                    new LoginActivity.Login().execute(user);
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent2, 7777);
                }
                break;
        }
    }

    private void setbyid() {
        SignUp = (TextView) findViewById(R.id.SignUp);
        IDPWSearch = (TextView) findViewById(R.id.IDPWSearch);

        et_login_id = (EditText) findViewById(R.id.et_login_id);
        et_login_id.setFilters(new InputFilter[]{filter});
        et_login_pw = (EditText) findViewById(R.id.et_login_pw);
        et_login_pw.setFilters(new InputFilter[]{filter});
    }

    // EditText 영문&숫자만 적용
    protected InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    // 로그인
    public class Login extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(LoginActivity.this);
            waitDlg.setMessage("로그인중 입니다.");
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
                Toast.makeText(LoginActivity.this, "ID 또는 PW 가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            } else if (s.getOK() == 1) {
                //Integer number = s.getUser_Number();

                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.putString("id_Set", s.getID().toString());
                prefEditor.putInt("level_Set", s.getLevel());
                prefEditor.putInt("number_Set",  s.getUser_Number());
                prefEditor.putString("email_Set", s.getEmail().toString());
                prefEditor.apply();

                isid = pref.getString("id_Set", "").toString();
                islevel = pref.getInt("level_Set", -1);
                isnumber = pref.getInt("number_Set", -1);
                isemail = pref.getString("email_Set", "" ).toString();

                Intent intent = new Intent();

                setResult(RESULT_OK, intent);
                Toast.makeText(LoginActivity.this, "로그인을 환영합니다.", Toast.LENGTH_SHORT).show();
                finish();

            } else if (s.getOK() == 2) {
                Toast.makeText(LoginActivity.this, "로그인 승인 대기를 기다려야합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), BusinessPopupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
                getApplicationContext().startActivity(intent);
            }
        }
    }
    // 네트워크 체크
    private boolean networkcheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if(wifi == true){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777 ) {
            if (resultCode == RESULT_OK) {

            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
