package com.example.bsyoo.jwc.school;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelSchool;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.text.SimpleDateFormat;

public class SchoolWriteActivity extends LoginInformation {

    private ModelSchool school = new ModelSchool();
    private TextView tv_writetitle, tv_applytime, tv_lecture;

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

        byid();

        settext();
    }

    private void byid(){
        tv_writetitle = (TextView) findViewById(R.id.tv_writetitle);
        tv_applytime = (TextView) findViewById(R.id.tv_applytime);
        tv_lecture = (TextView) findViewById(R.id.tv_lecture);
    }

    private void settext(){
        tv_writetitle.setText(school.getSchool_Title().toString()+" 지원서");
        tv_applytime.setText("교육신청 기간 : "+school.getApply_Time().toString());

        SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(school.getLecture_Time().getTime());  // 날짜, 시간
        tv_lecture.setText("교육시행 날짜 : "+datetime);
    }
}
