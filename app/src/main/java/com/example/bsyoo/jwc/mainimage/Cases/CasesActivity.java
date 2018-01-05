package com.example.bsyoo.jwc.mainimage.Cases;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bsyoo.jwc.R;

public class CasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("설치 사례");
    }

    public void casesClick(View view){
        switch (view.getId()){
            case R.id.img_cases1_1:
                // ImageView img_cases1_1 = (ImageView) findViewById(R.id.img_cases1_1);
                /*Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.cases1_1);
                Intent intent1 = new Intent(this, CasesInfoActivity.class);
                intent1.putExtra("img", bitmap1);
                startActivity(intent1);*/
                break;
        }
    }
}
