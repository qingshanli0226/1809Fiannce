package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.fragment.HomeFragment;
import com.example.a1809fiannce.fragment.InvestFragment;
import com.example.a1809fiannce.fragment.ManyFragment;
import com.example.a1809fiannce.fragment.MyMoneyFragment;
import com.example.formwork.model.HomeBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private CommonTabLayout tab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tab = (CommonTabLayout) findViewById(R.id.tab);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        InvestFragment investFragment = new InvestFragment();
        MyMoneyFragment myMoneyFragment = new MyMoneyFragment();
        ManyFragment manyFragment = new ManyFragment();
        beginTransaction.add(R.id.main,homeFragment);
        beginTransaction.add(R.id.main,investFragment);
        beginTransaction.add(R.id.main,myMoneyFragment);
        beginTransaction.add(R.id.main,manyFragment);
        beginTransaction.show(homeFragment);
        beginTransaction.hide(investFragment);
        beginTransaction.hide(myMoneyFragment);
        beginTransaction.hide(manyFragment);
        beginTransaction.commit();
        list.add(new TabCus("主页",R.mipmap.home_true,R.mipmap.home_fase));
        list.add(new TabCus("投资",R.mipmap.invest_true,R.mipmap.invest_fase));
        list.add(new TabCus("我的资产",R.mipmap.money_true,R.mipmap.money_fase));
        list.add(new TabCus("更多",R.mipmap.many_true,R.mipmap.many_fase));
        tab.setTabData(list);

        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                FragmentTransaction beginTransaction1 = getSupportFragmentManager().beginTransaction();
                if (position==0){
                    beginTransaction1.show(homeFragment);
                    beginTransaction1.hide(investFragment);
                    beginTransaction1.hide(myMoneyFragment);
                    beginTransaction1.hide(manyFragment);
                }else if (position==1){
                    beginTransaction1.show(investFragment);
                    beginTransaction1.hide(homeFragment);
                    beginTransaction1.hide(myMoneyFragment);
                    beginTransaction1.hide(manyFragment);
                }else if (position==2){
                    beginTransaction1.show(myMoneyFragment);
                    beginTransaction1.hide(investFragment);
                    beginTransaction1.hide(homeFragment);
                    beginTransaction1.hide(manyFragment);
                }else if (position==3){
                    beginTransaction1.show(manyFragment);
                    beginTransaction1.hide(investFragment);
                    beginTransaction1.hide(myMoneyFragment);
                    beginTransaction1.hide(homeFragment);
                }
                beginTransaction1.commit();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}