package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.Http_User;
import com.example.bsyoo.jwc.model.Model_User;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

public class DeleteUserActivity extends LoginInformation {

    private Model_User user = new Model_User();
    private CheckBox ch_delete;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        setTitle("계정 탈퇴");

        byid();

        SharedPreferences pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        user.setNumber(pref.getInt("number_Set", -1));

        new DeleteUserActivity.getLoginInfomation().execute(user);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch_delete.isChecked() == false){
                    Toast.makeText(DeleteUserActivity.this, "계정탈퇴에 동의해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    new DeleteUserActivity.deleteuser().execute(user);
                }
            }
        });

    }
    private void byid(){
        ch_delete = (CheckBox) findViewById(R.id.ch_delete);
        btn_delete = (Button) findViewById(R.id.btn_delete);
    }

    // 회원정보 가져오기
    public class getLoginInfomation extends AsyncTask<Model_User, Integer, Model_User> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(DeleteUserActivity.this);
            waitDlg.setMessage("회원정보를 가져오는중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Model_User doInBackground(Model_User... params) {

            Model_User count = new Http_User().getLoginInfomation(user);

            return count;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Model_User s) {
            super.onPostExecute(s);

            user = s;
            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    // 계정 탈퇴
    public class deleteuser extends AsyncTask<Model_User, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(DeleteUserActivity.this);
            waitDlg.setMessage("회원정보를 삭제중 입니다.");
            waitDlg.show();
        }
        @Override
        protected Integer doInBackground(Model_User... params) {

            Integer count = new Http_User().deleteuser(user);

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
            if(s==1) {
                Toast.makeText(DeleteUserActivity.this, "계정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeleteUserActivity.this, PwCheckActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
