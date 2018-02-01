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


        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(notice_info);
        photoview.update();
    }

    // 메모리 부족할때 호출되는 메소드. 이후 GC가 수행됨  // 매니페스트에 android:largeHeap="true" 작성
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(getApplicationContext()).trimMemory(level);
    }

}
