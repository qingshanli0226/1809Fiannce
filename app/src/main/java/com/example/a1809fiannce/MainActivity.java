package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.user.LogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("zx", "onCreate: 1111");
        Log.i("zx", "onCreate: 1111");
//        Intent intent = new Intent(this, LogActivity.class);
//        intent.setAction("123");
//        startActivity(intent);
        ARouter.getInstance().build("/zx/zx1")
                .withString("name","aaa")
                .navigation();
    }
}