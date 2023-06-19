package com.dya.notifi;

import static android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String Message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Message = getIntent().getStringExtra("Text");

        // Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_DENIED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS
                }, 100);
            }

        }

        String pkg=getPackageName();
        PowerManager pm=getSystemService(PowerManager.class);

        if (!pm.isIgnoringBatteryOptimizations(pkg)) {
            startActivity(new Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:"+pkg)));
        }else {
            Toast.makeText(this, "BATTERY OPTIMIZATIONS PERMISSION GRANTED", Toast.LENGTH_SHORT).show();

        }

        ScheduleNotification.scheduleNotification(this, 1, 0, 20,
                "TitleTest", "MessageTest // MessageTest");

    }
}