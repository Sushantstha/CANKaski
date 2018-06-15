package com.tribikram.myapplication;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Tribikram on 6/5/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("legend", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("legend", "Message data payload: " + remoteMessage.getData());
            //handleNow();
        }


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("legend", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }
}
