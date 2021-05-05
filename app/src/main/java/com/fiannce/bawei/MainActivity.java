package com.fiannce.bawei;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.fragment.homeFragment.HomeFragment;
import com.fiannce.bawei.fragment.investFragment.InvestFragment;
import com.fiannce.bawei.fragment.mineFragment.MineFragment;
import com.fiannce.bawei.fragment.moreFragment.MoreFragment;
import com.fiannce.net.mode.CommonBean;
import com.fiannce.zhaoyuzan.R;
import com.fiannce.bawei.mainActivity.adapter.CommonAdapter;
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
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestPermissions(new String[]{
                "android.permission.CALL_PHONE"
        },100);
        viewPager = findViewById(R.id.vp);
        commonTabLayout = findViewById(R.id.common);

        list.add(new HomeFragment());
        list.add(new InvestFragment());
        list.add(new MineFragment());
        list.add(new MoreFragment());
        commonAdapter = new CommonAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(commonAdapter);

        mTabEntities.add(new CommonBean(getString(R.string.activity_home),R.drawable.bottom02,R.drawable.bottom01));
        mTabEntities.add(new CommonBean(getString(R.string.activity_invest),R.drawable.bottom04,R.drawable.bottom03));
        mTabEntities.add(new CommonBean(getString(R.string.activity_mine),R.drawable.bottom06,R.drawable.bottom05));
        mTabEntities.add(new CommonBean(getString(R.string.activity_more),R.drawable.bottom08,R.drawable.bottom07));
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, getString(R.string.exitApp), Toast.LENGTH_SHORT).show();
                //System.currentTimeMillis()系统当前时间
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
