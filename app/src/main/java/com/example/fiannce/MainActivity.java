package com.example.fiannce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.fiannce.adapter.MyTab;
import com.example.fiannce.fragment.HomeFragment;
import com.example.fiannce.fragment.InvestFragment;
import com.example.fiannce.fragment.ManyFragment;
import com.example.fiannce.fragment.MyMoneyFragment;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private FrameLayout frame;
    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private ManyFragment manyFragment;
    private MyMoneyFragment myMoneyFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        frame = (FrameLayout) findViewById(R.id.frame);
        common = (CommonTabLayout) findViewById(R.id.common);

        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        manyFragment = new ManyFragment();
        myMoneyFragment = new MyMoneyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame,homeFragment)
                .add(R.id.frame,investFragment)
                .add(R.id.frame,manyFragment)
                .add(R.id.frame,myMoneyFragment)
                .commit();
        showFragment(homeFragment);

        customTabEntities.add(new MyTab("首页",R.mipmap.bottom02,R.mipmap.bottom01));
        customTabEntities.add(new MyTab("投资",R.mipmap.bottom04,R.mipmap.bottom03));
        customTabEntities.add(new MyTab("我的账户",R.mipmap.bottom06,R.mipmap.bottom05));
        customTabEntities.add(new MyTab("更多",R.mipmap.bottom08,R.mipmap.bottom07));

        common.setTabData(customTabEntities);

        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0){
                    showFragment(homeFragment);
                }else if (position == 1){
                    showFragment(investFragment);
                }else if (position == 2){
                    showFragment(manyFragment);
                }else if (position == 3){
                    showFragment(myMoneyFragment);
                }
                common.hideMsg(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initView() {

    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(investFragment)
                .hide(manyFragment)
                .hide(myMoneyFragment)
                .show(fragment)
                .commit();
    }
}