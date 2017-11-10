package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bsyoo.jwc.adapter.Adapter_Series;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class SeriesListActivity extends AppCompatActivity {

    private Adapter_Series adapter;
    private List<Model_Camera> cameralist;
    private Model_Camera camera = new Model_Camera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);

        ListView listView = (ListView) findViewById(R.id.series_listview);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        setTitle(type);

        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        cameralist = new ArrayList<>();

        if (type.equals("카메라")){
            camera = new Model_Camera();
            camera.setSeries("JWC-L 시리즈");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("JWC-PTZ 시리즈");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("JWC-S 시리즈");
            cameralist.add(camera);

        } else if(type.equals("녹화기")){
            camera = new Model_Camera();
            camera.setSeries("QHD 4MP ALL - HD DVR");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("ALL-HD DVR LITE 모델");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("ALL-HD DVR PRO 모델");
            cameralist.add(camera);
        }

        adapter = new Adapter_Series(this, R.layout.listitem_series, R.id.series_name, cameralist);

        listView.setOnItemClickListener( new SeriesListActivity.OnItemHandler());
        listView.setOnItemLongClickListener(new SeriesListActivity.OnItemHandler());
        listView.setOnItemSelectedListener(new SeriesListActivity.OnItemHandler());

        listView.setAdapter(adapter);
    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SeriesListActivity.this, SeriesActivity.class);
            camera = cameralist.get(position);
            intent.putExtra("series", camera.getSeries().toString());
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
}
