package com.fiannce.bawei.a1809fiannce.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fiannce.bawei.a1809fiannce.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("LQS", getClass().getSimpleName()+" onCreate....");

        findViewById(R.id.btnA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this,BActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String value= intent.getStringExtra("startParam");
        Log.d("LQS", getClass().getSimpleName()+" onNewIntent...." + value);
    }
}
