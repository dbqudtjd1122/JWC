package com.example.bsyoo.jwc.mainimage.Technology;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterTechnology;
import com.example.bsyoo.jwc.hppt.HttpTechnology;
import com.example.bsyoo.jwc.model.ModelTechnology;

import java.util.ArrayList;
import java.util.List;

public class TechnologyTabFragment1 extends TechnologyFragment {

    private View view = null;
    private ListView listView;
    private AdapterTechnology adapter;
    private List<ModelTechnology> techlist;
    private ModelTechnology tech = new ModelTechnology();

    public TechnologyTabFragment1() {

    }

    @Override
    public void recall() {
        super.recall();
        if(getNet()==true) {
            new TechnologyTabFragment1.getCameraList().execute("Camera");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_technology_tab_fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ListView 생성
        listView = (ListView) view.findViewById(R.id.list_Technology1);

        // 출력 데이터 생성
        techlist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterTechnology(getContext(), R.layout.listitem_notice, R.id.notice1, techlist);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);
        new TechnologyTabFragment1.getCameraList().execute("Camera");


        // 아이템 클릭 이벤트 (camera 모델값을 넘겨준다.)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TechnologyInfoActivity.class);
                tech = techlist.get(position);
                intent.putExtra("tech", tech);
                startActivity(intent);
            }
        });
    }

    // Http List DB 가져오기
    public class getCameraList extends AsyncTask<String, Integer, List<ModelTechnology>> {

        private ProgressDialog waitDlg = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(getContext());
            waitDlg.setMessage("카메라 영상리스트를 가져오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelTechnology> doInBackground(String... params) {

            try {
                techlist = new HttpTechnology().getTechnology(params[0].toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return techlist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelTechnology> list) {
            super.onPostExecute(list);

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            if(list != null) {
                techlist = list;
                adapter.clear();
                adapter.addAll(techlist);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}