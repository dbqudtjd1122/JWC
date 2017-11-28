package com.example.bsyoo.jwc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bsyoo.jwc.adapter.Adapter_Notice;
import com.example.bsyoo.jwc.hppt.Http_Notice;
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
        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        noticelist = new ArrayList<>();

        // 어댑터를 생성하고 데이터 설정
        adater = new Adapter_Notice(this, R.layout.listitem_notice, R.id.notice1, noticelist);

        listview.setOnItemClickListener( new OnItemHandler());
        listview.setOnItemLongClickListener(new OnItemHandler());
        listview.setOnItemSelectedListener(new OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listview.setAdapter(adater);
        new NoticeActivity.getNoticeList().execute("공지");

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

    // 데이터가져오기
    public class getNoticeList extends AsyncTask<String, Integer, List<Model_Notice>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(NoticeActivity.this);
            waitDlg.setMessage("공지사항을 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<Model_Notice> doInBackground(String... params) {

            List<Model_Notice> count = new Http_Notice().NoticeList(params[0].toString());

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Model_Notice> s) {
            super.onPostExecute(s);

            noticelist = s;
            adater.clear();
            adater.addAll(noticelist);
            adater.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
