package com.fiance.chengtianle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiance.chengtianle.Adapter.MyVp;
import com.fiance.chengtianle.Fragment.MainFragment;

import com.fiance.chengtianle.Fragment.InvestorFragment;
import com.fiance.chengtianle.Fragment.MineFragment;
import com.fiance.chengtianle.Fragment.MoreFragment;
import com.google.android.material.tabs.TabLayout;

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
        list.add(new MainFragment());
        list.add(new InvestorFragment());
        list.add(new MineFragment());
        list.add(new MoreFragment());

        ArrayList<String> title = new ArrayList<>();
        title.add("首页");
        title.add("投资");
        title.add("我的资产");
        title.add("更多");

        MyVp myVp = new MyVp(getSupportFragmentManager(), list, title);
        vp.setAdapter(myVp);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setIcon(R.drawable.bottom02);
        tab.getTabAt(1).setIcon(R.drawable.qb);
        tab.getTabAt(2).setIcon(R.drawable.bottom06);
        tab.getTabAt(3).setIcon(R.drawable.bottom08);



    }

    private void initView() {

        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
    }

}