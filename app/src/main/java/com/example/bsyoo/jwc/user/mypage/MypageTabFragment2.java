package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterSerialCode;
import com.example.bsyoo.jwc.hppt.HttpSerialCode;
import com.example.bsyoo.jwc.model.ModelUserSerialCode;
import com.example.bsyoo.jwc.model.ModelUser;

import java.util.ArrayList;
import java.util.List;


public class MypageTabFragment2 extends MypageFragment {

    private View view = null;
    private ModelUser user = new ModelUser();
    private ModelUserSerialCode usercode = new ModelUserSerialCode();
    private ListView listView;
    private List<ModelUserSerialCode> codelist;
    private AdapterSerialCode adapter;
    private Button btn_serialcode_add;

    public MypageTabFragment2(){
    }

    @Override
    public void recall() {
        super.recall();
        user = getOrderuser();
        usercode.setUser_Number(user.getUser_Number());
        new MypageTabFragment2.getCodeList().execute(usercode);
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

        btn_serialcode_add = (Button) view.findViewById(R.id.btn_serialcode_addgo);
        btn_serialcode_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SerialCodeAddActivity.class);
                intent.putParcelableArrayListExtra("usercode", (ArrayList<? extends Parcelable>) codelist);
                intent.putExtra("user", user);
                getActivity().startActivityForResult(intent, 7437);
            }
        });
    }

    // Http List DB 가져오기
    public class getCodeList extends AsyncTask<ModelUserSerialCode, Integer, List<ModelUserSerialCode>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(getContext());
            waitDlg.setMessage("시리얼 등록 리스트를 가져오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<ModelUserSerialCode> doInBackground(ModelUserSerialCode... params) {

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
        protected void onPostExecute(List<ModelUserSerialCode> list) {
            super.onPostExecute(list);

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            // 1.
            if(list != null) {
                codelist = list;
                adapter.clear();
                adapter.addAll(codelist);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
