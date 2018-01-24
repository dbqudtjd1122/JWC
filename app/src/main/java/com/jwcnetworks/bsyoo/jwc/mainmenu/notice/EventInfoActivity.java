package com.jwcnetworks.bsyoo.jwc.mainmenu.notice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;

import uk.co.senab.photoview.PhotoViewAttacher;

public class EventInfoActivity extends AppCompatActivity {

    private ModelNotice event = new ModelNotice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        event = (ModelNotice) intent.getSerializableExtra("event");
        ImageView event_info = (ImageView) findViewById(R.id.event_info);

        getSupportActionBar().setTitle(event.getNotice_title().toString());

        if (event.getImg_info() != null) {
            Glide.with(this).load(event.getImg_info()).override(720, 10000).fitCenter().into(event_info);
        }

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(event_info);
        photoview.update();

    }
}
