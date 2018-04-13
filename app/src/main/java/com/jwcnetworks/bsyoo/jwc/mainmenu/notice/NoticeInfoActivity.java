package com.jwcnetworks.bsyoo.jwc.mainmenu.notice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;

import uk.co.senab.photoview.PhotoViewAttacher;

public class NoticeInfoActivity extends AppCompatActivity {

    private ModelNotice notice = new ModelNotice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("공지사항");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        notice = (ModelNotice) intent.getSerializableExtra("notice");

        ImageView notice_info = (ImageView) findViewById(R.id.event_info);
        Glide.with(getApplicationContext()).load(notice.getImg_info()).override(720,4000).fitCenter().into(notice_info);

        TextView tv_text_info = (TextView) findViewById(R.id.tv_text_info);
        if(notice.getText_info() != null && !notice.getText_info().toString().equals("")) {
            tv_text_info.setText(notice.getText_info().toString());
        }else {
            tv_text_info.setVisibility(View.GONE);
        }


        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(notice_info);
        photoview.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
