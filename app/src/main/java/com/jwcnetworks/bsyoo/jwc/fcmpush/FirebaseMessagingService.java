package com.jwcnetworks.bsyoo.jwc.fcmpush;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.jwcnetworks.bsyoo.jwc.IntroActivity;
import com.jwcnetworks.bsyoo.jwc.MainActivity;
import com.jwcnetworks.bsyoo.jwc.R;
import com.google.firebase.messaging.RemoteMessage;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpUser;
import com.jwcnetworks.bsyoo.jwc.model.ModelPushToken;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    public SharedPreferences pref = null;
    public Integer islevel;
    public Integer ispush;
    public Integer isnumber;
    public String isid;

    public String title, message, page, same, myimgurl;
    public Integer level;   // 푸시에서 오는 레벨
    public Bitmap bigPicture;

    public ModelUser user = new ModelUser();
    public ModelPushToken pushToken = new ModelPushToken();

    public FirebaseMessagingService() {
    }

    @SuppressLint("WrongThread")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // 1. 공유 프레퍼런스 객체를 얻어온다. /data/data/패키지명/shared_prefs/Login.xml
        pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        islevel = pref.getInt("level_Set", -1);
        ispush = pref.getInt("push_Set", 1);
        isnumber = pref.getInt("number_Set", -1);
        isid = pref.getString("id_Set", "").toString();


        String tit = remoteMessage.getData().get("title").toString();
        String mes = remoteMessage.getData().get("message").toString();
        level = Integer.valueOf(remoteMessage.getData().get("level").toString());
        String type = remoteMessage.getData().get("type").toString();
        String page2 = remoteMessage.getData().get("page").toString();
        String same2 = remoteMessage.getData().get("same").toString();

        myimgurl = remoteMessage.getData().get("url").toString();

        //이미지 온라인 링크를 가져와 비트맵으로 바꾼다.
        if(!myimgurl.equals("")) {
            try {
                URL url = new URL(myimgurl);
                bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 비트맵으로 변경 2번째방법
        /*try {
            URL url = new URL(myimgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bigPicture = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
        }*/

        title = tit;
        message = mes;
        page = page2;
        same = same2;
        // 대리점 네트워크 /send 타면 type = 2 가 온다. UTF-8 타입
        if (type.equals("2")) {
            try {
                title = URLDecoder.decode(tit, "UTF-8");
                message = URLDecoder.decode(mes, "UTF-8");
                page = URLDecoder.decode(page2, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (ispush == 1) { // 푸시 수신여부{
            if (isnumber != -1) { // 로그인 한경우만
                user.setUser_Number(isnumber);
                new FirebaseMessagingService.getLoginInfomation().execute(user);
            } else { // 로그인 안한경우
                PushCheck(level);
            }
        } else {    // 푸시 수신 거부
            if (isnumber != -1) {   // 로그인 한 경우만
                setPushToken();
                pushToken.setInoutpush("수신거부");
                new FirebaseMessagingService.insertpushLog().execute(pushToken);
            }
        }
    }

    // 푸시 로그 입력
    public void setPushToken() {
        pushToken.setPushtitle(title);
        pushToken.setPushmessage(message);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        pushToken.setPushtime(date);

        pushToken.setUserid(isid);
        pushToken.setUserlevel(islevel);
        if (ispush == 1) {
            pushToken.setReception("수신");
        } else {
            pushToken.setReception("수신거부");
        }
    }

    public void PushCheck(Integer level) {
        if (level == 1) {        // 모든 사용자에게 푸시주기
            sendPushNotification(title, message);
        } else if (same.equals("1")) {   // 레벨에 맞는 사람 & 11레벨 이상등급 푸시 알람주기
            if (level == islevel || islevel >= 11) {
                sendPushNotification(title, message);
            }
        } else if (same.equals("2")) {  //  레벨이상의 모든 등급 푸시 알람 주기
            if (level <= islevel) {
                sendPushNotification(title, message);
            }
        }
    }

    public void sendPushNotification(String title, String message) {
        System.out.println("received message : " + message);

        Intent intent = null;
        if (page.equals("agency") && islevel >= 10) { // 대리점 네트워크
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("page", "agency");
        } else if (page.equals("dvr") && islevel >= 1) { // DVR 시리얼등록
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("page", "dvr");
        } else if (page.equals("wed")) { // 수요쿠폰
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("page", "wed");
        } else if (page.equals("redfriday")) { // 레드프라이데이
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("page", "redfriday");
        } else {
            intent = new Intent(this, IntroActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.putExtra("page", "normal");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //    PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
        // PendingIntent pendingIntent = PendingIntent.getService(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        //이미지 온라인 링크를 가져와 비트맵으로 바꾼다.
        /*try {
            URL url = new URL(myimgurl);
            bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // contentTitle 과 contentText는 드래그 전에 표시할 내용 입니다.
        NotificationCompat.Builder notificationBuilder = null;
        if(myimgurl.equals("")) { // 이미지 url 없음
             notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.myjwc_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pushrogo))
                    .setContentTitle(title)
                    .setContentText("아래로 당겨 주세요 ▼")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri).setLights(000000255, 500, 2000)
                    // 푸시 드래그 후에 표시될 내용
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(title)
                            .bigText(message))
                    .setContentIntent(pendingIntent);
        } else {  // 이미지 url 있음
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.myjwc_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pushrogo))
                    .setContentTitle(title)
                    .setContentText("아래로 당겨 주세요 ▼")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri).setLights(000000255, 500, 2000)
                    // 이미지 적용시
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bigPicture)
                            .setBigContentTitle(title)
                            .setSummaryText(message))
                    .setContentIntent(pendingIntent);
        }
        // 푸시 드래그 후에 표시될 내용 (위쪽에 적용되어있음)
        //NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(notificationBuilder);
        //style.bigText(message).setBigContentTitle(title);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    // 회원정보 가져오기
    public class getLoginInfomation extends AsyncTask<ModelUser, Integer, ModelUser> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ModelUser doInBackground(ModelUser... params) {

            ModelUser count = new HttpUser().getLoginInfomation(user);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser s) {
            super.onPostExecute(s);
            if (s != null) {
                user = s;
                islevel = user.getLevel();
                PushCheck(level);

                // 푸시 로그 입력
                setPushToken();
                pushToken.setInoutpush("푸시완료");
                new FirebaseMessagingService.insertpushLog().execute(pushToken);
            }
        }
    }

    // 푸시 로그 기록하기
    public class insertpushLog extends AsyncTask<ModelPushToken, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(ModelPushToken... params) {

            Integer count = new HttpUser().InsertpushLog(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
        }
    }

    @Override
    public void onMessageSent(String msgId) {
        Log.e(TAG, "onMessageSent: " + msgId);
    }

    @Override
    public void onSendError(String msgId, Exception e) {
        Log.e(TAG, "onSendError: " + msgId);
        Log.e(TAG, "Exception: " + e);
    }
}
