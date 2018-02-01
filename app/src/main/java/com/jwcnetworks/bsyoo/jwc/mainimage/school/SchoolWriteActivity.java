package com.jwcnetworks.bsyoo.jwc.mainimage.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpSchool;
import com.jwcnetworks.bsyoo.jwc.model.ModelSchool;
import com.jwcnetworks.bsyoo.jwc.model.ModelUserSchool;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

import java.text.SimpleDateFormat;

public class SchoolWriteActivity extends LoginInformation {

    private ModelSchool school = new ModelSchool();
    private ModelUser user = new ModelUser();
    private ModelUserSchool Suser = new ModelUserSchool();
    private TextView tv_writetitle, tv_applytime, tv_lecture, tv_writename, tv_teamname, tv_teaminfo;
    private Button btn_finish;
    private EditText et_personnel, et_motive;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_write);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("교육 신청서 작성");

        Intent intent = getIntent();
        school = (ModelSchool) intent.getSerializableExtra("school");
        user = (ModelUser) intent.getSerializableExtra("user");
        Suser.setUser_Number(user.getUser_Number());
        Suser.setSchool_Number(school.getSchool_Number());

        byid();
        settext();

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suser.setUser_Personnel(Integer.valueOf(et_personnel.getText().toString()));
                Suser.setUser_Motive(et_motive.getText().toString());
                netcheck = networkcheck();
                if(netcheck == true) {
                    new SchoolWriteActivity.insertSchoolUser().execute(Suser);
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent2, 7777);
                }
            }
        });
    }

    private void byid(){
        tv_writetitle = (TextView) findViewById(R.id.tv_writetitle);
        tv_applytime = (TextView) findViewById(R.id.tv_applytime);
        tv_lecture = (TextView) findViewById(R.id.tv_lecture);
        tv_writename = (TextView) findViewById(R.id.tv_writename);
        tv_teamname = (TextView) findViewById(R.id.tv_teamname);
        tv_teaminfo = (TextView) findViewById(R.id.tv_teaminfo);

        btn_finish = (Button) findViewById(R.id.btn_finish);

        et_personnel = (EditText) findViewById(R.id.et_personnel);
        et_motive = (EditText) findViewById(R.id.et_motive);
    }

    private void settext(){
        tv_writetitle.setText(school.getSchool_Title().toString());
        tv_applytime.setText(school.getApply_Time().toString());

        SimpleDateFormat data= new SimpleDateFormat("일정 : yyyy-MM-dd\n시간 : HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(school.getLecture_Time().getTime());  // 날짜, 시간
        tv_lecture.setText(datetime);

        tv_teamname.setText(school.getTeam().toString());
        tv_teaminfo.setText("담당자 "+school.getManager().toString()+" 연락처 : "+school.getManagerPhone().toString()+"\n(전화문의 마감시간 9:30~18:00)" );
        tv_writename.setText(user.getName().toString());
    }

    // 교육신청 마무리
    public class insertSchoolUser extends AsyncTask<ModelUserSchool, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SchoolWriteActivity.this);
            waitDlg.setMessage("교육신청중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Integer doInBackground(ModelUserSchool... params) {

            Integer count = new HttpSchool().insertSchoolUser(params[0]);

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
            if(s==1){
                Intent intent = new Intent(getApplicationContext(), SchoolInfoActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), SchoolInfoActivity.class);
        intent.putExtra("school", school);
        setResult(RESULT_OK, intent);
        finish();
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
}
