package com.example.bsyoo.jwc.mainimage.agency;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterAgency;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgency;
import com.example.bsyoo.jwc.network.Network;
import com.example.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class AgencyActivity extends AppCompatActivity {

    private AdapterAgency adapter;
    private List<ModelAgency> agencylist;
    private ModelAgency agency = new ModelAgency();
    private ListView listView;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("대리점 안내");


        listView = (ListView) findViewById(R.id.list_agency);

        // 출력 데이터 생성
        agencylist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterAgency(this, R.layout.listitem_agency, R.id.tv_agency_name, agencylist);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        netcheck = networkcheck();
        if (netcheck == true) {
            new AgencyActivity.AgencyList().execute();
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
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
                new AgencyActivity.AgencyList().execute();
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // Http List DB 가져오기
    public class AgencyList extends AsyncTask<String, Integer, List<ModelAgency>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(AgencyActivity.this);
            waitDlg.setMessage("대리점 정보를 가져오고 있습니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelAgency> doInBackground(String... params) {

            try {
                agencylist = new HttpAgency().getAgencyList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return agencylist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelAgency> list) {
            super.onPostExecute(list);
            // 1.
            agencylist = list;
            adapter.clear();
            adapter.addAll(agencylist);
            adapter.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
