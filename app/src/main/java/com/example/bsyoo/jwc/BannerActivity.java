package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        Intent intent = getIntent();
        int i = intent.getIntExtra("0", 0);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        ImageView banner = (ImageView) findViewById(R.id.banner);
        if(i==0) {
            banner.setImageResource(R.drawable.rental410);
        } else if(i==1){
            banner.setImageResource(R.drawable.rental410);
        } else if(i==2){
            banner.setImageResource(R.drawable.rental410);
        } else {
            banner.setImageResource(R.drawable.rental410);
        }
        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(banner);
        photoview.update();
    }
}
