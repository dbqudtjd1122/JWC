package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bsyoo.jwc.adapter.Adapter_Notice;
import com.example.bsyoo.jwc.model.Model_Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private ListView listview;
    private Adapter_Notice adater;
    private List<Model_Notice> noticelist;
    private Model_Notice notice = new Model_Notice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        listview = (ListView) findViewById(R.id.noticelistview);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("공지사항");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        noticelist = new ArrayList<>();

        Model_Notice notice = new Model_Notice();
        notice.setNotice("[공지] CCTV렌탈 서비스");
        notice.setDay("2017-06-29");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] 140만화소 vs 240만화소 CCTV화질비교");
        notice.setDay("2017-06-28");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] 편리한 CCTV 자가 설치방법");
        notice.setDay("2017-06-28");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] JWC 전국 설치점 안내");
        notice.setDay("2016-08-16");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] JWCMALL TV조선 '뉴스쇼 판' JWC매장 촬영 보도");
        notice.setDay("2014-09-11");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] CCTV 설치 및 운영 CCTV 6대 준수사항");
        notice.setDay("2013-12-11");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] [JWC몰] 추석연휴 배송안내");
        notice.setDay("2017-09-25");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] 다후아 전국 대리점 모집 안내 [마감]");
        notice.setDay("2017-08-25");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] JDO-4008(Pro모델) 업그레이드 이벤트");
        notice.setDay("2017-08-28");
        noticelist.add(notice);
        notice = new Model_Notice();
        notice.setNotice("[공지] JWC & Dahua 컨퍼런스 관련 기사");
        notice.setDay("2017-07-21");
        noticelist.add(notice);

        // 어댑터를 생성하고 데이터 설정
        adater = new Adapter_Notice(this, R.layout.listitem_notice, R.id.notice1, noticelist);

        listview.setOnItemClickListener( new OnItemHandler());
        listview.setOnItemLongClickListener(new OnItemHandler());
        listview.setOnItemSelectedListener(new OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listview.setAdapter(adater);

    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(NoticeActivity.this, NoticeInfoActivity.class);
            notice = noticelist.get(position);
            intent.putExtra("notice", notice);
            startActivity(intent);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String msg = "onItemLongClick : "+position + ", "+id;
            Toast.makeText(NoticeActivity.this, msg, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String msg = "onItemSelected : "+position + ", "+id;
            Toast.makeText(NoticeActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(NoticeActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
        }
    }
}
