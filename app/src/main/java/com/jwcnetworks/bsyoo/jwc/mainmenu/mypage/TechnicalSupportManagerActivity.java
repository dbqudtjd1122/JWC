package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

import java.util.Date;

public class TechnicalSupportManagerActivity extends LoginInformation {

    private ModelTechnicalSupport technical = new ModelTechnicalSupport();
    private ModelUser user = new ModelUser();
    private TextView tv_name,tv_question, tv_email, tv_sms, tv_title, tv_info;
    private EditText edt_managerinfo;
    private CheckBox check_email, check_sms;
    private Button btn_finish;
    private String managerinfo;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support_manager);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("문의 답변");

        Intent intent = getIntent();
        technical = (ModelTechnicalSupport) intent.getSerializableExtra("technical");
        user = (ModelUser) intent.getSerializableExtra("user");
        managerinfo = intent.getStringExtra("managerinfo");

        setText();

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                technical.setManagerID(isid);
                technical.setManagerinfo(edt_managerinfo.getText().toString());
                Date mdate = new Date();
                technical.setManagertime(mdate);
                netcheck = networkcheck();
                if (netcheck == true) {
                    new TechnicalSupportManagerActivity.updateManagerTechnical().execute(technical);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent, 7777);
                }
            }
        });
    }
    private void setText(){
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_question = (TextView) findViewById(R.id.tv_question);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_sms = (TextView) findViewById(R.id.tv_sms);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_info = (TextView) findViewById(R.id.tv_info);
        edt_managerinfo = (EditText) findViewById(R.id.edt_managerinfo);
        check_email = (CheckBox) findViewById(R.id.check_email);
        check_sms = (CheckBox) findViewById(R.id.check_sms);
        btn_finish = (Button) findViewById(R.id.btn_finish);


        tv_name.setText(user.getName().toString());
        tv_question.setText(technical.getQuestion().toString());
        tv_email.setText(user.getEmail().toString());
        tv_sms.setText(user.getPhone().toString());
        tv_title.setText(technical.getTitle().toString());
        tv_info.setText(technical.getInfo().toString());
        edt_managerinfo.setText(managerinfo);
        if(technical.getEmailcheck() == 1){
            check_email.setChecked(true);
        }
        if(technical.getSmscheck() == 1){
            check_sms.setChecked(true);
        }
    }

    // 1:1 답변 업데이트
    public class updateManagerTechnical extends AsyncTask<ModelTechnicalSupport, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(TechnicalSupportManagerActivity.this);
            waitDlg.setMessage("1:1 문의 답변 대기 중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Integer doInBackground(ModelTechnicalSupport... params) {

            Integer count = new HttpTechnicalSupport().updateManagerTechnical(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s == 1) {
                Toast.makeText(getApplicationContext(), "1:1 문의 답변이 작성 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TechnicalSupportActivity.class);
                intent.putExtra("technical", technical);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
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
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                new TechnicalSupportManagerActivity.updateManagerTechnical().execute(technical);
            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
