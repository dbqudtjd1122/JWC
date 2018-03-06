package com.jwcnetworks.bsyoo.jwc.mainimage.banner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MobileLiveActivity extends AppCompatActivity {

    private ImageView img_install, img_mobilelive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_live);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("모바일 어플 체험");

        img_install = (ImageView) findViewById(R.id.img_install);

        img_mobilelive = (ImageView) findViewById(R.id.img_mobilelive);
        Glide.with(this).load("http://jwcnet.godohosting.com/app/jwc_app/img/main/LiveCCTV_s.jpg").override(720,4000).fitCenter().into(img_mobilelive);

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(img_mobilelive);
        photoview.setScaleType(ImageView.ScaleType.FIT_XY);

        img_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=kr.co.jwccctv.viewer"));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "구글스토어 연결이 불가능 합니다.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}
