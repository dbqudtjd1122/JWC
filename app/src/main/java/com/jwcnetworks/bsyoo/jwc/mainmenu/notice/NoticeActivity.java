package com.jwcnetworks.bsyoo.jwc.mainmenu.notice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterNotice;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpNotice;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private ListView listview;
    private AdapterNotice adater;
    private List<ModelNotice> noticelist;
    private ModelNotice notice = new ModelNotice();

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("공지사항");

        listview = (ListView) findViewById(R.id.noticelistview);

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        noticelist = new ArrayList<>();

        // 어댑터를 생성하고 데이터 설정
        adater = new AdapterNotice(getApplicationContext(), R.layout.listitem_notice, R.id.notice1, noticelist);

        listview.setOnItemClickListener( new OnItemHandler());
        listview.setOnItemLongClickListener(new OnItemHandler());
        listview.setOnItemSelectedListener(new OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listview.setAdapter(adater);

        netcheck = networkcheck();
        if (netcheck == true) {
            new NoticeActivity.getNoticeList().execute("공지");
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), NoticeInfoActivity.class);
            notice = noticelist.get(position);
            intent.putExtra("notice", notice);
            startActivity(intent);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // String msg = "onItemLongClick : "+position + ", "+id;
            // Toast.makeText(NoticeActivity.this, msg, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // String msg = "onItemSelected : "+position + ", "+id;
            // Toast.makeText(NoticeActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // makeText(NoticeActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
        }
    }

    // 네트워크 체크
    private boolean networkcheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if (wifi == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                new NoticeActivity.getNoticeList().execute("공지");
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 데이터가져오기
    public class getNoticeList extends AsyncTask<String, Integer, List<ModelNotice>> {

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
        protected List<ModelNotice> doInBackground(String... params) {

            List<ModelNotice> count = new HttpNotice().NoticeList(params[0].toString());

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelNotice> s) {
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
