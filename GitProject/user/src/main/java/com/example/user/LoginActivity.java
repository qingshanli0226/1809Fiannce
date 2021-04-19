package com.example.user;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity {

    private Button skipPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        skipPay.setOnClickListener(v -> {
            EventBus.getDefault().postSticky(1);
        });
    }

    private void initView() {
        skipPay = (Button) findViewById(R.id.skip_pay);
    }
}