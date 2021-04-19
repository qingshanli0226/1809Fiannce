package com.fiance.chengtianle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fiance.user.UserActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().postSticky("tz");

        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);



    }

}