package com.example.bsyoo.jwc.user.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpUser;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.Login.LoginInformation;

public class DeleteUserActivity extends LoginInformation {

    private ModelUser user = new ModelUser();
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

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

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

    // 계정 탈퇴
    public class deleteuser extends AsyncTask<ModelUser, Integer, Integer> {

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
        protected Integer doInBackground(ModelUser... params) {

            Integer count = new HttpUser().deleteuser(user);

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
