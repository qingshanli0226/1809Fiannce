package com.example.designed;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.designed.adapter.FragmentAdapter;
import com.example.designed.fragment.BlankFragment;
import com.example.designed.fragment.BlankFragment2;
import com.example.designed.fragment.BlankFragment3;
import com.example.designed.fragment.BlankFragment4;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> list = new ArrayList<>();
    List<String> title = new ArrayList<>();
    private ViewPager vp;
    private TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.add("首页");
        title.add("投资");
        title.add("我的资产");
        title.add("更多");

        list.add(new BlankFragment());
        list.add(new BlankFragment2());
        list.add(new BlankFragment3());
        list.add(new BlankFragment4());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list, title);
        vp.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(vp);



    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
