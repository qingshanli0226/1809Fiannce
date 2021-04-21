package com.example.gitproject;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.gitproject.bean.CustomBean;
import com.example.gitproject.fragment.HomeFragment;
import com.example.gitproject.fragment.MineFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {


    private RadioGroup mainRadioGroup;

    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> tabEntitys;

    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    public void initView() {
        tabEntitys = new ArrayList<>();
        common = (CommonTabLayout) findViewById(R.id.common);
    }

    @Override
    public void initPresenter() {

    }

    HomeFragment homeFragment1 = new HomeFragment();
    MineFragment mineFragment2 = new MineFragment();
    MineFragment mineFragment3 = new MineFragment();
    MineFragment mineFragment4 = new MineFragment();

    @Override
    public void initData() {

        fragmentManager();

        addData();
        //判断选择的那个
        common.setTabData(tabEntitys);
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (position) {
                    case 0:
                        fragmentTransaction.show(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case 1:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.show(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case 2:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.show(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case 3:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.show(mineFragment4);
                        break;
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });






    }

    private void addData() {
        tabEntitys.add(new CustomBean("首页",R.drawable.bottom02,R.drawable.bottom01));
        tabEntitys.add(new CustomBean("首页",R.drawable.bottom04,R.drawable.bottom03));
        tabEntitys.add(new CustomBean("首页",R.drawable.bottom06,R.drawable.bottom05));
        tabEntitys.add(new CustomBean("首页",R.drawable.bottom08,R.drawable.bottom07));
    }

    private void fragmentManager() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_linear, homeFragment1);
        fragmentTransaction.add(R.id.main_linear, mineFragment2);
        fragmentTransaction.add(R.id.main_linear, mineFragment3);
        fragmentTransaction.add(R.id.main_linear, mineFragment4);
        fragmentTransaction.hide(mineFragment2);
        fragmentTransaction.hide(mineFragment3);
        fragmentTransaction.hide(mineFragment4);
        fragmentTransaction.commit();
    }


}