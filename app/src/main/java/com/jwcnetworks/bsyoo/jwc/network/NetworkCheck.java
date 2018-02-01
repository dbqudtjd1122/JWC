package com.jwcnetworks.bsyoo.jwc.network;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;

public class NetworkCheck extends AppCompatActivity {

    Button btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_check);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        setTitle("네트워크 연결 확인");

        btn_refresh = (Button) findViewById(R.id.btn_refresh);

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                Boolean wifi = new Network().isNetWork(networkInfo);
                if(wifi == true) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "네트워크 연결이 확인되지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
