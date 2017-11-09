package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bsyoo.jwc.adapter.Adapter_Camera;
import com.example.bsyoo.jwc.camera.CameraTabFragment1;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class SeriesActivity extends AppCompatActivity {

    private Adapter_Camera adapter;
    private List<Model_Camera> cameralist;
    private Model_Camera camera = new Model_Camera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        ListView listView = (ListView) findViewById(R.id.series_list);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        Intent intent = getIntent();
        String series = intent.getStringExtra("series");
        setTitle(series);

        /*// 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        if(series.equals("JWC-L 시리즈")){
            camera = new Model_Camera();
            camera.setSeries("[JWC-L 시리즈]");
            camera.setOnlinename("JWC-L1VD");
            camera.setPerformance("12LED 2.8mm 광각, SONY 1/2.8 스타비스센서 메탈 반달케이스");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[JWC-L 시리즈]");
            camera.setOnlinename("JWC-L3HAF");
            camera.setPerformance("하이파워 4LED 2.8~12mm 오토포커스렌즈 롱바디 하우징일체형, UTC ZOOM 컨트롤");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[JWC-L 시리즈]");
            camera.setOnlinename("JWC-L4LPR");
            camera.setPerformance("파워 18LED (740nm) 6~50mm IR조절기능 시속 60km 차량번호식별 UTC 아답타별매");
            cameralist.add(camera);
        } else if(series.equals("JWC-PTZ 시리즈")){
            camera = new Model_Camera();
            camera.setSeries("[JWC-PTZ 시리즈]");
            camera.setOnlinename("JSP-210A");
            camera.setPerformance("하이파워 6LED, 광학10배줌(5~50mm) SONY센서, UTC지원, 벽브라켓+아답타포함");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[JWC-PTZ 시리즈]");
            camera.setOnlinename("JSP-218A");
            camera.setPerformance("하이파워 14LED, 광학18배줌(4.7~94mm) SONY센서, UTC지원, 벽브라켓+아답타포함");
            cameralist.add(camera);
        } else if(series.equals("JWC-S 시리즈")){
            camera = new Model_Camera();
            camera.setSeries("[JWC-S 시리즈]");
            camera.setOnlinename("JWC-S1D 2.8mm");
            camera.setPerformance("2.8mm None IR 광각 SONY 1/2.8 센서 플라스틱 화이트색상");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[JWC-S 시리즈]");
            camera.setOnlinename("JWC-S1D 3.6mm");
            camera.setPerformance("3.6mm None IR SONY 1/2.8 센서 플라스틱 화이트색상");
            cameralist.add(camera);
        }
        // ---------------------
        else if(series.equals("QHD 4MP ALL - HD DVR")){
            camera = new Model_Camera();
            camera.setSeries("[QHD 4MP]");
            camera.setOnlinename("JDO-4008B (1TB)");
            camera.setPerformance("AHD+TVI+CVI+SD (4MP+2MP) 60FPS@4MP 녹화 최대 20TB지원");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[QHD 4MP]");
            camera.setOnlinename("JDO-4008B (2TB)");
            camera.setPerformance("AHD+TVI+CVI+SD (4MP+2MP) 60FPS@4MP 녹화 최대 20TB지원");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[QHD 4MP]");
            camera.setOnlinename("JDO-4008B (3TB)");
            camera.setPerformance("AHD+TVI+CVI+SD (4MP+2MP) 60FPS@4MP 녹화 최대 20TB지원");
            cameralist.add(camera);
        }
        else if(series.equals("ALL-HD DVR LITE 모델")){
            camera = new Model_Camera();
            camera.setSeries("[ALL-HD DVR LITE]");
            camera.setOnlinename("JDO-4005B (1TB)");
            camera.setPerformance("120FPS@1080P 녹화 AHD+TVI+CVI+SD");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[ALL-HD DVR LITE]");
            camera.setOnlinename("JDO-8005 (1TB)");
            camera.setPerformance("240FPS@1080P 녹화 AHD+TVI+CVI+SD");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[ALL-HD DVR LITE]");
            camera.setOnlinename("JDO-1605 (2TB)");
            camera.setPerformance("120FPS@1080P 풀프레임 녹화");
            cameralist.add(camera);
        } else if(series.equals("ALL-HD DVR PRO 모델")){
            camera = new Model_Camera();
            camera.setSeries("[ALL-HD DVR PRO]");
            camera.setOnlinename("JDO-4008 (1TB)");
            camera.setPerformance("120FPS@1080P 풀프레임 녹화");
            cameralist.add(camera);
            camera = new Model_Camera();
            camera.setSeries("[ALL-HD DVR PRO]");
            camera.setOnlinename("JDO-4008 (2TB)");
            camera.setPerformance("120FPS@1080P 풀프레임 녹화");
            cameralist.add(camera);
        }

        // Adapter 생성
        adapter = new Adapter_Camera(this, R.layout.listitem_camera, R.id.text_series, cameralist);

        listView.setOnItemClickListener( new SeriesActivity.OnItemHandler());
        listView.setOnItemLongClickListener(new SeriesActivity.OnItemHandler());
        listView.setOnItemSelectedListener(new SeriesActivity.OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);
    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SeriesActivity.this, SeriesInfoActivity.class);
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
}
