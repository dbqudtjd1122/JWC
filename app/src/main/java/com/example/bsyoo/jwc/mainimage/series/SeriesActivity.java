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

public class SeriesActivity extends LoginInformation {

    private AdapterCamera adapter;
    private List<ModelCamera> cameralist;
    private ModelCamera camera = new ModelCamera();
    private GridView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);

        listView = (GridView) findViewById(R.id.series_list);

        Intent intent = getIntent();
        String series = intent.getStringExtra("series");

        setTitle(series);

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterCamera(this, R.layout.listitem_camera, R.id.text_cameraname, cameralist, islevel);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);
        new SeriesActivity.getCameraInfoList().execute(series);

        listView.setOnItemClickListener( new SeriesActivity.OnItemHandler());
        listView.setOnItemLongClickListener(new SeriesActivity.OnItemHandler());
        listView.setOnItemSelectedListener(new SeriesActivity.OnItemHandler());
    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SeriesActivity.this, SeriesInfoActivity.class);
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
    public class getCameraInfoList extends AsyncTask<String, Integer, List<ModelCamera>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(SeriesActivity.this);
            waitDlg.setMessage("시리즈 정보를 가져오고 있습니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelCamera> doInBackground(String... params) {

            try {
                cameralist = new HttpCamera().getCameraInfoList(params[0].toString());
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
