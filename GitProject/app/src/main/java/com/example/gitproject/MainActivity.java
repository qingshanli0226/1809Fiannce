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



    private ArrayList<CustomTabEntity> tabEntitys;
    private RadioGroup mainRadiogroup;
    private RadioButton oneBtn;
    private RadioButton twoBtn;
    private RadioButton threeBtn;
    private RadioButton fourBtn;

    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    public void initView() {
        tabEntitys = new ArrayList<>();
        mainRadiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        oneBtn = (RadioButton) findViewById(R.id.one_btn);
        twoBtn = (RadioButton) findViewById(R.id.two_btn);
        threeBtn = (RadioButton) findViewById(R.id.three_btn);
        fourBtn = (RadioButton) findViewById(R.id.four_btn);
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

        //选择
        mainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.one_btn:
                        fragmentTransaction.show(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case R.id.two_btn:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.show(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case R.id.three_btn:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.show(mineFragment3);
                        fragmentTransaction.hide(mineFragment4);
                        break;
                    case R.id.four_btn:
                        fragmentTransaction.hide(homeFragment1);
                        fragmentTransaction.hide(mineFragment2);
                        fragmentTransaction.hide(mineFragment3);
                        fragmentTransaction.show(mineFragment4);
                        break;
                }
                fragmentTransaction.commit();
            }
        });





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