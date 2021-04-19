package com.example.a1809fiannce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    private TextView st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        st.setOnClickListener(view -> {
            EventBus.getDefault().postSticky("你好");
            startActivity(new Intent("lg"));
        });
    }

    private void initView() {
        st = (TextView) findViewById(R.id.st);
    }
}