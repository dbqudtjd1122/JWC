package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

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
import android.widget.ImageView;
import android.widget.ListView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterAgencyTopic;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpAgency;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopic;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class AgencyTopicActivity extends AppCompatActivity{

    private ModelUser user = new ModelUser();
    private ListView listview;
    private AdapterAgencyTopic adapter;
    private List<ModelAgencyTopic> agencytopiclist;
    private ModelAgencyTopic agencytopic= new ModelAgencyTopic();
    private View footerView;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_topic);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("대리점 공유");

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        listview = (ListView) findViewById(R.id.topivlistview);

        // 꼬리 아이템
        footerView = getLayoutInflater().inflate(R.layout.listitem_review_footer, null);

        agencytopiclist = new ArrayList<>();

        adapter = new AdapterAgencyTopic(getApplicationContext(), R.layout.listitem_agency_topic, R.id.tv_id, agencytopiclist, user);

        // 꼬리아이템
        listview.addFooterView(footerView, null, false);

        listview.setAdapter(adapter);
        netcheck = networkcheck();
        if(netcheck == true) {
            new AgencyTopicActivity.getTopicList().execute();
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        ImageView img_write = (ImageView) findViewById(R.id.img_write);
        img_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgencyTopicWriteActivity.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 178);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AgencyTopicReviewActivity.class);
                agencytopic = agencytopiclist.get(position);
                intent.putExtra("topic", agencytopic);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 글 새로 작성시 & 수정시
        if (requestCode == 178 ) {
            if (resultCode == RESULT_OK) {
                new AgencyTopicActivity.getTopicList().execute();
            }
            //리턴값이 없을때
            else {
            }
        }

        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777 ) {
            if (resultCode == RESULT_OK) {
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 데이터가져오기
    public class getTopicList extends AsyncTask<String, Integer, List<ModelAgencyTopic>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AgencyTopicActivity.this);
            waitDlg.setMessage("대리점 공유란을 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelAgencyTopic> doInBackground(String... params) {

            List<ModelAgencyTopic> count = new HttpAgency().getAgencyTopicList();

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelAgencyTopic> s) {
            super.onPostExecute(s);

            agencytopiclist = s;
            adapter.clear();
            adapter.addAll(agencytopiclist);
            adapter.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 네트워크 체크
    private boolean networkcheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if(wifi == true){
            return true;
        } else {
            return false;
        }
    }
}
