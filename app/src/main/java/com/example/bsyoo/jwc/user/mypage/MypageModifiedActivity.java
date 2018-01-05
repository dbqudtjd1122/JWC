package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.IdRes;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpSignUp;
import com.example.bsyoo.jwc.hppt.HttpUser;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.AddressActivity;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

public class MypageModifiedActivity extends LoginInformation {

    private ModelUser user = new ModelUser();
    private EditText et_pw, et_pw2, et_name, et_email, et_email2, et_addr3, et_phone1, et_phone2, et_phone3, et_hphone1, et_hphone2, et_hphone3, et_Mutual, et_Representation, et_Buisness1,et_Buisness2,et_Buisness3, et_Sectors;
    private TextView tv_id, tv_addr1, tv_addr2, tv_pwcheck;
    private CheckBox ch_email, ch_phone;
    private RadioButton radioButton, radioButton2;
    private RadioGroup Rgroup;
    private LinearLayout buisness_layout;
    public Integer REQUEST_CODE = 8577;

    // 이메일 중복확인
    private int emailcheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_modified);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("개인정보 수정");

        byid();

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");
        settext();

        // 라디오그룹 클릭리스너
        Rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButton){
                    buisness_layout.setVisibility(View.GONE);
                } else {
                    buisness_layout.setVisibility(View.VISIBLE);
                }
            }
        });
        // 비밀번호 일치한지 확인
        et_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_pw2.getText().toString().equals(et_pw.getText().toString())) {
                    tv_pwcheck.setText("비밀번호가 일치합니다.");
                    tv_pwcheck.setTextColor(Color.parseColor("#FF22FF00"));
                } else {
                    tv_pwcheck.setText("비밀번호가 일치하지않습니다.");
                    tv_pwcheck.setTextColor(Color.parseColor("#FF0000"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        et_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_pw.getText().toString().equals(et_pw2.getText().toString())) {
                    tv_pwcheck.setText("비밀번호가 일치합니다.");
                    tv_pwcheck.setTextColor(Color.parseColor("#FF22FF00"));
                } else {
                    tv_pwcheck.setText("비밀번호가 일치하지않습니다.");
                    tv_pwcheck.setTextColor(Color.parseColor("#FF0000"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                tv_addr1.setText(data.getStringExtra("우편"));
                tv_addr2.setText(data.getStringExtra("주소"));
            }
            // 리턴값이 없을떄
            else {
            }
        }
    }
    public void signClick(View view) {
        switch (view.getId()) {
            case R.id.btn_email_check:
                if(user.getEmail().toString().equals(et_email.getText().toString()+"@"+et_email2.getText().toString())){
                    Toast.makeText(this, "사용가능한 Email 입니다.", Toast.LENGTH_SHORT).show();
                    emailcheck = 1;
                    break;
                }
                user.setEmail(et_email.getText().toString()+"@"+et_email2.getText().toString());
                if (et_email.getText().toString().length() <= 4 || et_email2.getText().toString().length() <= 5) {
                    Toast.makeText(this, "이메일을 정확히 입력해 주세요", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    new MypageModifiedActivity.HttpEmailCheck().execute(user);
                }
                break;
            case R.id.btn_addr:
                Intent intent = new Intent(MypageModifiedActivity.this, AddressActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btn_signupfinish:
                user.setPW(et_pw2.getText().toString());
                user.setName(et_name.getText().toString());
                user.setAddr(tv_addr1.getText().toString() + " " + tv_addr2.getText().toString() + " / " + et_addr3.getText().toString());
                user.setPhone(et_phone1.getText().toString() + et_phone2.getText().toString() + et_phone3.getText().toString());
                user.setPhone_home(et_hphone1.getText().toString() +"-"+ et_hphone2.getText().toString() +"."+ et_hphone3.getText().toString());
                if (ch_email.isChecked() == true) {
                    user.setEmail_sms(1);
                } else {
                    user.setEmail_sms(2);
                }
                if (ch_phone.isChecked() == true) {
                    user.setPhone_sms(1);
                } else {
                    user.setPhone_sms(2);
                }
                if (radioButton.isChecked() == true) {
                    user.setOK(1);
                } else {
                    user.setOK(2);
                    user.setMutual(et_Mutual.getText().toString());
                    user.setRepresentation(et_Representation.getText().toString());
                    user.setBuisness_number(et_Buisness1.getText().toString() + et_Buisness2.getText().toString() + et_Buisness3.getText().toString());
                    user.setSectors(et_Sectors.getText().toString());
                }

                // 가입정보를 입력하지 않은 경우
                if (user.getPW().length() <= 7) {
                    Toast.makeText(this, "패스워드를 8자리 이상 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    break;
                } else if (!et_pw.getText().toString().equals(et_pw2.getText().toString())) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다..", Toast.LENGTH_SHORT).show();
                    break;
                } else if (user.getName().length() <= 1) {
                    Toast.makeText(this, "이름을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    break;
                } else if (tv_addr2.getText().toString().equals("")) {
                    Toast.makeText(this, "주소를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    break;
                } else if (user.getPhone().length() <= 10) {
                    Toast.makeText(this, "핸드폰 번호를 전부 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (user.getEmail().toString().equals(et_email.getText().toString()+"@"+et_email2.getText().toString())) {
                } else {
                    if(emailcheck == 0) {
                        Toast.makeText(this, "Email 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        user.setEmail(et_email.getText().toString()+"@"+et_email2.getText().toString());
                    }
                }
                if (radioButton.isChecked() == false) {
                    if (user.getMutual().length() <= 2) {
                        Toast.makeText(this, "상호명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (user.getRepresentation().length() <= 1) {
                        Toast.makeText(this, "대표자명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (user.getBuisness_number().length() <= 9) {
                        Toast.makeText(this, "사업자번호를 정확히 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (user.getSectors().length() <= 3) {
                        Toast.makeText(this, "업태 / 업종을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                new MypageModifiedActivity.LoginUpdate().execute(user);
        }
    }

    private void byid(){
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_pw2 = (EditText) findViewById(R.id.et_pw2);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_email2 = (EditText) findViewById(R.id.et_email2);
        et_addr3 = (EditText) findViewById(R.id.et_addr3);
        et_phone1 = (EditText) findViewById(R.id.et_phone1);
        et_phone2 = (EditText) findViewById(R.id.et_phone2);
        et_phone3 = (EditText) findViewById(R.id.et_phone3);
        et_hphone1 = (EditText) findViewById(R.id.et_hphone1);
        et_hphone2 = (EditText) findViewById(R.id.et_hphone2);
        et_hphone3 = (EditText) findViewById(R.id.et_hphone3);
        et_Mutual = (EditText) findViewById(R.id.et_Mutual);
        et_Representation = (EditText) findViewById(R.id.et_Representation);
        et_Buisness1 = (EditText) findViewById(R.id.et_Buisness1);
        et_Buisness2 = (EditText) findViewById(R.id.et_Buisness2);
        et_Buisness3 = (EditText) findViewById(R.id.et_Buisness3);
        et_Sectors = (EditText) findViewById(R.id.et_Sectors);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_addr1 = (TextView) findViewById(R.id.tv_addr1);
        tv_addr2 = (TextView) findViewById(R.id.tv_addr2);
        tv_pwcheck = (TextView) findViewById(R.id.tv_pwcheck);

        ch_email = (CheckBox) findViewById(R.id.ch_email);
        ch_phone = (CheckBox) findViewById(R.id.ch_phone);

        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);

        Rgroup = (RadioGroup) findViewById(R.id.Rgroup);

        buisness_layout = (LinearLayout) findViewById(R.id.buisness_layout);
    }

    private void settext(){

        tv_id.setText(user.getID().toString());
        et_pw.setText(user.getPW().toString());
        et_pw2.setText(user.getPW().toString());
        et_name.setText(user.getName().toString());

        // 이메일값 입력
        String str = user.getEmail().toString();
        int idx = str.indexOf("@");
        et_email.setText(str.substring(0, idx));
        et_email2.setText(str.substring(idx+1, str.length()));

        // 주소값 입력
        str = user.getAddr().toString();
        idx = str.indexOf(")");
        int idx2 = str.indexOf("/");
        tv_addr1.setText(str.substring(0, idx+1));
        tv_addr2.setText(str.substring(idx+2, idx2-1));
        if(idx2+2 < str.length()) {
            et_addr3.setText(str.substring(idx2 + 2, str.length()));
        }

        // 핸드폰값 입력
        et_phone1.setText(user.getPhone().toString().substring(0, 3));
        et_phone2.setText(user.getPhone().toString().substring(3, 7));
        et_phone3.setText(user.getPhone().toString().substring(7, user.getPhone().toString().length()));

        // 집전화번호값 입력
        if(user.getPhone_home().toString().length() >= 2) {
            str = user.getPhone_home().toString();
            idx = str.indexOf("-");
            idx2 = str.indexOf(".");
            et_hphone1.setText(str.substring(0, idx));
            et_hphone2.setText(str.substring(idx+1, idx2));
            et_hphone3.setText(str.substring(idx2+1, str.length()));
        }

        // 일반소비자, 사업자 나눔
        if(user.getMutual() == null){
            radioButton.setChecked(true);
            buisness_layout.setVisibility(View.GONE);
        } else {
            radioButton2.setChecked(true);
            buisness_layout.setVisibility(View.VISIBLE);
            et_Mutual.setText(user.getMutual().toString());
            et_Representation.setText(user.getRepresentation().toString());
            et_Buisness1.setText(user.getBuisness_number().toString().substring(0,3));
            et_Buisness2.setText(user.getBuisness_number().toString().substring(3,5));
            et_Buisness3.setText(user.getBuisness_number().toString().substring(5,user.getBuisness_number().toString().length()));
            et_Sectors.setText(user.getSectors().toString());
        }

        if(user.getEmail_sms() == 1){
            ch_email.setChecked(true);
        } else {
            ch_email.setChecked(false);
        }

        if(user.getPhone_sms() == 1){
            ch_phone.setChecked(true);
        } else {
            ch_phone.setChecked(false);
        }
    }

    // Email중복체크
    public class HttpEmailCheck extends AsyncTask<ModelUser, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(MypageModifiedActivity.this);
            waitDlg.setMessage("Email 확인중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelUser... params) {

            Integer count = new HttpSignUp().EmailCheck(user);

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
                Toast.makeText(MypageModifiedActivity.this, "중복된 Email 입니다.", Toast.LENGTH_SHORT).show();
                emailcheck = 0;
            } else {
                Toast.makeText(MypageModifiedActivity.this, "사용가능한 Email 입니다.", Toast.LENGTH_SHORT).show();
                emailcheck = 1;
            }
        }
    }

    // 회원정보 업데이트
    public class LoginUpdate extends AsyncTask<ModelUser, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(MypageModifiedActivity.this);
            waitDlg.setMessage("회원정보 수정중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelUser... params) {

            Integer count = new HttpUser().LoginInfoUpdate(user);

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
                if(user.getOK()==1){
                    Toast.makeText(MypageModifiedActivity.this, "회원정보가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MypageModifiedActivity.this, "사업자 로그인 승인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(MypageModifiedActivity.this, PwCheckActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(MypageModifiedActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
