package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.bsyoo.jwc.model.Model_Notice;

import uk.co.senab.photoview.PhotoViewAttacher;

public class NoticeInfoActivity extends AppCompatActivity {

    private Model_Notice notice = new Model_Notice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("공지사항");
        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        notice = (Model_Notice) intent.getSerializableExtra("notice");

        ImageView notice_info = (ImageView) findViewById(R.id.notice_info);
        if (notice.getNotice().equals("[공지] CCTV렌탈 서비스")){
            notice_info.setImageResource(R.drawable.rental410);
        } else if (notice.getNotice().equals("[공지] 편리한 CCTV 자가 설치방법")){
            notice_info.setImageResource(R.drawable.mycctv);
        }

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(notice_info);
        photoview.update();



    }
}
