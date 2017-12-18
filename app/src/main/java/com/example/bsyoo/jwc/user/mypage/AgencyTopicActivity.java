package com.example.bsyoo.jwc.user.mypage;

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

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterAgencyTopic;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.example.bsyoo.jwc.model.ModelUser;

import java.util.ArrayList;
import java.util.List;

public class AgencyTopicActivity extends AppCompatActivity {

    private ModelUser user = new ModelUser();
    private ListView listview;
    private AdapterAgencyTopic adater;
    private List<ModelAgencyTopic> agencytopiclist;
    private ModelAgencyTopic agencytopic= new ModelAgencyTopic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_topic);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("대리점 공유");

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        listview = (ListView) findViewById(R.id.topivlistview);

        agencytopiclist = new ArrayList<>();

        adater = new AdapterAgencyTopic(this, R.layout.listitem_agency_topic, R.id.tv_id, agencytopiclist, user);

        listview.setOnItemClickListener( new OnItemHandler());
        listview.setOnItemLongClickListener(new OnItemHandler());
        listview.setOnItemSelectedListener(new OnItemHandler());

        listview.setAdapter(adater);
        new AgencyTopicActivity.getTopicList().execute();

    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            adater.clear();
            adater.addAll(agencytopiclist);
            adater.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
