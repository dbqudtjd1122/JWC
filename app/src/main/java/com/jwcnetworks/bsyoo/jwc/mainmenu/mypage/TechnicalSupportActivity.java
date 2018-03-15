package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterTechnicalSuppor;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

import java.util.ArrayList;
import java.util.List;

public class TechnicalSupportActivity extends LoginInformation {

    private ListView listview_technical;
    private AdapterTechnicalSuppor adater;
    private List<ModelTechnicalSupport> technicallist;
    private ModelUser user = new ModelUser();
    private View footerView;
    private ModelTechnicalSupport technical = new ModelTechnicalSupport();

    private ImageView img_technical_info;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle("기술지원팀 1:1 문의");

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        listview_technical = (ListView) findViewById(R.id.listview_technical);

        // 꼬리 아이템
        footerView = getLayoutInflater().inflate(R.layout.listitem_review_footer, null);

        technicallist = new ArrayList<>();

        // 어댑터를 생성하고 데이터 설정
        adater = new AdapterTechnicalSuppor(getApplicationContext(), R.layout.listitem_technical_support, R.id.tv_title, technicallist, user, isnumber, TechnicalSupportActivity.this);

        listview_technical.setOnItemClickListener( new OnItemHandler());
        listview_technical.setOnItemLongClickListener(new OnItemHandler());
        listview_technical.setOnItemSelectedListener(new OnItemHandler());

        // 꼬리아이템
        listview_technical.addFooterView(footerView, null, false);

        // 리스트뷰에 어댑터 설정
        listview_technical.setAdapter(adater);

        netcheck = networkcheck();
        if (netcheck == true) {
            new getTechnicalList().execute(user);
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        img_technical_info = (ImageView) findViewById(R.id.img_technical_info);
        img_technical_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TechnicalSupportWriteActivity.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 5849);
            }
        });
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
                new TechnicalSupportActivity.getTechnicalList().execute(user);
            }
            //리턴값이 없을때
            else {
            }
        }

        // 문의글 작성 및 수정에서 오는 Result
        if (requestCode == 5849) {
            if (resultCode == RESULT_OK) {
                new TechnicalSupportActivity.getTechnicalList().execute(user);
            }
            //리턴값이 없을때
            else {
            }
        }

        // 문의글 답변에서 오는 Result
        if (requestCode == 6422) {
            if (resultCode == RESULT_OK) {
                new TechnicalSupportActivity.getTechnicalList().execute(user);
                technical = (ModelTechnicalSupport) data.getSerializableExtra("technical");
                new TechnicalSupportActivity.PushManager().execute(technical);
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 데이터가져오기
    public class getTechnicalList extends AsyncTask<ModelUser, Integer, List<ModelTechnicalSupport>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(TechnicalSupportActivity.this);
            waitDlg.setMessage("문의 내역을 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelTechnicalSupport> doInBackground(ModelUser... params) {

            List<ModelTechnicalSupport> count = new HttpTechnicalSupport().getTechnicalSupport(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelTechnicalSupport> s) {
            super.onPostExecute(s);

            technicallist = s;
            adater.clear();
            adater.addAll(technicallist);
            adater.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 1:1 문의신청
    public class PushManager extends AsyncTask<ModelTechnicalSupport, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(ModelTechnicalSupport... params) {

            new HttpTechnicalSupport().ManagerTokenstart(params[0]);

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

        }
    }
}