package com.example.a1809fiannce.exeprice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a1809fiannce.R;

public class MainActivity extends AppCompatActivity {
    private ProView pro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        pro = (ProView) findViewById(R.id.pro);
        pro.num(80,true);
    }
}