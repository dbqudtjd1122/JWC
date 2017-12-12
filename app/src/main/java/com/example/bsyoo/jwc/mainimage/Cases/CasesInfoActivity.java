package com.example.bsyoo.jwc.mainimage.Cases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.bsyoo.jwc.R;

public class CasesInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_info);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("설치 사례");

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getExtras().get("img");

        ImageView img_view = (ImageView) findViewById(R.id.img);

        img_view.setImageBitmap(bitmap);
    }
}
