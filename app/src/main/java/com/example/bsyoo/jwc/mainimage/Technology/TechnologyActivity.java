package com.example.bsyoo.jwc.mainimage.Technology;

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
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterTechnology;
import com.example.bsyoo.jwc.hppt.HttpTechnology;
import com.example.bsyoo.jwc.model.ModelTechnology;

import java.util.ArrayList;
import java.util.List;

public class TechnologyActivity extends AppCompatActivity  {

    private ListView listview;
    private AdapterTechnology adater;
    private List<ModelTechnology> techlist;
    private ModelTechnology tech = new ModelTechnology();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        listview = (ListView) findViewById(R.id.list_Technology);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }

        getSupportActionBar().setTitle("기술자료");

        techlist = new ArrayList<>();

        // 어댑터를 생성하고 데이터 설정
        adater = new AdapterTechnology(this, R.layout.listitem_notice, R.id.notice1, techlist);

        listview.setOnItemClickListener( new TechnologyActivity.OnItemHandler());
        listview.setOnItemLongClickListener(new TechnologyActivity.OnItemHandler());
        listview.setOnItemSelectedListener(new TechnologyActivity.OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listview.setAdapter(adater);
        new TechnologyActivity.getTechList().execute();
    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(TechnologyActivity.this, TechnologyInfoActivity.class);
            tech = techlist.get(position);
            intent.putExtra("tech", tech);
            startActivity(intent);

        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // String msg = "onItemLongClick : "+position + ", "+id;
            // Toast.makeText(TechnologyActivity.this, msg, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // String msg = "onItemSelected : "+position + ", "+id;
            // Toast.makeText(TechnologyActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //Toast.makeText(TechnologyActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
        }
    }

    // 데이터가져오기
    public class getTechList extends AsyncTask<String, Integer, List<ModelTechnology>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(TechnologyActivity.this);
            waitDlg.setMessage("기술정보를 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelTechnology> doInBackground(String... params) {

            List<ModelTechnology> list = new HttpTechnology().getTechnology();

            return list;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelTechnology> s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            if(s != null) {
                techlist = s;
                adater.clear();
                adater.addAll(techlist);
                adater.notifyDataSetChanged();
            } else {
                Toast.makeText(TechnologyActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
