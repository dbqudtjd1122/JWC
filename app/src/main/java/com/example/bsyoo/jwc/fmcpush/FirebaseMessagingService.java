package com.example.bsyoo.jwc.fmcpush;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.example.bsyoo.jwc.IntroActivity;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.user.Login.LoginInformation;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    public SharedPreferences pref = null;
    public String islevel;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // 1. 공유 프레퍼런스 객체를 얻어온다. /data/data/패키지명/shared_prefs/Login.xml
        pref = getSharedPreferences("Login", Context.MODE_PRIVATE);
        islevel = String.valueOf(pref.getInt("level_Set", -1));

        // 레벨에 맞는 사람에게만 푸시 알람주기
        if(remoteMessage.getData().get("level").equals(islevel)){
            sendPushNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
        }
        // 모든 사용자에게 푸시주기
        else if(remoteMessage.getData().get("level").equals("1")){
            sendPushNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
        }

    }

    private void sendPushNotification(String title, String message) {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //    PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // contentTitle 과 contentText는 드래그 전에 표시할 내용 입니다.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.jwc_small_logo_red).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.pushrogo) )
                .setContentTitle(title)
                .setContentText("아래로 당겨 주세요 ▼")
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000)
                // 푸시 드래그 후에 표시될 내용
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(title)
                        .bigText(message))
                .setContentIntent(pendingIntent);

        // 푸시 드래그 후에 표시될 내용
        //NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(notificationBuilder);
        //style.bigText(message).setBigContentTitle("JWC 공지 사항");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
