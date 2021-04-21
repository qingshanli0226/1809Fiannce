package com.example.a1809zg;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.a1809zg.fragment.BlankFragment;
import com.example.a1809zg.fragment.BlankFragment2;
import com.example.a1809zg.fragment.BlankFragment3;
import com.example.a1809zg.fragment.BlankFragment4;
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



        ArrayList<CustomTabEntity> list = new ArrayList<>();
        list.add(new CustomBean("首页", R.mipmap.room, R.mipmap.room_sele));
        list.add(new CustomBean("投资", R.mipmap.money, R.mipmap.money_sele));
        list.add(new CustomBean("我的资产", R.mipmap.mine, R.mipmap.mine_sele));
        list.add(new CustomBean("更多", R.mipmap.more, R.mipmap.more_sele));

        data.add(new BlankFragment());
        data.add(new BlankFragment2());
        data.add(new BlankFragment3());
        data.add(new BlankFragment4());

        com.setTabData(list, this, R.id.line, data);
        com.setTextSelectColor(Color.RED);
        com.setTextUnselectColor(Color.BLACK);



    }

    private void initView() {
        line = findViewById(R.id.line);
        com = findViewById(R.id.com);
    }
}