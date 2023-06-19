package com.dya.notifi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "channel_id";
    PendingIntent pendingIntent;
    String title;
    String message;
    int notificationId;
    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder builder;


    @Override
    public void onReceive(Context context, Intent intent) {


        // Extract notification data from the intent
        title = intent.getStringExtra("title");
        message = intent.getStringExtra("message");
        notificationId = intent.getIntExtra("notificationId", 0);


        // Create the notification channel
        createNotificationChannel(context);


        Intent repeating_Intent = new Intent(context, MainActivity.class); // Change  MainActivity.class to yor Activity want to open
        repeating_Intent.putExtra("Text", "Notification Clicked , You can used this cod to open another Activity");
        pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);


        Bitmap picture =BitmapFactory.decodeResource(context.getResources(),R.drawable.icon);

        // Create a notification
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)

                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.bell)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null)
                )
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setDefaults(Notification.DEFAULT_LIGHTS);


        // Show the notification
        notificationManager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());


    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        CharSequence name = "Daily Notification (Dya)";
        String description = "Daily Notification Description (Dya)";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }




}