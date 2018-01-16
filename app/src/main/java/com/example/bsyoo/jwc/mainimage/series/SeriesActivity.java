package com.example.bsyoo.jwc.mainimage.series;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.bsyoo.jwc.network.Network;
import com.example.bsyoo.jwc.network.NetworkCheck;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

import java.util.ArrayList;
import java.util.List;

public class SeriesActivity extends LoginInformation {

    private AdapterCamera adapter;
    private List<ModelCamera> cameralist;
    private ModelCamera camera = new ModelCamera();
    private GridView listView;

    private String series, newcamera;

    private Boolean netcheck = true;  // 네트워크 연결확인

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
        series = intent.getStringExtra("series");
        newcamera = intent.getStringExtra("new");

        // 출력 데이터 생성
        cameralist = new ArrayList<>();

        // Adapter 생성
        adapter = new AdapterCamera(this, R.layout.listitem_camera, R.id.text_cameraname, cameralist, islevel);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        if (newcamera != null) {
            if (newcamera.equals("1")) {
                setTitle("신제품 안내");
                netcheck = networkcheck();
                if (netcheck == true) {
                    new SeriesActivity.getCameraInfoList().execute(newcamera);
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivityForResult(intent2, 7777);
                }
            }
        } else {
            setTitle(series);
            netcheck = networkcheck();
            if (netcheck == true) {
                new SeriesActivity.getCameraInfoList().execute(series);
            } else {
                Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                startActivityForResult(intent2, 7777);
            }
        }

        listView.setOnItemClickListener(new SeriesActivity.OnItemHandler());
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

    // 네트워크 체크
    private boolean networkcheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if (wifi == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 네트워크 불량에서 오는 Result
        if (requestCode == 7777) {
            if (resultCode == RESULT_OK) {
                if (newcamera != null) {
                    if (newcamera.equals("1")) {
                        new getCameraInfoList().execute(newcamera);
                    }
                } else {
                    new getCameraInfoList().execute(series);
                }
            }
        }
        //리턴값이 없을때
        else {
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
                if (newcamera != null) {
                    if (newcamera.equals("1")) {
                        cameralist = new HttpCamera().getCameraNewList(params[0].toString());
                    }
                } else {
                    cameralist = new HttpCamera().getCameraInfoList(params[0].toString());
                }
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
