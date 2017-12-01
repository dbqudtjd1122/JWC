package com.example.bsyoo.jwc.school;

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
import com.example.bsyoo.jwc.adapter.AdapterSchool;
import com.example.bsyoo.jwc.hppt.HttpSchool;
import com.example.bsyoo.jwc.model.ModelSchool;

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

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("교육목록");

        SchoolListview = (ListView) findViewById(R.id.schoollistview);

        // 출력 데이터 생성
        schoollist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterSchool(this, R.layout.listitem_event, R.id.event_title, schoollist);

        // 리스트뷰에 어댑터 설정
        SchoolListview.setAdapter(adapter);

        new SchoolActivity.getSchoolList().execute();

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
}
