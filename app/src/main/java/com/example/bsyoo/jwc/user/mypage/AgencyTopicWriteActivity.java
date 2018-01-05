package com.example.bsyoo.jwc.user.mypage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsyoo.jwc.IntroActivity;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.fmcpush.FirebaseInstanceIDService;
import com.example.bsyoo.jwc.fmcpush.FirebaseMessagingService;
import com.example.bsyoo.jwc.hppt.HttpAgency;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.example.bsyoo.jwc.model.ModelUser;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.apache.harmony.awt.datatransfer.DataSnapshot;
import org.json.JSONObject;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class AgencyTopicWriteActivity extends AppCompatActivity {

    private ModelAgencyTopic topic = new ModelAgencyTopic();
    private ModelUser user = new ModelUser();
    private TextView tv_id;
    private Button btn_finish;
    private EditText et_info, et_name;

    private final String TAG = "JSA-FCM";
    private Random random = new Random();

    private int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_topic_write);

        // 액션바에 백그라운드 이미지 넣기
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.actionbar);
        getSupportActionBar().setBackgroundDrawable(d);
        setTitle("대리점 공유글 작성");

        byid();

        Intent intent = getIntent();
        i = intent.getIntExtra("1", -1);
        // 수정
        if (i == 1) {
            topic = (ModelAgencyTopic) intent.getSerializableExtra("topic");
            tv_id.setText("작성자 : " + topic.getID().toString());
            et_name.setText(topic.getTopic_Name().toString());
            et_info.setText(topic.getInformation().toString());
        }
        // 새로 작성
        else {
            user = (ModelUser) intent.getSerializableExtra("user");
            tv_id.setText("작성자 : " + user.getID().toString());
        }

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic.setTopic_Name(et_name.getText().toString());
                topic.setInformation(et_info.getText().toString());
                if (i == 1) {
                    topic.setID(topic.getID().toString());
                    new AgencyTopicWriteActivity.UpdateTopic().execute(topic);
                } else {
                    topic.setID(user.getID().toString());
                    new AgencyTopicWriteActivity.InsertTopic().execute(topic);
                }
            }
        });
    }

    private void byid() {
        tv_id = (TextView) findViewById(R.id.tv_id);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        et_info = (EditText) findViewById(R.id.et_info);
        et_info.setFilters(new InputFilter[]{filter});
        et_name = (EditText) findViewById(R.id.et_name);
        et_info.setFilters(new InputFilter[]{filter});
    }

    // EditText 특수문자 및 이모티콘 제외
    protected InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[0-9a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ\\x20]*$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    // 작성
    public class InsertTopic extends AsyncTask<ModelAgencyTopic, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AgencyTopicWriteActivity.this);
            waitDlg.setMessage("작성글 등록중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelAgencyTopic... params) {

            Integer count = new HttpAgency().InsertTopic(params[0]);

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
                /*FirebaseMessaging fm = FirebaseMessaging.getInstance();
                fm.send(new RemoteMessage.Builder("387675888283" + "@gcm.googleapis.com")
                        .setMessageId(Integer.toString(msgId.incrementAndGet()))
                        .addData("title", "Hello World")
                        .addData("message","SAY_HELLO")
                        .build());*/

                /*
                FirebaseMessaging fm = FirebaseMessaging.getInstance();
                String to = "387675888283"+"@gcm.googleapis.com"; // the notification key
                AtomicInteger msgId = new AtomicInteger();
                fm.send(new RemoteMessage.Builder(to)
                        .setMessageId(String.valueOf(msgId.incrementAndGet()))
                        .addData("title", "titleworld")
                        .addData("message", "world")
                        .addData("level", "1")
                        .build());
                        */

                /*FirebaseMessaging fm = FirebaseMessaging.getInstance();
                RemoteMessage message = new RemoteMessage.Builder("118371ec89e84dbe"+"@gcm.googleapis.com")
                        .setMessageId(Integer.toString(random.nextInt(9999)))
                        .addData("title", "titleworld")
                        .addData("message", "world")
                        .addData("level", "1")
                        .build();

                if (!message.getData().isEmpty()) {
                    Log.e(TAG, "UpstreamData: " + message.getData());
                }

                if (!message.getMessageId().isEmpty()) {
                    Log.e(TAG, "UpstreamMessageId: " + message.getMessageId());
                }
                fm.send(message);*/

                //sendPushNotification("타이틀 테스트", "메세지 테스트");

                Intent intent = new Intent(AgencyTopicWriteActivity.this, AgencyTopicActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(AgencyTopicWriteActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 수정
    public class UpdateTopic extends AsyncTask<ModelAgencyTopic, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AgencyTopicWriteActivity.this);
            waitDlg.setMessage("게시글 수정중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelAgencyTopic... params) {

            Integer count = new HttpAgency().UpdateTopic(params[0]);

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
                Intent intent = new Intent(AgencyTopicWriteActivity.this, AgencyTopicActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(AgencyTopicWriteActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}