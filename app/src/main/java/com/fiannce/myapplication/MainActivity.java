package com.fiannce.myapplication;


import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.fiannce.myapplication.fragment.more.MoreFragment;
import com.fiannce.myapplication.fragment.property.PropertyFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.myapplication.fragment.home.HomeFragment;
import com.fiannce.myapplication.fragment.investment.InvestmentFragment;
import com.fiannce.net.mode.HomeBean;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {


    private android.widget.RadioGroup group;
    private android.widget.RadioButton homeRadio;
    private android.widget.RadioButton investmentRadio;
    private android.widget.RadioButton propertyRadio;
    private android.widget.RadioButton moreRadio;
    HomeFragment homeFragment;
    InvestmentFragment investmentFragment;
    MoreFragment moreFragment;
    PropertyFragment propertyFragment;

    @Override
    protected void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        homeRadio = (RadioButton) findViewById(R.id.home_radio);
        investmentRadio = (RadioButton) findViewById(R.id.investment_radio);
        propertyRadio = (RadioButton) findViewById(R.id.property_radio);
        moreRadio = (RadioButton) findViewById(R.id.more_radio);

        homeFragment = new HomeFragment();
        investmentFragment = new InvestmentFragment();
        propertyFragment = new PropertyFragment();
        moreFragment = new MoreFragment();

    }


    @Override
    protected void initData() {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_ll, homeFragment);
        fragmentTransaction.add(R.id.main_ll, investmentFragment);
        fragmentTransaction.add(R.id.main_ll, propertyFragment);
        fragmentTransaction.add(R.id.main_ll, moreFragment);
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(investmentFragment);
        fragmentTransaction.hide(propertyFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();



        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_radio:
                        FragmentManager supportFragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(investmentFragment);
                        fragmentTransaction.hide(propertyFragment);
                        fragmentTransaction.hide(moreFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.investment_radio:
                        FragmentManager supportFragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = supportFragmentManager2.beginTransaction();
                        fragmentTransaction2.hide(homeFragment);
                        fragmentTransaction2.show(investmentFragment);
                        fragmentTransaction2.hide(propertyFragment);
                        fragmentTransaction2.hide(moreFragment);
                        fragmentTransaction2.commit();
                        break;
                    case R.id.property_radio:
                        FragmentManager supportFragmentManager3 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction3 = supportFragmentManager3.beginTransaction();
                        fragmentTransaction3.hide(homeFragment);
                        fragmentTransaction3.hide(investmentFragment);
                        fragmentTransaction3.show(propertyFragment);
                        fragmentTransaction3.hide(moreFragment);
                        fragmentTransaction3.commit();
                        break;
                    case R.id.more_radio:
                        FragmentManager supportFragmentManager4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction4 = supportFragmentManager4.beginTransaction();
                        fragmentTransaction4.hide(homeFragment);
                        fragmentTransaction4.hide(investmentFragment);
                        fragmentTransaction4.hide(propertyFragment);
                        fragmentTransaction4.show(moreFragment);
                        fragmentTransaction4.commit();
                        break;
                }
            }
        });


    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }


    @Override
    protected void initPresenter() {

    }
}
