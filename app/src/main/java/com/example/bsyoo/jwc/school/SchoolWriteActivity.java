package com.example.bsyoo.jwc.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpSchool;
import com.example.bsyoo.jwc.model.ModelSchool;
import com.example.bsyoo.jwc.model.ModelUserSchool;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.text.SimpleDateFormat;

public class SchoolWriteActivity extends LoginInformation {

    private ModelSchool school = new ModelSchool();
    private ModelUser user = new ModelUser();
    private ModelUserSchool Suser = new ModelUserSchool();
    private TextView tv_writetitle, tv_applytime, tv_lecture, tv_writename;
    private Button btn_finish;
    private EditText et_personnel, et_motive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_write);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
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
                new SchoolWriteActivity.insertSchoolUser().execute(Suser);
            }
        });
    }

    private void byid(){
        tv_writetitle = (TextView) findViewById(R.id.tv_writetitle);
        tv_applytime = (TextView) findViewById(R.id.tv_applytime);
        tv_lecture = (TextView) findViewById(R.id.tv_lecture);
        tv_writename = (TextView) findViewById(R.id.tv_writename);

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
                Intent intent = new Intent(SchoolWriteActivity.this, SchoolInfoActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}
