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
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;

import java.util.Date;
import java.util.regex.Pattern;

public class TechnicalSupportWriteActivity extends AppCompatActivity {

    private ModelUser user = new ModelUser();
    private ModelTechnicalSupport technical = new ModelTechnicalSupport();
    private TextView tv_name, tv_email;
    private Button btn_finish, btn_question;
    private CheckBox chbox_email, chbox_sms;

    private EditText edt_phone, edt_info, edt_title;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support_info);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("기술지원팀 1:1 문의");

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        settext();

        final int i = intent.getIntExtra("1", -1);
        if (i == 1){
            technical = (ModelTechnicalSupport) intent.getSerializableExtra("technical");
            if(technical.getEmailcheck() == 1){
                chbox_email.setChecked(true);
            }
            if(technical.getSmscheck() == 1){
                chbox_sms.setChecked(true);
            }
            edt_title.setText(technical.getTitle().toString());
            edt_info.setText(technical.getInfo().toString());
        }

        btn_question.setOnClickListener(new View.OnClickListener() { // 질문유형
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                technical.setID(user.getID().toString());
                technical.setUserNumber(user.getUser_Number());
                technical.setQuestion(btn_question.getText().toString());
                technical.setTitle(edt_title.getText().toString());
                technical.setInfo(edt_info.getText().toString());
                if(chbox_email.isChecked() == true){
                    technical.setEmailcheck(1);
                } else {
                    technical.setEmailcheck(2);
                }
                if(chbox_sms.isChecked() == true){
                    technical.setSmscheck(1);
                } else {
                    technical.setSmscheck(2);
                }
                Date mDate = new Date();
                technical.setInserttime(mDate);
                technical.setManagertime(mDate);

                if(technical.getQuestion().toString().equals("상담 내용을 선택하세요.")){
                    Toast.makeText(getApplicationContext(), "상담 유형을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    netcheck = networkcheck();
                    if (netcheck == true) {
                        if(i == 1){
                            new TechnicalSupportWriteActivity.updateTechnicalSupport().execute(technical);
                        } else {
                            new TechnicalSupportWriteActivity.insertTechnicalSupport().execute(technical);
                        }
                    } else {
                        Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                        startActivityForResult(intent, 7777);
                    }
                }
            }
        });
    }

    // 길게 눌렀을때 호출 메소드
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v== btn_question) {
            getMenuInflater().inflate(R.menu.technicalsupport_menu, menu);
        }
    }

    // 메뉴 아이템을 선택했을때 자동 호출 메소드
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //눌러진 MenuItem의 Item Id를 얻어와 식별
        switch (item.getItemId()) {
            case R.id.item_as:
                btn_question.setText("A/S 문의");
                break;
            case R.id.item_connect:
                btn_question.setText("제품연결 문의");
                break;
            case R.id.item_remote:
                btn_question.setText("원격지원 문의");
                break;
            case R.id.item_etc:
                btn_question.setText("기타");
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void settext(){
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_info = (EditText) findViewById(R.id.edt_info);
        edt_info.setFilters(new InputFilter[]{specialCharacterFilter});
        edt_title = (EditText) findViewById(R.id.edt_title);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_question = (Button) findViewById(R.id.btn_question);
        registerForContextMenu(btn_question);
        btn_question.setLongClickable(false);
        chbox_email = (CheckBox) findViewById(R.id.chbox_email);
        chbox_sms = (CheckBox) findViewById(R.id.chbox_sms);

        tv_name.setText(user.getName().toString());
        tv_email.setText(user.getEmail().toString());
        edt_phone.setText(user.getPhone().toString());
    }

    /** 이모티콘이 있을경우 "" 리턴, 그렇지 않을 경우 null 리턴 **/
    protected InputFilter specialCharacterFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                // 이모티콘 패턴
                Pattern unicodeOutliers = Pattern.compile("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+");
                // '-' 입력 받고 싶을 경우 : unicodeOutliers.matcher(source).matches() && !source.toString().matches(".*-.*")
                if(unicodeOutliers.matcher(source).matches()) {
                    return "";
                }
            }
            return null;
        }
    };

    // 1:1 문의신청
    public class insertTechnicalSupport extends AsyncTask<ModelTechnicalSupport, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(TechnicalSupportWriteActivity.this);
            waitDlg.setMessage("1:1 문의 중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Integer doInBackground(ModelTechnicalSupport... params) {

            Integer count = new HttpTechnicalSupport().insertTechnicalSupport(params[0]);
            if(count == 1){
                new HttpTechnicalSupport().Tokenstart(params[0]);
            }

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s == 1) {
                Toast.makeText(getApplicationContext(), "1:1 문의가 접수 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TechnicalSupportActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 1:1 문의 업데이트
    public class updateTechnicalSupport extends AsyncTask<ModelTechnicalSupport, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(TechnicalSupportWriteActivity.this);
            waitDlg.setMessage("1:1 문의 수정 중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Integer doInBackground(ModelTechnicalSupport... params) {

            Integer count = new HttpTechnicalSupport().updateTechnicalSupport(params[0]);

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s == 1) {
                Toast.makeText(getApplicationContext(), "1:1 문의가 수정 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TechnicalSupportActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 네트워크 체크
    private boolean networkcheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean wifi = new Network().isNetWork(networkInfo);
        if(wifi == true){
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
            }
            //리턴값이 없을때
            else {
            }
        }
    }
}
