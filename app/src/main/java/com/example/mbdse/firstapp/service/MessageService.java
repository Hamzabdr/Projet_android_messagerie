package com.example.mbdse.firstapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.mbdse.firstapp.ConnexionActivity;
import com.example.mbdse.firstapp.R;

public class MessageService extends Service {

        public static final int MSG_SAY_HELLO = 1;
        public static boolean serviceRunning = false;


        static class IncomingHandler extends Handler {
            private Context applicationContext;

            IncomingHandler(Context context) {
                applicationContext = context.getApplicationContext();
            }

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_SAY_HELLO:
                        Toast.makeText(applicationContext, "hello!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        }

        Messenger mMessenger;

        @Override
        public void onCreate() {
            if(serviceRunning) return;
            super.onCreate();
            Intent notificationIntent = new Intent(this, ConnexionActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, "M_CH_ID")
                    .setSmallIcon(R.drawable.message)
                    .setContentTitle("MsgMe")
                    .setContentText("You have a new msg")
                    .setContentIntent(pendingIntent).build();

            startForeground(1337, notification);
            Log.i("SERVICE", "onCreate Called!");
        }

        @Override
        public void onTaskRemoved(Intent rootIntent) {
            Log.i("SERVICE", "task removed!");
            myJob.stopJob();
            serviceRunning = false;
            Intent intent = new Intent();
            intent.setAction("com.example.mbdse.firstapp.service.restartService");
            intent.setClass(this, Receiver.class);
            sendBroadcast(intent);
            super.onTaskRemoved(rootIntent);
        }

        @Override
        public void onDestroy() {
            myJob.stopJob();
            serviceRunning = false;
            Toast.makeText(this,"Service stopped", Toast.LENGTH_SHORT).show();
            Log.e("SERVICE", "DESTROYED");
            Intent intent = new Intent("com.example.mbdse.firstapp.service.restartService");
            sendBroadcast(intent);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
            mMessenger = new Messenger(new IncomingHandler(this));
            return mMessenger.getBinder();
        }

        Thread thread;
        Job myJob;
        class Job implements Runnable{
            private boolean doStop = false;

            public synchronized  void stopJob(){
                doStop = true;
            }

            private synchronized boolean keepRunning() {
                return !doStop;
            }

            @Override
            public void run() {
                int i = 0;
                Looper.prepare();
                while(keepRunning()) {
                    try {
                        i++;
                        final int ii = i;
                        Thread.sleep(1000);
                        Log.i("SERVICE", "log " + i);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MessageService.this, "Service " + ii, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if(serviceRunning) return START_NOT_STICKY;
            super.onStartCommand(intent,flags,startId);
            Toast.makeText(this, "Started Service!!!", Toast.LENGTH_SHORT).show();

            myJob = new Job();
            thread = new Thread(myJob);
            thread.start();
            serviceRunning = true;
            return START_STICKY;
        }
    }


