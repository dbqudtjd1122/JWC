package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.bsyoo.jwc.model.Model_Camera;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SeriesInfoActivity extends AppCompatActivity {

    private Model_Camera camera = new Model_Camera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_info);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        // 뒤로가기 버튼
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        Intent intent = getIntent();
        camera = (Model_Camera) intent.getSerializableExtra("camera");
        ImageView series_info = (ImageView) findViewById(R.id.series_info);
        if(camera.getOnlinename().toString().equals("JWC-L1VD")){
            series_info.setImageResource(R.drawable.l1vd_ss_410);
        } else if(camera.getOnlinename().toString().equals("JWC-L3HAF")){
            series_info.setImageResource(R.drawable.l3haf_ss_410);
        } else if(camera.getOnlinename().toString().equals("JWC-L4LPR")){
            series_info.setImageResource(R.drawable.l4lpr_ss_410);
        }

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(series_info);
        photoview.update();
    }
}
