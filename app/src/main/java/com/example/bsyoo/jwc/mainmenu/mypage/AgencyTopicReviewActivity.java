package com.example.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterAgencyTopicReview;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.example.bsyoo.jwc.model.ModelAgencyTopicReview;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.network.Network;
import com.example.bsyoo.jwc.network.NetworkCheck;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class AgencyTopicReviewActivity extends AppCompatActivity {

    private ModelAgencyTopic topic = new ModelAgencyTopic();

    private ModelUser user = new ModelUser();
    private TextView tv_id, tv_name, tv_time, tv_topicinfo;
    private ImageView img_topic1, img_topic2, img_topic3;
    private ListView listView;
    private EditText et_insert_review;
    private Button btn_insert_review;
    private View headerView, footerView;

    private ModelAgencyTopicReview review = new ModelAgencyTopicReview();
    private List<ModelAgencyTopicReview> reviewlist;
    private AdapterAgencyTopicReview adapter;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_topic_review);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("댓글");

        Intent intent = getIntent();
        topic = (ModelAgencyTopic) intent.getSerializableExtra("topic");
        user = (ModelUser) intent.getSerializableExtra("user");

        // 머리 아이템
        headerView = getLayoutInflater().inflate(R.layout.listitem_review_header, null);
        // 꼬리 아이템
        footerView = getLayoutInflater().inflate(R.layout.listitem_review_footer, null);

        byid();

        tv_id.setText("작성자 : " + topic.getID().toString());
        tv_name.setText("제목 : " + topic.getTopic_Name().toString());

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(topic.getTopic_Time().getTime());
        tv_time.setText(datetime);

        tv_topicinfo.setText(topic.getInformation().toString());

        if (topic.getImg1() != null) {
            img_topic1.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(topic.getImg1().toString()).override(250, 250).fitCenter().into(img_topic1);
        }
        if (topic.getImg2() != null) {
            img_topic2.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(topic.getImg2().toString()).override(250, 250).fitCenter().into(img_topic2);
        }
        if (topic.getImg3() != null) {
            img_topic3.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(topic.getImg3().toString()).override(250, 250).fitCenter().into(img_topic3);
        }

        reviewlist = new ArrayList<>();

        adapter = new AdapterAgencyTopicReview(this, R.layout.listitem_agency_topic_review, R.id.tv_id, reviewlist, user, topic);

        // 머리아이템
        listView.addHeaderView(headerView, null, true);
        // 꼬리아이템
        listView.addFooterView(footerView, null, false);

        listView.setAdapter(adapter);

        netcheck = networkcheck();
        if (netcheck == true) {
            new AgencyTopicReviewActivity.getReviewList().execute(topic);
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        btn_insert_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review.setID(user.getID().toString());
                review.setInformation(et_insert_review.getText().toString());
                review.setTopic_Number(topic.getTopic_Number());

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                review.setReview_Time(date);

                netcheck = networkcheck();
                if (netcheck == true) {
                    new AgencyTopicReviewActivity.InsertReview().execute(review);
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent2, 7777);
                }
            }
        });
    }

    private void byid() {
        tv_id = (TextView) headerView.findViewById(R.id.tv_id);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        tv_time = (TextView) headerView.findViewById(R.id.tv_time);
        tv_topicinfo = (TextView) headerView.findViewById(R.id.tv_topicinfo);

        img_topic1 = (ImageView) headerView.findViewById(R.id.img_topic1);
        img_topic2 = (ImageView) headerView.findViewById(R.id.img_topic2);
        img_topic3 = (ImageView) headerView.findViewById(R.id.img_topic3);

        listView = (ListView) findViewById(R.id.list_topic_review);

        et_insert_review = (EditText) findViewById(R.id.et_insert_review);
        et_insert_review.setFilters(new InputFilter[]{filter});

        btn_insert_review = (Button) findViewById(R.id.btn_insert_review);
    }

    // EditText 특수문자 및 이모티콘 제외
    protected InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[0-9a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ\\x20]*$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

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
                new AgencyTopicReviewActivity.getReviewList().execute(topic);
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 리뷰 가져오기
    public class getReviewList extends AsyncTask<ModelAgencyTopic, Integer, List<ModelAgencyTopicReview>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AgencyTopicReviewActivity.this);
            waitDlg.setMessage("리뷰 정보를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelAgencyTopicReview> doInBackground(ModelAgencyTopic... params) {

            List<ModelAgencyTopicReview> list = new HttpAgency().getAgencyReviewList(params[0]);

            return list;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelAgencyTopicReview> s) {
            super.onPostExecute(s);

            reviewlist = s;
            adapter.clear();
            adapter.addAll(reviewlist);
            adapter.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 리뷰 작성
    public class InsertReview extends AsyncTask<ModelAgencyTopicReview, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AgencyTopicReviewActivity.this);
            waitDlg.setMessage("리뷰 등록중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelAgencyTopicReview... params) {

            Integer count = new HttpAgency().InsertReview(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            if (s == 1) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                review.setReview_Time(date);
                adapter.insert(review, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                et_insert_review.setText("");
            } else {
                Toast.makeText(AgencyTopicReviewActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}