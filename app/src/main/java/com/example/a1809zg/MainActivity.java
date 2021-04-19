package com.example.a1809zg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent();
//        intent.setAction("aa");
//        startActivity(intent);
        ARouter.getInstance()
                .build("/a/aa")
                .withString("name","123")
                .navigation(MainActivity.this);
    }
}