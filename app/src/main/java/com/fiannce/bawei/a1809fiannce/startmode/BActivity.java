package com.fiannce.bawei.a1809fiannce.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fiannce.bawei.a1809fiannce.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("LQS", getClass().getSimpleName()+" onCreate....");
        findViewById(R.id.btnB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BActivity.this,CActivity.class);
                intent.putExtra("startParam", "B启动当");
                startActivity(intent);
            }
        });
    }
}
