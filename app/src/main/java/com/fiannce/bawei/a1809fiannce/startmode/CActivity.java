package com.fiannce.bawei.a1809fiannce.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fiannce.bawei.a1809fiannce.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        Intent intent = getIntent();
        String value= intent.getStringExtra("startParam");
        Log.d("LQS", getClass().getSimpleName()+" onCreate...." + value);


        findViewById(R.id.btnC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CActivity.this,AActivity.class);
                intent.putExtra("startParam", "C启动当");
                startActivity(intent);
            }
        });
    }

    //当通过startActivity(intentC)启动一个Activity时，如果被启动的Activity的启动模式是singleTop或者singleTask或者singletInstance，如果该Activity的实例被
    //复用，那么该函数onNewIntent将会被调到，该函数的的参数intent即为startActivity里面使用的intentC参数
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);//如果调用setIntent，之后通过getIntent方法获取到的intent将会是新的intent，否则默认获取的是第一次创建该Activity的intent参数

        String value= intent.getStringExtra("startParam");
        Log.d("LQS", getClass().getSimpleName()+" onNewIntent...." + value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String value= intent.getStringExtra("startParam");
        Log.d("LQS", getClass().getSimpleName()+" onResume...." + value);
    }
}
