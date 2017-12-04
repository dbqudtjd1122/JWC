package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterSerialCode;
import com.example.bsyoo.jwc.hppt.HttpSerialCode;
import com.example.bsyoo.jwc.model.ModelSerialCode;
import com.example.bsyoo.jwc.model.ModelUser;

import java.util.ArrayList;
import java.util.List;


public class MypageTabFragment2 extends MypageFragment {

    private View view = null;
    private ModelUser user = new ModelUser();
    private ModelSerialCode code = new ModelSerialCode();
    private ListView listView;
    private List<ModelSerialCode> codelist;
    private AdapterSerialCode adapter;
    private Button btn_serialcode_add;

    public MypageTabFragment2(){

    }

    @Override
    public void recall() {
        super.recall();
        user = getOrderuser();
        code.setUser_Number(user.getUser_Number());
        new MypageTabFragment2.getCodeList().execute(code);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mypage_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ListView 생성
        listView = (ListView) view.findViewById(R.id.series_code);

        // 출력 데이터 생성
        codelist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterSerialCode(getContext(), R.layout.listitem_code, R.id.tv_seriesname, codelist);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        btn_serialcode_add = (Button) view.findViewById(R.id.btn_serialcode_add);
        btn_serialcode_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SerialCodeAddActivity.class);
                startActivityForResult(intent, 156);
            }
        });

    }

    // Http List DB 가져오기
    public class getCodeList extends AsyncTask<ModelSerialCode, Integer, List<ModelSerialCode>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(getContext());
            waitDlg.setMessage("시리얼 등록 리스트를 가져오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelSerialCode> doInBackground(ModelSerialCode... params) {

            try {
                codelist = new HttpSerialCode().getSerialCodeList(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return codelist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelSerialCode> list) {
            super.onPostExecute(list);
            // 1.
            codelist = list;
            adapter.clear();
            adapter.addAll(codelist);
            adapter.notifyDataSetChanged();

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}
