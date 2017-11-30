package com.example.bsyoo.jwc.school;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelSchool;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SchoolInfoActivity extends AppCompatActivity {

    private ModelSchool school = new ModelSchool();
    private ImageView school_info;
    private LinearLayout school_write;


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


        school_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SchoolInfoActivity.this, SchoolWriteActivity.class);
                intent.putExtra("school", school);
                startActivityForResult(intent, 789);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
