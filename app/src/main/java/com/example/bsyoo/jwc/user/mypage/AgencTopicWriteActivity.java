package com.example.bsyoo.jwc.user.mypage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;

public class AgencTopicWriteActivity extends AppCompatActivity {

    private ModelAgencyTopic topic = new ModelAgencyTopic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenc_topic_write);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("댓글 작성");

        Intent intent = getIntent();
        topic = (ModelAgencyTopic) intent.getSerializableExtra("topic");
    }
}