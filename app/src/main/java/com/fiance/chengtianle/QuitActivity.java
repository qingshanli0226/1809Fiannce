package com.fiance.chengtianle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fiance.chengtianle.Fragment.MineFragment;

public class QuitActivity extends AppCompatActivity {

    private TextView name;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit);
        initView();
        String uname = MineFragment.name;

        name.setText(uname);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sflogin = getSharedPreferences("sflogin", MODE_PRIVATE);
                SharedPreferences.Editor edit = sflogin.edit();
                edit.putBoolean("islogin",false);
                edit.commit();
                Intent intent = new Intent(QuitActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        name = findViewById(R.id.name);
        btn = findViewById(R.id.btn);
    }
}