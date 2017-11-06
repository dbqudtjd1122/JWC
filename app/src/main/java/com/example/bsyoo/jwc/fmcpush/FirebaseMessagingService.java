package com.example.bsyoo.jwc.fmcpush;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.example.bsyoo.jwc.IntroActivity;
import com.example.bsyoo.jwc.R;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    private static final String TAG = "FirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        sendPushNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
    }


    private void sendPushNotification(String title, String message) {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //                                                                                          PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // contentTitle 과 contentText는 드래그 전에 표시할 내용 입니다.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.jwc_small_logo_red).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.jwc_small_logo_red2) )
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
