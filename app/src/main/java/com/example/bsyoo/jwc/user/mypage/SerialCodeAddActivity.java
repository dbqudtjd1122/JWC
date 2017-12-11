package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpSerialCode;
import com.example.bsyoo.jwc.mainimage.Technology.TechnologyActivity;
import com.example.bsyoo.jwc.model.ModelCamera;
import com.example.bsyoo.jwc.model.ModelSerialCode;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.model.ModelUserSerialCode;
import com.example.bsyoo.jwc.user.Login.SignUpActivity;

import java.util.List;
import java.util.regex.Pattern;

public class SerialCodeAddActivity extends AppCompatActivity {

    private LinearLayout ll_serial;
    private EditText et_serialcode;
    private TextView tv_seriesname, tv_dvrname, tv_serialcode;
    private ImageView img_dvr;

    private ModelUserSerialCode usercode = new ModelUserSerialCode();
    private ModelSerialCode code = new ModelSerialCode();
    private ModelUser user = new ModelUser();
    private ModelCamera camera = new ModelCamera();
    private List<ModelUserSerialCode> codelist;

    private int overlap = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_code_add);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("시리얼 넘버 등록");

        byid();

        Intent intent = getIntent();
        codelist = intent.getParcelableArrayListExtra("usercode");
        user = (ModelUser) intent.getSerializableExtra("user");

    }

    private void byid(){
        ll_serial = (LinearLayout) findViewById(R.id.ll_serial);
        et_serialcode = (EditText) findViewById(R.id.et_serialcode);
        et_serialcode.setFilters(new InputFilter[]{filter});

        tv_seriesname = (TextView) findViewById(R.id.tv_seriesname);
        tv_dvrname = (TextView) findViewById(R.id.tv_dvrname);
        tv_serialcode = (TextView) findViewById(R.id.tv_serialcode);

        img_dvr = (ImageView) findViewById(R.id.img_dvr);

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
                        Toast.makeText(this, "이미 등록된 시리얼 입니다.", Toast.LENGTH_SHORT).show();
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

                if(code.getSerial_Code().toString().length() <= 5){
                    Toast.makeText(this, "6자리를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    ll_serial.setVisibility(View.GONE);
                } else {
                    new SerialCodeAddActivity.HttpSerialCheck().execute(code);
                }

                break;
            case R.id.btn_serialcode_add:  // 확인
                usercode.setUser_Number(user.getUser_Number());
                usercode.setCameratype("녹화기");
                usercode.setOnlineseries(camera.getOnlineseries().toString());
                usercode.setOnlinename(camera.getOnlinename().toString());

                usercode.setSerial_Code(code.getSerial_Code().toString());
                usercode.setImg_title(camera.getOnline_Img_title().toString());

                new SerialCodeAddActivity.InsertSerial().execute(usercode);
                break;
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
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if(s == null) {
                ll_serial.setVisibility(View.GONE);
                Toast.makeText(SerialCodeAddActivity.this, "조회되지않는 시리얼 입니다.", Toast.LENGTH_SHORT).show();
            } else {
                camera = s;
                ll_serial.setVisibility(View.VISIBLE);
                tv_seriesname.setText("시리즈이름 : " + s.getOnlineseries().toString());
                tv_dvrname.setText(   "제품이름   : " + s.getOnlinename().toString());
                tv_serialcode.setText("시리얼코드 : " + et_serialcode.getText().toString());

                Glide.with(getApplicationContext()).load(s.getOnline_Img_title().toString()).override(100, 100).fitCenter().into(img_dvr);
                et_serialcode.setText("");
            }
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
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
                Intent intent = new Intent(SerialCodeAddActivity.this, MypageActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(SerialCodeAddActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
