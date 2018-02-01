package com.jwcnetworks.bsyoo.jwc.mainimage.Cases;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterCases;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpCases;
import com.jwcnetworks.bsyoo.jwc.model.ModelCases;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class CasesActivity extends AppCompatActivity {

    private ListView CasesListView;
    private AdapterCases adapter;
    private ModelCases cases = new ModelCases();
    private List<ModelCases> caseslist = new ArrayList<>();

    private Boolean netcheck = true; // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("설치 사례");

        CasesListView = (ListView) findViewById(R.id.list_cases);

        // 출력 데이터 생성
        caseslist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterCases(this, R.layout.listitem_cases, R.id.tv_topic, caseslist);

        // 리스트뷰에 어댑터 설정
        CasesListView.setAdapter(adapter);

        netcheck = networkcheck();
        if (netcheck == true) {
            new CasesActivity.getCasesList().execute();
        } else {
            Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent2, 7777);
        }

        CasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(caseslist.get(position).getBlog_URL().toString()));
                // intent.setPackage("com.android.chrome"); // 크롬으로 열기
                startActivity(intent);
            }
        });
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
                new CasesActivity.getCasesList().execute();
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    public class getCasesList extends AsyncTask<String, Integer, List<ModelCases>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(CasesActivity.this);
            waitDlg.setMessage("설치사례를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelCases> doInBackground(String... params) {

            caseslist = new HttpCases().getCasesList();

            return caseslist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelCases> list) {
            super.onPostExecute(list);

            caseslist = list;
            adapter.clear();
            adapter.addAll(caseslist);
            adapter.notifyDataSetChanged();

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }


}
