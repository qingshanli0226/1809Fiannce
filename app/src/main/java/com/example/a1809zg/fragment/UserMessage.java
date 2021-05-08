package com.example.a1809zg.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1809zg.R;
import com.example.frame.CacheUserManager;
import com.example.frame.MyToolbar;
import com.example.user.login.LoginActivity;

public class UserMessage extends AppCompatActivity {

    private MyToolbar bar;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initView() {
        bar = findViewById(R.id.bar);
        btn = findViewById(R.id.btn);
    }
}