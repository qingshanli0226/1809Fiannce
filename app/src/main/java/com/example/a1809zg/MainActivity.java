package com.example.a1809zg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.a1809zg.fragment.CustomBean;
import com.example.a1809zg.fragment.HomeFragment;
import com.example.a1809zg.fragment.InvestFragment;
import com.example.a1809zg.fragment.MineFragment;
import com.example.a1809zg.fragment.MoreFragment;
import com.example.frame.CommonConstant;
import com.example.frame.FrameArouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> data = new ArrayList<>();
    private FrameLayout line;
    private CommonTabLayout com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        Intent intent = new Intent();
////        intent.setAction("aa");
////        startActivity(intent);
//        ARouter.getInstance()
//                .build("/a/aa")
//                .withString("name","123")
//                .navigation(MainActivity.this);
        initView();
        FrameArouter.getInstance().registerpath(CommonConstant.APP_MAIN_PATH,MainActivity.class);



        ArrayList<CustomTabEntity> list = new ArrayList<>();
        list.add(new CustomBean("首页", R.mipmap.room, R.mipmap.room_sele));
        list.add(new CustomBean("投资", R.mipmap.money, R.mipmap.money_sele));
        list.add(new CustomBean("我的资产", R.mipmap.mine, R.mipmap.mine_sele));
        list.add(new CustomBean("更多", R.mipmap.more, R.mipmap.more_sele));

        data.add(new HomeFragment());
        data.add(new InvestFragment());
        data.add(new MineFragment());
        data.add(new MoreFragment());

        com.setTabData(list, this, R.id.line, data);
        com.setTextSelectColor(Color.RED);
        com.setTextUnselectColor(Color.BLACK);




    }




    private void initView() {
        line = findViewById(R.id.line);
        com = findViewById(R.id.com);

    }
}