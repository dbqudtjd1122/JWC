package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        Intent intent = getIntent();
        int i = intent.getIntExtra("0", 0);

        ImageView banner = (ImageView) findViewById(R.id.banner);
        if(i==0) {
            banner.setImageResource(R.drawable.jdo_4008);
        } else if(i==1){
            banner.setImageResource(R.drawable.jdo_4008);
        } else if(i==2){
            banner.setImageResource(R.drawable.rental410);
        }
    }
}
