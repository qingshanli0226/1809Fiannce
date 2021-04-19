package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d("123456789");
    }
}