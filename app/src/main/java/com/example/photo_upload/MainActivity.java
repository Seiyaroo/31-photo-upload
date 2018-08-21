package com.example.photo_upload;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView mImageView;
    private int REQUEST_CHECK_PHONE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendSMS)
    public void sendSMS() {
        String number = "1-555-pizza";
        String message = "Wow, I didn't know this place existed!";

        Uri uri = Uri.parse("smsto:" + number);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", message);
        startActivity(it);
    }

    @OnClick(R.id.setAlarm)
    public void setAlarm() {
        String message = "Switch laundry";
        int hour = 12;
        int minutes = 20;

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        startActivity(intent);
    }

    @OnClick(R.id.openMaps)
    public void openMap() {
        String url = "https://www.google.com/maps/place/Domino\'s+Pizza/@47.6137027,-122.3232356,17z/data=!4m12!1m6!3m5!1s0x54906acc71609d39:0x633f4b47142a67f0!2sDomino\'s+Pizza!8m2!3d47.6136991!4d-122.3210469!3m4!1s0x54906acc71609d39:0x633f4b47142a67f0!8m2!3d47.6136991!4d-122.3210469";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.callPhone)
    public void callJenny() {
        String number = "1-206-867-5309";
        Uri uri = Uri.parse("tel:" + number);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
             startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, REQUEST_CHECK_PHONE_PERMISSION);
        }
    }
}
