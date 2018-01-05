package com.example.bsyoo.jwc.mainimage.series;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterCamera;
import com.example.bsyoo.jwc.hppt.HttpCamera;
import com.example.bsyoo.jwc.model.ModelCamera;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.util.ArrayList;
import java.util.List;

public class ModelSearchActivity extends LoginInformation {

    private AdapterCamera adapter;
    private List<ModelCamera> cameralist;
    private ModelCamera camera = new ModelCamera();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_search);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("제품 검색");

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        gridView = (GridView) findViewById(R.id.search_list);
        Intent intent = getIntent();
        String search = intent.getStringExtra("model");

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterCamera(this, R.layout.listitem_camera, R.id.text_cameraname, cameralist, islevel);

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
            intent.putExtra("level", islevel);
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
    public class getCameraSearchList extends AsyncTask<String, Integer, List<ModelCamera>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(ModelSearchActivity.this);
            waitDlg.setMessage("제품 정보를 가져오고 있습니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelCamera> doInBackground(String... params) {

            try {
                cameralist = new HttpCamera().getCameraSearchList(params[0].toString());
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
        protected void onPostExecute(List<ModelCamera> list) {
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
