package com.fiannce.bawei.a1809fiannce;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DebugActivity extends AppCompatActivity {

    private int sum = 0;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(i =0; i < 5;i++) {
                    sum=sum+i;
                }
            }
        }).start();

        int subValue = subValue(sum);

        Log.d("LQS", subValue+"");
    }


    private int subValue(int sum) {
        int value = 1;

        sum = sum - value;

        return sum;
    }
}
