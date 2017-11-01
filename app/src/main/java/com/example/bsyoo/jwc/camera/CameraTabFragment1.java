package com.example.bsyoo.jwc.camera;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.Adapter_Camera;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class CameraTabFragment1 extends CameraFragment {

    private View view = null;
    private Adapter_Camera adapter;
    private List<Model_Camera> cameralist;
    private Model_Camera camera = new Model_Camera();

    public CameraTabFragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_camera_tab_fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ListView 생성
        ListView listView = (ListView) view.findViewById(R.id.fraglist1);

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

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

        // Adapter 생성
        adapter = new Adapter_Camera(getContext(), R.layout.listitem_camera, R.id.text_series, cameralist);

        listView.setOnItemClickListener( new OnItemHandler());
        listView.setOnItemLongClickListener(new OnItemHandler());
        listView.setOnItemSelectedListener(new OnItemHandler());

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
