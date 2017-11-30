package com.example.bsyoo.jwc.school;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpUser;
import com.example.bsyoo.jwc.model.ModelSchool;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.LoginInformation;
import com.example.bsyoo.jwc.user.mypage.MypageModifiedActivity;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SchoolInfoActivity extends LoginInformation {

    private ModelSchool school = new ModelSchool();
    private ImageView school_info;
    private LinearLayout school_write;
    private ModelUser user = new ModelUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }

        Intent intent = getIntent();
        school = (ModelSchool) intent.getSerializableExtra("school");

        setTitle(school.getSchool_Title().toString());

        // 종료된 교육인경우
        school_write = (LinearLayout) findViewById(R.id.school_write);
        if(school.getStart_End().toString().equals("종료")){
            school_write.setVisibility(View.GONE);
        }

        school_info = (ImageView) findViewById(R.id.school_info);
        Glide.with(this).load(school.getImg_info()).override(720, 4000).fitCenter().into(school_info);

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(school_info);
        photoview.update();

        // 회원정보 가져오기
        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        user.setUser_Number(pref.getInt("number_Set", -1));
        if(user.getUser_Number() == -1 ) {
        } else {
            new SchoolInfoActivity.getLoginInfomation().execute(user);
        }

        school_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getUser_Number() == -1) {
                    Toast.makeText(SchoolInfoActivity.this, "로그인 해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SchoolInfoActivity.this, SchoolWriteActivity.class);
                    intent.putExtra("school", school);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 789);
                }
            }
        });
    }

    // 회원정보 가져오기
    public class getLoginInfomation extends AsyncTask<ModelUser, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SchoolInfoActivity.this);
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
    }
}
