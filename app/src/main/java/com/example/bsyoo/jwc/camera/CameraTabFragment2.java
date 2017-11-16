package com.example.bsyoo.jwc.camera;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.SeriesActivity;
import com.example.bsyoo.jwc.adapter.Adapter_Series;
import com.example.bsyoo.jwc.hppt.Http_Camera;
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
        //new CameraTabFragment2.getCameraList().execute("녹화기");



        // 아이템 클릭 이벤트 (camera 모델값을 넘겨준다.)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SeriesActivity.class);
                camera = cameralist.get(position);
                intent.putExtra("series", camera.getOnlineseries().toString());
                startActivity(intent);
            }
        });
    }

    // Http List DB 가져오기
    public class getCameraList extends AsyncTask<String, Integer, List<Model_Camera>> {

        private ProgressDialog waitDlg = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<Model_Camera> doInBackground(String... params) {

            try {
                cameralist = new Http_Camera().getCameraList(params[0].toString());
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
        }
    }

    public void setdata(){
        camera = new Model_Camera();
        camera.setOnlineseries("QHD 4MP ALL - HD DVR");
        cameralist.add(camera);
        camera = new Model_Camera();
        camera.setOnlineseries("ALL-HD DVR LITE 모델");
        cameralist.add(camera);
        camera = new Model_Camera();
        camera.setOnlineseries("ALL-HD DVR PRO 모델");
        cameralist.add(camera);
    }
}
