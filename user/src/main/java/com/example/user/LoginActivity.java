package com.example.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends AppCompatActivity {

    private TextView login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void get(String str){
//        Log.i("123", "get: 123456789");
        if (str.equals("login")){
            Toast.makeText(this, "这是登录页面", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        login = (TextView) findViewById(R.id.login);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
