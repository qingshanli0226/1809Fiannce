package com.fiannce.zhaoyuzan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.net.mode.CommonBean;
import com.fiannce.zhaoyuzan.adapter.CommonAdapter;
import com.fiannce.zhaoyuzan.fragment.MoreFragment;
import com.fiannce.zhaoyuzan.fragment.HomeFragment;
import com.fiannce.zhaoyuzan.fragment.MineFragment;
import com.fiannce.zhaoyuzan.fragment.InvestFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    private List<Fragment> list = new ArrayList<>();
    private ViewPager viewPager;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout commonTabLayout;
    private CommonAdapter commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.vp);
        commonTabLayout = findViewById(R.id.common);

        list.add(new HomeFragment());
        list.add(new InvestFragment());
        list.add(new MineFragment());
        list.add(new MoreFragment());
        commonAdapter = new CommonAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(commonAdapter);

        mTabEntities.add(new CommonBean("首页",R.drawable.bottom02,R.drawable.bottom01));
        mTabEntities.add(new CommonBean("投资",R.drawable.bottom04,R.drawable.bottom03));
        mTabEntities.add(new CommonBean("我的资产",R.drawable.bottom06,R.drawable.bottom05));
        mTabEntities.add(new CommonBean("更多",R.drawable.bottom08,R.drawable.bottom07));
        commonTabLayout.setTabData(mTabEntities);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                commonTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int i) {

                viewPager.setCurrentItem(i);
            }

            @Override
            public void onTabReselect(int i) {

            }
        });

    }
}
