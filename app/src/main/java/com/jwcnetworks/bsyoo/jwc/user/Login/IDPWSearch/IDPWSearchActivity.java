package com.jwcnetworks.bsyoo.jwc.user.Login.IDPWSearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.Gmail.GMail;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpUser;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Pattern;

public class IDPWSearchActivity extends AppCompatActivity {

    private EditText et_id_searchname, et_id_searchemail, et_pw_searchid;
    private ModelUser iduser = new ModelUser();
    private ModelUser pwuser = new ModelUser();
    private LinearLayout ll_idsearch, ll_pwsearch;
    private TextView tv_idsearch1, tv_idsearch2, tv_pwsearch;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idpwsearch);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        setTitle("아이디 & 비밀번호 찾기");

        byid();
    }

    public void idpwonclick(View view) {
        switch (view.getId()){
            case R.id.btn_id_search1:
                iduser = new ModelUser();
                iduser.setName(et_id_searchname.getText().toString());
                iduser.setEmail(et_id_searchemail.getText().toString());
                netcheck = networkcheck();
                if(netcheck == true) {
                    new IDPWSearchActivity.IDSearch().execute(iduser);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent, 7777);
                }
                break;
            case R.id.btn_idview:
                netcheck = networkcheck();
                if(netcheck == true) {
                    new IDGMailSender().execute();
                } else {
                    Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent, 7777);
                }
                break;
            case R.id.btn_pw_search1:
                pwuser = new ModelUser();
                pwuser.setID(et_pw_searchid.getText().toString());
                netcheck = networkcheck();
                if(netcheck == true) {
                    new IDPWSearchActivity.PWSearch().execute(pwuser);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent, 7777);
                }
                break;
            case R.id.btn_pwview:
                // 임시비밀번호 만들기
                StringBuffer temp = new StringBuffer();
                Random rnd = new Random();
                for (int i = 0; i < 10; i++) {
                    int rIndex = rnd.nextInt(3);
                    switch (rIndex) {
                        case 0:
                            // a-z
                            temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                            break;
                        case 1:
                            // A-Z
                            temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                            break;
                        case 2:
                            // 0-9
                            temp.append((rnd.nextInt(10)));
                            break;
                    }
                }
                netcheck = networkcheck();
                if(netcheck == true) {
                    Toast.makeText(getApplicationContext(), tv_pwsearch.getText().toString() +"로 임시비밀번호를 발송하였습니다.", Toast.LENGTH_SHORT).show();
                    new IDPWSearchActivity.PWGMailSender().execute(temp.toString()); // 임시비밀번호 메일 발송
                    pwuser.setPW(temp.toString());  // 임시비밀번호 넣기
                    new IDPWSearchActivity.PWChange().execute(pwuser); // 임시비밀번호로 데이터 변경
                } else {
                    Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent, 7777);
                }
                break;
            case R.id.but_login:
                finish();
                break;
        }
    }

    // 아이디찾기 - 첫번째
    private class IDSearch extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(IDPWSearchActivity.this);
            waitDlg.setMessage("아이디 확인중 입니다.");
            waitDlg.show();
        }
        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpUser().IDSearch1(iduser);

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
            if(s == null){
                Toast.makeText(getApplicationContext(), "입력한 정보의 ID가 없습니다.", Toast.LENGTH_SHORT).show();
                ll_idsearch.setVisibility(View.GONE);
            } else {
                iduser = s;
                idsearch();
            }
        }
    }

    // 아이디찾기 - 두번째 메일(아이디 보내주기)
    private class IDGMailSender extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Integer... params) {

            GMail sender = new GMail("jwccctv@gmail.com", "orohzqtygypwpkat"); // SUBSTITUTE HERE
            try {
                sender.sendMail(
                        "[JWC] 아이디 찾기 안내 메일입니다.",   // 메일제목
                       "JWC APP 아이디를 알려드립니다. \nID : "+ iduser.getID().toString(),           // 메일 본문
                        "jwccctv@gmail.com",                   // 보내는 Email
                        iduser.getEmail().toString()            // 받는 Email
                );
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), iduser.getEmail().toString() +"로 아이디를 발송하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 비밀번호찾기 - 첫번째
    private class PWSearch extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(IDPWSearchActivity.this);
            waitDlg.setMessage("비밀번호 확인중 입니다.");
            waitDlg.show();
        }
        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpUser().PWSearch1(params[0]);

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
            if(s == null){
                Toast.makeText(getApplicationContext(), "조회되지않는 ID입니다.", Toast.LENGTH_SHORT).show();
                ll_pwsearch.setVisibility(View.GONE);
            } else {
                pwuser = s;
                pwsearch();
            }
        }
    }

    // 비밀번호찾기 - 두번째 메일(임시비밀번호 보내주기)
    private class PWGMailSender extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(String... params) {

            GMail sender = new GMail("jwccctv@gmail.com", "orohzqtygypwpkat"); // SUBSTITUTE HERE
            try {
                sender.sendMail(
                        "[JWC] 비밀번호 찾기 안내 메일입니다.",   // 메일제목
                        "JWC APP 임시 비밀번호를 알려드립니다. \nPW : "+ params[0].toString()+"\n로그인 후 비밀번호를 변경해주세요.",           // 메일 본문
                        "jwccctv@gmail.com",                   // 보내는 Email
                        pwuser.getEmail().toString()            // 받는 Email
                );
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
        }
    }

    // 비밀번호찾기 - 임시비밀번호로 데이터 변경
    private class PWChange extends AsyncTask<ModelUser, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(ModelUser... params) {

            Integer count = new HttpUser().PWSearch2(params[0]);

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

        }
    }

    private void idsearch(){
        ll_idsearch.setVisibility(View.VISIBLE);

        String str = iduser.getID().toString();

        StringBuffer sb = new StringBuffer(str);
        String idlength = sb.replace(str.length()-4, str.length(), "****").toString();

        tv_idsearch1.setText(idlength);

        SimpleDateFormat data= new SimpleDateFormat("yyyy.MM.dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(iduser.getUserTime().getTime());  // 리뷰 수정 날짜, 시간
        tv_idsearch2.setText(" (가입일 : "+datetime+")");

        et_id_searchemail.setText("");
        et_id_searchname.setText("");
    }

    private void pwsearch(){
        ll_pwsearch.setVisibility(View.VISIBLE);

        String str = pwuser.getEmail().toString();

        int idx = str.indexOf("@");
        int idxx = str.indexOf(".");
        String email1 = str.substring(0, idx);
        String email2 = str.substring(idx, idxx);
        String email3 = str.substring(idxx);

        StringBuffer sb = new StringBuffer(email1);
        String emailtext = sb.replace(email1.length() -4, email1.length(), "****").toString();
        StringBuffer sbb = new StringBuffer(email2);
        String emailtext2 = sbb.replace(email2.length()-3, email2.length(), "***").toString();

        tv_pwsearch.setText(emailtext+emailtext2+email3);

        et_pw_searchid.setText("");
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

    private void byid(){
        et_id_searchname = (EditText) findViewById(R.id.et_id_searchname);
        et_id_searchemail = (EditText) findViewById(R.id.et_id_searchemail);
        et_id_searchemail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS); // 이메일타입
        et_pw_searchid = (EditText) findViewById(R.id.et_pw_searchid);
        et_pw_searchid.setFilters(new InputFilter[]{filter});

        ll_idsearch = (LinearLayout) findViewById(R.id.ll_idsearch);
        ll_pwsearch = (LinearLayout) findViewById(R.id.ll_pwsearch);

        tv_idsearch1 = (TextView) findViewById(R.id.tv_idsearch1);
        tv_idsearch2 = (TextView) findViewById(R.id.tv_idsearch2);
        tv_pwsearch = (TextView) findViewById(R.id.tv_pwsearch);
    }
}
