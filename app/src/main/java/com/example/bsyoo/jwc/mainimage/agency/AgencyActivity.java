package com.example.bsyoo.jwc.mainimage.agency;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterAgency;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgency;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class AgencyActivity extends AppCompatActivity {

    private AdapterAgency adapter;
    private List<ModelAgency> agencylist;
    private ModelAgency agency = new ModelAgency();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("대리점 안내");

        listView = (ListView) findViewById(R.id.list_agency);

        // 출력 데이터 생성
        agencylist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterAgency(this, R.layout.listitem_agency, R.id.tv_agency_name, agencylist);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);
        new AgencyActivity.AgencyList().execute();

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
