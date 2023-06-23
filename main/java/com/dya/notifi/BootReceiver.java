package com.dya.notifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Reschedule all notifications
            ScheduleNotification.scheduleNotification(context, 1, 0, 20,
                    "TitleTest", "MessageTest // MessageTest");
             /* 
            // Reschedule all notifications
            ScheduleNotification.scheduleNotification(context, 2, 22, 59,
                    "ویردەکانی بەیانیان", "ئیستا کاتی خویندنی ویردەکانی بەیانیانە ☀");
                    */

        }


      
    }
}
