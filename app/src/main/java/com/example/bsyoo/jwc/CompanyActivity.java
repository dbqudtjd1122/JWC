package com.example.bsyoo.jwc;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;


public class CompanyActivity extends AppCompatActivity {

    private ImageView companyimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("회사소개");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        companyimg = (ImageView) findViewById(R.id.companyimg);
        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(companyimg);
        photoview.update();
    }
}
