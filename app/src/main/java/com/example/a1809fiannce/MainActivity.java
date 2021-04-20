package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.a1809fiannce.adapter.FragmentAdapter;
import com.example.a1809fiannce.home.HoemFragment;
import com.example.a1809fiannce.tab.MyCustomTabEntity;
import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity{


    private LinearLayout actHomeLl;
    private CommonTabLayout actHomeCt;

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

        HoemFragment hoemFragment = new HoemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.act_home_ll,hoemFragment);
        fragmentTransaction.commit();

        actHomeCt.setTabData(customTabEntities);
        actHomeCt.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==0){

                }else if (position==1){

                }else if (position==2){

                }else if (position==3){

                }
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
        actHomeLl = (LinearLayout) findViewById(R.id.act_home_ll);
        actHomeCt = (CommonTabLayout) findViewById(R.id.act_home_ct);
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