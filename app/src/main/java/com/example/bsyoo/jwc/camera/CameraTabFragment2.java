package com.example.bsyoo.jwc.camera;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.SeriesActivity;
import com.example.bsyoo.jwc.adapter.Adapter_Series;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class CameraTabFragment2 extends CameraFragment {

    private View view = null;
    private Adapter_Series adapter;
    private List<Model_Camera> cameralist;
    private Model_Camera camera = new Model_Camera();
    private ListView listView;

    public CameraTabFragment2(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_camera_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ListView 생성
        listView = (ListView) view.findViewById(R.id.fraglist2);

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        setdata();

        // Adapter 생성
        adapter = new Adapter_Series(getContext(), R.layout.listitem_series, R.id.series_name, cameralist);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);



        // 아이템 클릭 이벤트 (camera 모델값을 넘겨준다.)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SeriesActivity.class);
                camera = cameralist.get(position);
                intent.putExtra("series", camera.getSeries().toString());
                startActivity(intent);
            }
        });
    }

    public void setdata(){
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
}
