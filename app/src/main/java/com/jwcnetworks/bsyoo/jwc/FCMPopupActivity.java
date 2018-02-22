package com.jwcnetworks.bsyoo.jwc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FCMPopupActivity extends Activity {

    private Button btn_finish;
    private TextView tv_title, tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_fcmpopup);

        btn_finish = (Button) findViewById(R.id.btn_finish);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_info = (TextView) findViewById(R.id.tv_info);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        tv_title.setText(title);
        tv_info.setText(message);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
