package com.jwcnetworks.bsyoo.jwc.mainimage.school;

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
import com.jwcnetworks.bsyoo.jwc.adapter.AdapterSchool;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpSchool;
import com.jwcnetworks.bsyoo.jwc.model.ModelSchool;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity {

    private ListView SchoolListview;
    private ModelSchool school = new ModelSchool();
    private List<ModelSchool> schoollist;
    private AdapterSchool adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        setTitle("교육목록");

        SchoolListview = (ListView) findViewById(R.id.schoollistview);
        SchoolListview.setDivider(null);

        // 출력 데이터 생성
        schoollist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterSchool(this, R.layout.listitem_school, R.id.tv_school_title, schoollist);

        // 리스트뷰에 어댑터 설정
        SchoolListview.setAdapter(adapter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if(wifi == true){

        new SchoolActivity.getSchoolList().execute();

        } else {
            Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
            startActivityForResult(intent, 7777);
        }

        SchoolListview.setOnItemClickListener(new SchoolActivity.OnItemHandler());
        SchoolListview.setOnItemLongClickListener(new SchoolActivity.OnItemHandler());
        SchoolListview.setOnItemSelectedListener(new SchoolActivity.OnItemHandler());
    }

    public class OnItemHandler implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SchoolActivity.this, SchoolInfoActivity.class);
            school = schoollist.get(position);
            intent.putExtra("school", school);
            startActivityForResult(intent, 478);
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


    public class getSchoolList extends AsyncTask<Integer, Integer, List<ModelSchool>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SchoolActivity.this);
            waitDlg.setMessage("교육정보를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelSchool> doInBackground(Integer... params) {

            schoollist = new HttpSchool().SchoolList();

            return schoollist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelSchool> list) {
            super.onPostExecute(list);

            schoollist = list;
            adapter.clear();
            adapter.addAll(schoollist);
            adapter.notifyDataSetChanged();

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 로그인창으로 가는 Result
        if (requestCode == 478 ) {
            if (resultCode == RESULT_OK) {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
            //리턴값이 없을때
            else {
            }
        }
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777 ) {
            if (resultCode == RESULT_OK) {
                new SchoolActivity.getSchoolList().execute();
            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
