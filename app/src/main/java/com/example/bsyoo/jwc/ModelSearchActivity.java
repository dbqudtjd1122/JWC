package com.example.bsyoo.jwc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.bsyoo.jwc.adapter.Adapter_Camera;
import com.example.bsyoo.jwc.hppt.Http_Camera;
import com.example.bsyoo.jwc.model.Model_Camera;
import com.example.bsyoo.jwc.user.LoginInformation;

import java.util.ArrayList;
import java.util.List;

public class ModelSearchActivity extends LoginInformation {

    private Adapter_Camera adapter;
    private List<Model_Camera> cameralist;
    private Model_Camera camera = new Model_Camera();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_search);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("제품 검색");
        gridView = (GridView) findViewById(R.id.search_list);
        Intent intent = getIntent();
        String search = intent.getStringExtra("model");

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        // Adapter 생성
        adapter = new Adapter_Camera(this, R.layout.listitem_camera, R.id.text_cameraname, cameralist, islevel);

        // 리스트뷰에 어댑터 설정
        gridView.setAdapter(adapter);
        new ModelSearchActivity.getCameraSearchList().execute(search);

        gridView.setOnItemClickListener( new ModelSearchActivity.OnItemHandler());
        gridView.setOnItemLongClickListener(new ModelSearchActivity.OnItemHandler());
        gridView.setOnItemSelectedListener(new ModelSearchActivity.OnItemHandler());
    }
    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ModelSearchActivity.this, SeriesInfoActivity.class);
            camera = cameralist.get(position);
            intent.putExtra("camera", camera);
            startActivity(intent);
        }
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // Http List DB 가져오기
    public class getCameraSearchList extends AsyncTask<String, Integer, List<Model_Camera>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(ModelSearchActivity.this);
            waitDlg.setMessage("제품 정보를 가져오고 있습니다.");
            waitDlg.show();
        }

        @Override
        protected List<Model_Camera> doInBackground(String... params) {

            try {
                cameralist = new Http_Camera().getCameraSearchList(params[0].toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cameralist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Model_Camera> list) {
            super.onPostExecute(list);
            // 1.
            cameralist = list;
            adapter.clear();
            adapter.addAll(cameralist);
            adapter.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
