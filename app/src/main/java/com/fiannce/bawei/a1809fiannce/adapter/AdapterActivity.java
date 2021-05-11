package com.fiannce.bawei.a1809fiannce.adapter;

import android.Manifest;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.fiannce.bawei.a1809fiannce.R;

public class AdapterActivity extends AppCompatActivity {
    private int value = 5;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);


        //凡是版本适配，需要if条件，确保该方法是在有这个API的系统上运行的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 10);
        }
        value = 9;
        for(int i = 0; i < 10;i++){
            Log.d("LQS I = ", i+"");
        }

        Log.d("LQS", "BUTON SIZE: " + getResources().getDimension(R.dimen.button_size) + " value = " + value);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
