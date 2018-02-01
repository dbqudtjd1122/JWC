package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpSerialCode;
import com.jwcnetworks.bsyoo.jwc.model.ModelCamera;
import com.jwcnetworks.bsyoo.jwc.model.ModelSerialCode;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.model.ModelUserSerialCode;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.List;
import java.util.regex.Pattern;

public class SerialCodeAddActivity extends AppCompatActivity {

    private LinearLayout ll_serial;
    private EditText et_serialcode, et_company_info;
    private TextView tv_seriesname, tv_serialcode, textView58;

    private ModelUserSerialCode usercode = new ModelUserSerialCode();
    private ModelSerialCode code = new ModelSerialCode();
    private ModelUser user = new ModelUser();
    private ModelCamera camera = new ModelCamera();
    private List<ModelUserSerialCode> codelist;

    private int overlap = -1;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_code_add);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("DVR 정픔 등록");

        byid();

        Intent intent = getIntent();
        codelist = intent.getParcelableArrayListExtra("usercode");
        user = (ModelUser) intent.getSerializableExtra("user");

        textView58.setText("위 시리즈가 맞으신 경우 \"등록하기\"를 눌러주세요.");
    }

    private void byid(){
        ll_serial = (LinearLayout) findViewById(R.id.ll_serial);
        et_serialcode = (EditText) findViewById(R.id.et_serialcode);
        et_serialcode.setFilters(new InputFilter[]{filter});
        et_company_info = (EditText) findViewById(R.id.et_company_info);

        tv_seriesname = (TextView) findViewById(R.id.tv_seriesname);
        tv_serialcode = (TextView) findViewById(R.id.tv_serialcode);
        textView58 = (TextView) findViewById(R.id.textView58);

    }

    // EditText 영문&숫자만 적용
    protected InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    public void serialClick(View view){
        switch (view.getId()){
            case R.id.btn_serialcode:  // 조회
                code.setSerial_Code(et_serialcode.getText().toString());
                for(int i = 0; i<=codelist.size()-1 ; i++){
                    if(codelist.get(i).getSerial_Code().toString().equals(code.getSerial_Code().toString())){
                        Toast.makeText(getApplicationContext(), "이미 등록된 시리얼 입니다.", Toast.LENGTH_SHORT).show();
                        ll_serial.setVisibility(View.GONE);
                        overlap = i;
                        break;
                    }
                }
                // 등록된 시리얼 체크
                if(overlap != -1 ){
                    overlap = -1;
                    break;
                }

                if(code.getSerial_Code().toString().length() <= 5 || code.getSerial_Code().toString().length() >= 7){
                    Toast.makeText(getApplicationContext(), "6자리를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    ll_serial.setVisibility(View.GONE);
                } else {
                    netcheck = networkcheck();
                    if (netcheck == true) {
                        new SerialCodeAddActivity.HttpSerialCheck().execute(code);
                    } else {
                        Intent intent2 = new Intent(getApplicationContext(), NetworkCheck.class);
                        startActivityForResult(intent2, 7777);
                    }
                }
                break;
            case R.id.btn_serialcode_add:  // 확인
                usercode.setUser_Number(user.getUser_Number());
                usercode.setCameratype("녹화기");
                usercode.setOnlineseries(camera.getOnlineseries().toString());
                usercode.setSerial_Code(code.getSerial_Code().toString());
                usercode.setCompany_Info(et_company_info.getText().toString());

                netcheck = networkcheck();
                if (netcheck == true) {
                    new SerialCodeAddActivity.InsertSerial().execute(usercode);
                } else {
                    Intent intent3 = new Intent(getApplicationContext(), NetworkCheck.class);
                    startActivity(intent3);
                }
                break;
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
                new SerialCodeAddActivity.HttpSerialCheck().execute(code);
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    // 시리얼 체크
    public class HttpSerialCheck extends AsyncTask<ModelSerialCode, Integer, ModelCamera> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SerialCodeAddActivity.this);
            waitDlg.setMessage("시리얼 확인중 입니다.");
            waitDlg.show();
        }

        @Override
        protected ModelCamera doInBackground(ModelSerialCode... params) {

            ModelCamera count = new HttpSerialCode().selectSerialCode(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelCamera s) {
            super.onPostExecute(s);
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if(s == null) {
                ll_serial.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "조회되지않는 시리얼 입니다.", Toast.LENGTH_SHORT).show();
            } else {
                camera = s;
                ll_serial.setVisibility(View.VISIBLE);
                tv_seriesname.setText("시리즈이름 : " + s.getOnlineseries().toString());
                tv_serialcode.setText("시리얼코드 : " + et_serialcode.getText().toString());
                et_company_info.requestFocus();

                et_serialcode.setText("");
            }
        }
    }

    // 시리얼 등록
    public class InsertSerial extends AsyncTask<ModelUserSerialCode, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SerialCodeAddActivity.this);
            waitDlg.setMessage("시리얼 등록중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelUserSerialCode... params) {

            Integer count = new HttpSerialCode().InsertSerial(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            if(s == 1){
                // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
                if (waitDlg != null) {
                    waitDlg.dismiss();
                    waitDlg = null;
                }
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
