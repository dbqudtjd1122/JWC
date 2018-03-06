package com.jwcnetworks.bsyoo.jwc.mainimage.News;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNews;

public class NewsInfoActivity extends AppCompatActivity {

    private TextView tv_info1, tv_info2, tv_info3, tv_source;
    private ImageView img_1, img_2, img_link;
    private ModelNews news = new ModelNews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        Intent intent = getIntent();
        news = (ModelNews) intent.getSerializableExtra("news");
        getSupportActionBar().setTitle(news.getNewstitle().toString());

        setbyid();

        tv_info1.setText(news.getInfo1().toString());

        if (news.getInfo2() != null && !news.getInfo2().toString().equals("")) {
            tv_info2.setText(news.getInfo2().toString());
        }
        if (news.getInfo3() != null && !news.getInfo3().toString().equals("")) {
            tv_info3.setText(news.getInfo3().toString());
        }
        if(news.getImage1() != null && !news.getImage1().toString().equals("")) {
            Glide.with(getApplicationContext()).load(news.getImage1()).override(720, 4000).fitCenter().into(img_1);
        }
        if(news.getImage2() != null && !news.getImage2().toString().equals("")) {
            Glide.with(getApplicationContext()).load(news.getImage2()).override(720, 4000).fitCenter().into(img_2);
        }

        tv_source.setText("출처 : "+news.getSource().toString());
        img_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink().toString()));
                startActivity(intent);
            }
        });
    }

    private void setbyid(){
        tv_info1 = (TextView) findViewById(R.id.tv_info1);
        tv_info2 = (TextView) findViewById(R.id.tv_info2);
        tv_info3 = (TextView) findViewById(R.id.tv_info3);
        tv_source = (TextView) findViewById(R.id.tv_source);

        img_1 = (ImageView) findViewById(R.id.img_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        img_link = (ImageView) findViewById(R.id.img_link);
    }
}
