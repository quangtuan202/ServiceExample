package com.tuan.serviceexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.tuan.serviceexample.App.CHANNEL_ID;
import static com.tuan.serviceexample.NotificationConstant.TIME_COUNTER_ACTION;
import static com.tuan.serviceexample.NotificationTimeConverter.convertToTimeWithTwoFormat;

public class ExampleService extends Service {
    public static long timePass1=0;
    public static long timePass2=0;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        int id=intent.getIntExtra("id",0);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                //.setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent);
                //.build();


        CountDownTimer countDownTimer = new CountDownTimer(1_000_000_000_000L, NotificationConstant.COUNT_DOWN_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                timePass1++;

                //NotificationTimer.this.notificationBuilder.setContentTitle("Sleeping...");
                notification.setContentText(convertToTimeWithTwoFormat(timePass1));
                notificationManager.notify(id, notification.build());

            }

            @Override
            public void onFinish() {
                ;
            }
        };

        if(id==1) {
            countDownTimer.start();
            //startForeground(id, notification.build());
        }
        else {
            //NotificationManager notificationManager = getSystemService(NotificationManager.class);
            countDownTimer.start();
            //notificationManager.notify(id, notification.build());
        }
        //do heavy work on a background thread
        //stopSelf();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}