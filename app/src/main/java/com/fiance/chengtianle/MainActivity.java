package com.fiance.chengtianle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiance.chengtianle.Adapter.MyVp;
import com.fiance.chengtianle.Fragment.fourFragment;
import com.fiance.chengtianle.Fragment.oneFragment;
import com.fiance.chengtianle.Fragment.threeFragment;
import com.fiance.chengtianle.Fragment.twoFragment;
import com.fiance.user.UserActivity;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {


    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new oneFragment());
        list.add(new twoFragment());
        list.add(new threeFragment());
        list.add(new fourFragment());

        ArrayList<String> title = new ArrayList<>();
        title.add("首页");
        title.add("投资");
        title.add("我的资产");
        title.add("更多");

        MyVp myVp = new MyVp(getSupportFragmentManager(), list, title);
        vp.setAdapter(myVp);
        tab.setupWithViewPager(vp);




    }

    private void initView() {

        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
    }

}