package com.jwcnetworks.bsyoo.jwc.mainimage.News;

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
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterNews;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpNotice;
import com.jwcnetworks.bsyoo.jwc.model.ModelNews;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ListView listview;
    private AdapterNews adater;
    private List<ModelNews> newslist;
    private ModelNews news = new ModelNews();

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("CCTV 관련 뉴스");

        listview = (ListView) findViewById(R.id.list_news);

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        newslist = new ArrayList<>();

        adater = new AdapterNews(getApplicationContext(), R.layout.listitem_notice, R.id.textView9, newslist);

        listview.setOnItemClickListener( new NewsActivity.OnItemHandler());
        listview.setOnItemLongClickListener(new NewsActivity.OnItemHandler());
        listview.setOnItemSelectedListener(new NewsActivity.OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listview.setAdapter(adater);

        netcheck = networkcheck();
        if (netcheck == true) {
            new NewsActivity.getNewsList().execute();
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), NewsInfoActivity.class);
            news = newslist.get(position);
            intent.putExtra("news", news);
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
                new NewsActivity.getNewsList().execute();
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 데이터가져오기
    public class getNewsList extends AsyncTask<Integer, Integer, List<ModelNews>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(NewsActivity.this);
            waitDlg.setMessage("뉴스를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelNews> doInBackground(Integer... params) {

            List<ModelNews> count = new HttpNotice().getNewsList();

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelNews> s) {
            super.onPostExecute(s);

            if(s != null) {
                newslist = s;
                adater.clear();
                adater.addAll(newslist);
                adater.notifyDataSetChanged();
            }

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
