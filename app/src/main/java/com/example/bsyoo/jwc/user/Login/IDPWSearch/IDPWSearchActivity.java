package com.example.bsyoo.jwc.user.Login.IDPWSearch;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.Http_User;
import com.example.bsyoo.jwc.model.Model_User;

import java.text.SimpleDateFormat;

public class IDPWSearchActivity extends AppCompatActivity {

    private EditText et_id_searchname, et_id_searchemail, et_pw_searchid;
    private Model_User user = new Model_User();
    private LinearLayout ll_idsearch;
    private TextView tv_idsearch1, tv_idsearch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idpwsearch);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("아이디 & 비밀번호 찾기");

        byid();

    }

    public void idpwonclick(View view) {
        switch (view.getId()){
            case R.id.btn_id_search1:
                user.setName(et_id_searchname.getText().toString());
                user.setEmail(et_id_searchemail.getText().toString());
                new IDPWSearchActivity.IDSearch().execute(user);
                break;
            case R.id.btn_pw_search1:
                break;
        }
    }

    public class IDSearch extends AsyncTask<Model_User, Integer, Model_User> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(IDPWSearchActivity.this);
            waitDlg.setMessage("로그인중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Model_User doInBackground(Model_User... params) {

            Model_User count = new Http_User().IDSearch1(user);

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Model_User s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            user = s;
            if(s == null){
                Toast.makeText(IDPWSearchActivity.this, "입력한 정보의 ID가 없습니다.", Toast.LENGTH_SHORT).show();
            } else {
                idsearch();
            }
        }
    }

    public void idsearch(){
        ll_idsearch.setVisibility(View.VISIBLE);

        String str = user.getID().toString();


        StringBuffer sb = new StringBuffer(str);
        String idlength = sb.replace(str.length()-4, str.length(), "****").toString();

        tv_idsearch1.setText(idlength);

        SimpleDateFormat data= new SimpleDateFormat("yyyy.MM.dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(user.getUserTime().getTime());  // 리뷰 수정 날짜, 시간
        tv_idsearch2.setText(" (가입일 : "+datetime+")");

        et_id_searchemail.setText("");
        et_id_searchname.setText("");
    }

    public void byid(){
        et_id_searchname = (EditText) findViewById(R.id.et_id_searchname);
        et_id_searchemail = (EditText) findViewById(R.id.et_id_searchemail);
        et_pw_searchid = (EditText) findViewById(R.id.et_pw_searchid);

        ll_idsearch = (LinearLayout) findViewById(R.id.ll_idsearch);

        tv_idsearch1 = (TextView) findViewById(R.id.tv_idsearch1);
        tv_idsearch2 = (TextView) findViewById(R.id.tv_idsearch2);
    }
}
