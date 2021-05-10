package com.example.user.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.user.R;
import com.example.user.user.log.LoginFragment;
import com.example.user.user.reg.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ViewPager vp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        vp = (ViewPager) findViewById(R.id.vp);

        UserBroad userBroad = new UserBroad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("log");
        registerReceiver(userBroad,intentFilter);


        List<Fragment> list=new ArrayList<>();
        list.add(new RegisterFragment());
        list.add(new LoginFragment());
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragAdapter);

    }
    class UserBroad extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("log")){
                vp.setCurrentItem(1);
            }
        }
    }
}