package com.example.bsyoo.jwc.mainimage.Cases;


import android.graphics.Color;
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

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
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
