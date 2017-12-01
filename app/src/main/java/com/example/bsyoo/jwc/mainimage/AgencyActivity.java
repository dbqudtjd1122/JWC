package com.example.bsyoo.jwc.mainimage;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class AgencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("대리점 안내");

        ImageView img_agency1 = (ImageView) findViewById(R.id.img_agency1);
        ImageView img_agency2 = (ImageView) findViewById(R.id.img_agency2);
        Glide.with(this).load("http://jwccctv.cafe24.com/bizdemo16210/img/etc/Agency1.jpg").override(720,4000).fitCenter().into(img_agency1);
        Glide.with(this).load("http://jwccctv.cafe24.com/bizdemo16210/img/etc/Agency2.jpg").override(720,4000).fitCenter().into(img_agency2);


        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(img_agency1);
        photoview.update();
        PhotoViewAttacher photoview2 = new PhotoViewAttacher(img_agency2);
        photoview2.update();
    }
}
