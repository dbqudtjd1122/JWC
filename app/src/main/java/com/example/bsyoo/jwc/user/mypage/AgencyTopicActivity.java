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
import android.widget.Button;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterAgencyTopic;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.example.bsyoo.jwc.model.ModelUser;

import java.util.ArrayList;
import java.util.List;

public class AgencyTopicActivity extends AppCompatActivity{

    private ModelUser user = new ModelUser();
    private ListView listview;
    private AdapterAgencyTopic adapter;
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

        adapter = new AdapterAgencyTopic(this, R.layout.listitem_agency_topic, R.id.tv_id, agencytopiclist, user);

        listview.setAdapter(adapter);

        new AgencyTopicActivity.getTopicList().execute();

        Button btn_write = (Button) findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgencyTopicActivity.this, AgencyTopicWriteActivity.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 178);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AgencyTopicActivity.this, AgencyTopicReviewActivity.class);
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
}
