package com.example.bsyoo.jwc.mainimage.notice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelNotice;

import uk.co.senab.photoview.PhotoViewAttacher;

public class NoticeInfoActivity extends AppCompatActivity {

    private ModelNotice notice = new ModelNotice();

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
        notice = (ModelNotice) intent.getSerializableExtra("notice");

        ImageView notice_info = (ImageView) findViewById(R.id.event_info);
        Glide.with(this).load(notice.getImg_info()).override(720,4000).fitCenter().into(notice_info);


        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(notice_info);
        photoview.update();
    }
}
