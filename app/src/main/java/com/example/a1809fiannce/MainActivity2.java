package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tab = (CommonTabLayout) findViewById(R.id.tab);

//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        InvestFragment investFragment = new InvestFragment();
//        MyMoneyFragment myMoneyFragment = new MyMoneyFragment();
//        ManyFragment manyFragment = new ManyFragment();
//
//        beginTransaction.add(R.id.main,homeFragment);
//        beginTransaction.add(R.id.main,investFragment);
//        beginTransaction.add(R.id.main,myMoneyFragment);
//        beginTransaction.add(R.id.main,manyFragment);
//
//        beginTransaction.show(homeFragment);
//        beginTransaction.hide(investFragment);
//        beginTransaction.hide(myMoneyFragment);
//        beginTransaction.hide(manyFragment);
//        beginTransaction.commit();

        list.add(new TabCus("主页",R.mipmap.bottom02,R.mipmap.bottom01));
        list.add(new TabCus("投资",R.mipmap.bottom04,R.mipmap.bottom03));
        list.add(new TabCus("我的资产",R.mipmap.bottom06,R.mipmap.bottom05));
        list.add(new TabCus("更多",R.mipmap.bottom08,R.mipmap.bottom07));
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new MyMoneyFragment());
        fragmentList.add(new ManyFragment());
        tab.setTabData(list,this,R.id.main,fragmentList);
//
//        tab.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                FragmentTransaction beginTransaction1 = getSupportFragmentManager().beginTransaction();
//                if (position==0){
//                    Toast.makeText(MainActivity2.this, "0", Toast.LENGTH_SHORT).show();
//                    beginTransaction1.show(homeFragment);
//                    beginTransaction1.hide(investFragment);
//                    beginTransaction1.hide(myMoneyFragment);
//                    beginTransaction1.hide(manyFragment);
//                }else if (position==1){
//                    Toast.makeText(MainActivity2.this, "1", Toast.LENGTH_SHORT).show();
//                    beginTransaction1.show(investFragment);
//                    beginTransaction1.hide(homeFragment);
//                    beginTransaction1.hide(myMoneyFragment);
//                    beginTransaction1.hide(manyFragment);
//                }else if (position==2){
//                    Toast.makeText(MainActivity2.this, "2", Toast.LENGTH_SHORT).show();
//                    beginTransaction1.show(myMoneyFragment);
//                    beginTransaction1.hide(investFragment);
//                    beginTransaction1.hide(homeFragment);
//                    beginTransaction1.hide(manyFragment);
//                }else if (position==3){
//                    Toast.makeText(MainActivity2.this, "3", Toast.LENGTH_SHORT).show();
//                    beginTransaction1.show(manyFragment);
//                    beginTransaction1.hide(investFragment);
//                    beginTransaction1.hide(myMoneyFragment);
//                    beginTransaction1.hide(homeFragment);
//                }
//                beginTransaction1.commit();
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
    }
}