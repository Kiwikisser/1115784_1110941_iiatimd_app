package com.example.koffie_app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token){
        Log.d("testtoken", token);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onMessageReceived(RemoteMessage message){
//        //Log.d("msg is: ", "notificatie ontvangen" + message);
//        sendNotification(message.getData().get("coffeejoke"));
//
//
//    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void sendNotification(String messageBody){
//        Intent intent= new Intent(this, RegisterActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("coffeejoke", messageBody);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = "1";
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,channelId)
//        .setContentTitle("haha funny coffeejoke xD")
//        .setContentText(messageBody)
//        .setSmallIcon(R.mipmap.ic_launcher)
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationChannel channel = new NotificationChannel(channelId, "coffee_notification", NotificationManager.IMPORTANCE_DEFAULT);
//        notificationManager.createNotificationChannel(channel);
//        notificationManager.notify(0, notificationBuilder.build());
//    }
}
