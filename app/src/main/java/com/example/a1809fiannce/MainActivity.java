package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.a1809fiannce.adapter.FragmentAdapter;
import com.example.a1809fiannce.home.HoemFragment;
import com.example.a1809fiannce.invest.InvestFragment;
import com.example.a1809fiannce.tab.MyCustomTabEntity;
import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity{

    private CommonTabLayout actHomeCt;
    private ViewPager actHomeVp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new MyCustomTabEntity("首页",R.mipmap.bottom01,R.mipmap.bottom02));
        customTabEntities.add(new MyCustomTabEntity("投资",R.mipmap.bottom03,R.mipmap.bottom04));
        customTabEntities.add(new MyCustomTabEntity("我的资产",R.mipmap.bottom05,R.mipmap.bottom06));
        customTabEntities.add(new MyCustomTabEntity("更多",R.mipmap.bottom07,R.mipmap.bottom08));


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HoemFragment());
        fragments.add(new InvestFragment());
        fragments.add(new HoemFragment());
        fragments.add(new HoemFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        actHomeVp.setAdapter(fragmentAdapter);

        actHomeVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        actHomeVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                actHomeCt.setCurrentTab(position);
            }
        });
        actHomeCt.setTabData(customTabEntities);
        actHomeCt.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                actHomeVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });



    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        actHomeCt = (CommonTabLayout) findViewById(R.id.act_home_ct);
        actHomeVp = (ViewPager) findViewById(R.id.act_home_vp);
    }


    private long i;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - i >2000){
                Toast.makeText(this, "再按一次结束", Toast.LENGTH_SHORT).show();
                i =System.currentTimeMillis();
                return true;
            }else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}