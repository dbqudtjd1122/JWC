package com.example.bsyoo.jwc.mainmenu.notice;

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

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterEvent;
import com.example.bsyoo.jwc.hppt.HttpNotice;
import com.example.bsyoo.jwc.model.ModelNotice;
import com.example.bsyoo.jwc.network.Network;
import com.example.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private ListView EventListView;
    private AdapterEvent adapter;
    private ModelNotice event = new ModelNotice();

    private List<ModelNotice> noticeslist = new ArrayList<ModelNotice>();

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("이벤트");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        EventListView = (ListView) findViewById(R.id.event_list);

        // 출력 데이터 생성
        noticeslist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterEvent(this, R.layout.listitem_event, R.id.event_title, noticeslist);

        // 리스트뷰에 어댑터 설정
        EventListView.setAdapter(adapter);

        netcheck = networkcheck();
        if (netcheck == true) {
            new EventActivity.getEventList().execute("이벤트");
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        EventListView.setOnItemClickListener( new EventActivity.OnItemHandler());
        EventListView.setOnItemLongClickListener(new EventActivity.OnItemHandler());
        EventListView.setOnItemSelectedListener(new EventActivity.OnItemHandler());

    }
    public class OnItemHandler implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(EventActivity.this, EventInfoActivity.class);
            event = noticeslist.get(position);
            intent.putExtra("event", event);
            startActivity(intent);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
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
                new EventActivity.getEventList().execute("이벤트");
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    public class getEventList extends AsyncTask<String, Integer, List<ModelNotice>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(EventActivity.this);
            waitDlg.setMessage("이벤트를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelNotice> doInBackground(String... params) {

            noticeslist = new HttpNotice().EventList(params[0].toString());

            return noticeslist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelNotice> list) {
            super.onPostExecute(list);

            noticeslist = list;
            adapter.clear();
            adapter.addAll(noticeslist);
            adapter.notifyDataSetChanged();

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}

