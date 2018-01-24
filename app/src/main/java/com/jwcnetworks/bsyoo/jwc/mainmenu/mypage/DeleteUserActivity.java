package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpUser;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.network.Network;
import com.jwcnetworks.bsyoo.jwc.network.NetworkCheck;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

public class DeleteUserActivity extends LoginInformation {

    private ModelUser user = new ModelUser();
    private CheckBox ch_delete;
    private Button btn_delete;
    private TextView tv_delete;

    private Boolean netcheck = true;  // 네트워크 연결확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("계정 탈퇴");

        byid();

        Intent intent = getIntent();
        user = (ModelUser) intent.getSerializableExtra("user");

        tv_delete.setText("- JWC 회원탈퇴 시 JWC 서비스에 탈퇴되며,\n" +
                " 다른 기본서비스(제품정보 등)는 이용이 가능합니다.\n\n" +
                "- 탈퇴 후 재가입 시, 제한을 받을 수 있습니다.\n\n" +
                "- 탈퇴한 계정의 JWC 이용 기록은 모두 삭제됩니다.\n" +
                "  삭제된 데이터는 복구가 불가합니다.\n" +
                "  (단, 작성된 리뷰와, 교육 내역은 5년까지 보관)\n\n" +
                "  [삭제되는 이용 기록]\n" +
                "  아이디, 비밀번호, 이름, 이메일, 휴대폰 번호,\n" +
                "  주소, 사업자정보\n\n" +
                "- 탈퇴시 복구가 불가능하니 신중하게 선택하세요.");
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch_delete.isChecked() == false){
                    Toast.makeText(DeleteUserActivity.this, "계정탈퇴에 동의해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    netcheck = networkcheck();
                    if (netcheck == true) {
                        new DeleteUserActivity.deleteuser().execute(user);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), NetworkCheck.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void byid(){
        ch_delete = (CheckBox) findViewById(R.id.ch_delete);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
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
            }else {
                Toast.makeText(DeleteUserActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
