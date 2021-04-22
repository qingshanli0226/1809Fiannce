package com.example.a1809fiannce;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.a1809fiannce.mainActivity.fragment.HomeFragment;
import com.example.a1809fiannce.mainActivity.fragment.InvestmentFragment;
import com.example.a1809fiannce.mainActivity.fragment.MoreFragment;
import com.example.a1809fiannce.mainActivity.fragment.MyassetsFragment;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;



@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {
    private android.widget.LinearLayout HomeLinearLayout;
    private android.widget.RadioButton homeFragment;
    private android.widget.RadioButton investmentFragment;
    private android.widget.RadioButton moreFragment;
    private android.widget.RadioButton myassetsFragment;
    private android.widget.RadioGroup homeGroup;
    private HomeFragment homeFragments = new HomeFragment();
    private InvestmentFragment investmentFragments = new InvestmentFragment();
    private MyassetsFragment myassetsFragments = new MyassetsFragment();
    private MoreFragment moreFragments = new MoreFragment();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }
    //拿到的数据
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        HomeLinearLayout = (LinearLayout) findViewById(R.id.Home_LinearLayout);
        homeFragment = (RadioButton) findViewById(R.id.home_fragment);
        investmentFragment = (RadioButton) findViewById(R.id.investment_fragment);
        moreFragment = (RadioButton) findViewById(R.id.more_fragment);
        myassetsFragment = (RadioButton) findViewById(R.id.myassets_fragment);
        homeGroup = (RadioGroup) findViewById(R.id.home_group);
        //初始化fragmen
        initFragment();
        //初始化radio
        initDrawable();
        //Radio 点击事件
        RadioClick();
    }
    //RadioClick 点击
    private void RadioClick() {
        homeGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            switch (i){
                    case R.id.home_fragment:
                        initDrawable();
                        fragmentTransaction.show(homeFragments);
                        fragmentTransaction.hide(investmentFragments);
                        fragmentTransaction.hide(myassetsFragments);
                        fragmentTransaction.hide(moreFragments);
                        fragmentTransaction.commit();
                    break;
                case R.id.investment_fragment:
                        //首页
                        Drawable drawableSearch = getResources().getDrawable(R.mipmap.bottom01);
                        drawableSearch.setBounds(0,0,100,100);
                        homeFragment.setCompoundDrawables(null,drawableSearch,null,null);
                        homeFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        Drawable drawableSearch1 = getResources().getDrawable(R.mipmap.bottom04);
                        drawableSearch1.setBounds(0,0,100,100);
                        investmentFragment.setCompoundDrawables(null,drawableSearch1,null,null);
                        investmentFragment.setTextColor(Color.parseColor("#3883D1"));
                        //我的资产
                        Drawable drawableSearch2 = getResources().getDrawable(R.mipmap.bottom05);
                        drawableSearch2.setBounds(0,0,100,100);
                        myassetsFragment.setCompoundDrawables(null,drawableSearch2,null,null);
                        myassetsFragment.setText("我的资产");
                        myassetsFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        //更多
                        Drawable drawableSearch3 = getResources().getDrawable(R.mipmap.bottom07);
                        drawableSearch3.setBounds(0,0,100,100);
                        moreFragment.setCompoundDrawables(null,drawableSearch3,null,null);
                        moreFragment.setText("更多");
                        moreFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        fragmentTransaction.hide(homeFragments);
                        fragmentTransaction.show(investmentFragments);
                        fragmentTransaction.hide(myassetsFragments);
                        fragmentTransaction.hide(moreFragments);
                        fragmentTransaction.commit();
                        break;
                    case R.id.myassets_fragment:
                        //首页
                        Drawable drawableSearchh = getResources().getDrawable(R.mipmap.bottom01);
                        drawableSearchh.setBounds(0,0,100,100);
                        homeFragment.setCompoundDrawables(null,drawableSearchh,null,null);
                        homeFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        Drawable drawableSearchin = getResources().getDrawable(R.mipmap.bottom03);
                        drawableSearchin.setBounds(0,0,100,100);
                        investmentFragment.setCompoundDrawables(null,drawableSearchin,null,null);
                        investmentFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        //我的资产
                        Drawable drawableSearchm = getResources().getDrawable(R.mipmap.bottom06);
                        drawableSearchm.setBounds(0,0,100,100);
                        myassetsFragment.setCompoundDrawables(null,drawableSearchm,null,null);
                        myassetsFragment.setText("我的资产");
                        myassetsFragment.setTextColor(Color.parseColor("#E24242"));
                        //更多
                        Drawable drawableSearchmo = getResources().getDrawable(R.mipmap.bottom07);
                        drawableSearchmo.setBounds(0,0,100,100);
                        moreFragment.setCompoundDrawables(null,drawableSearchmo,null,null);
                        moreFragment.setText("更多");
                        moreFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        fragmentTransaction.hide(homeFragments);
                        fragmentTransaction.hide(investmentFragments);
                        fragmentTransaction.show(myassetsFragments);
                        fragmentTransaction.hide(moreFragments);
                        fragmentTransaction.commit();
                        break;
                    case R.id.more_fragment:
                        //首页
                        Drawable drawable = getResources().getDrawable(R.mipmap.bottom01);
                        drawable.setBounds(0,0,100,100);
                        homeFragment.setCompoundDrawables(null,drawable,null,null);
                        homeFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        Drawable drawable1 = getResources().getDrawable(R.mipmap.bottom03);
                        drawable1.setBounds(0,0,100,100);
                        investmentFragment.setCompoundDrawables(null,drawable1,null,null);
                        investmentFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        //我的资产
                        Drawable drawable2 = getResources().getDrawable(R.mipmap.bottom05);
                        drawable2.setBounds(0,0,100,100);
                        myassetsFragment.setCompoundDrawables(null,drawable2,null,null);

                        myassetsFragment.setText("我的资产");
                        myassetsFragment.setTextColor(Color.parseColor("#6D6D6D"));
                        //更多
                        Drawable drawable3 = getResources().getDrawable(R.mipmap.bottom08);
                        drawable3.setBounds(0,0,100,100);
                        moreFragment.setCompoundDrawables(null,drawable3,null,null);
                        moreFragment.setText("更多");
                        moreFragment.setTextColor(Color.parseColor("#3883D1"));
                        fragmentTransaction.hide(homeFragments);
                        fragmentTransaction.hide(investmentFragments);
                        fragmentTransaction.hide(myassetsFragments);
                        fragmentTransaction.show(moreFragments);
                        fragmentTransaction.commit();
                        break;
            }
        });

    }


    //初始化Fragment
    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.Home_LinearLayout,homeFragments);
        fragmentTransaction.add(R.id.Home_LinearLayout,investmentFragments);
        fragmentTransaction.add(R.id.Home_LinearLayout,myassetsFragments);
        fragmentTransaction.add(R.id.Home_LinearLayout,moreFragments);
        fragmentTransaction.show(homeFragments);
        fragmentTransaction.hide(investmentFragments);
        fragmentTransaction.hide(myassetsFragments);
        fragmentTransaction.hide(moreFragments);
        fragmentTransaction.commit();
    }
    //设置图片大小和文字颜色初始化
    private void initDrawable() {
        //首页
        Drawable drawableSearch = getResources().getDrawable(R.mipmap.bottom02);
        drawableSearch.setBounds(0,0,100,100);
        homeFragment.setCompoundDrawables(null,drawableSearch,null,null);
        homeFragment.setText("首页");
        homeFragment.setTextColor(Color.parseColor("#3883D1"));
        //投资
        Drawable drawableSearch1 = getResources().getDrawable(R.mipmap.bottom03);
        drawableSearch1.setBounds(0,0,100,100);
        investmentFragment.setCompoundDrawables(null,drawableSearch1,null,null);
        investmentFragment.setText("投资");
        investmentFragment.setTextColor(Color.parseColor("#6D6D6D"));
        //我的资产
        Drawable drawableSearch2 = getResources().getDrawable(R.mipmap.bottom05);
        drawableSearch2.setBounds(0,0,100,100);
        myassetsFragment.setCompoundDrawables(null,drawableSearch2,null,null);
        myassetsFragment.setText("我的资产");
        myassetsFragment.setTextColor(Color.parseColor("#6D6D6D"));
        //更多
        Drawable drawableSearch3 = getResources().getDrawable(R.mipmap.bottom07);
        drawableSearch3.setBounds(0,0,100,100);
        moreFragment.setCompoundDrawables(null,drawableSearch3,null,null);
        moreFragment.setText("更多");
        moreFragment.setTextColor(Color.parseColor("#6D6D6D"));
    }
}
