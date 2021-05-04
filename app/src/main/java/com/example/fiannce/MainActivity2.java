package com.example.fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fiannce.fragment.homefragment.ProView;

public class MainActivity2 extends AppCompatActivity {

    private ProView pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pro = (ProView) findViewById(R.id.pro);

        pro.num(30,true);
    }
}