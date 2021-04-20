package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private LinearLayout ll;
    private CommonTabLayout comm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ll = (LinearLayout) findViewById(R.id.ll);
        comm = (CommonTabLayout) findViewById(R.id.comm);
    }

    @Override
    protected void initData() {
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new MyCustomTabEntity("首页",R.drawable.bottom01,R.drawable.bottom02));
        customTabEntities.add(new MyCustomTabEntity("投资",R.drawable.bottom03,R.drawable.bottom04));
        customTabEntities.add(new MyCustomTabEntity("我的资产",R.drawable.bottom05,R.drawable.bottom06));
        customTabEntities.add(new MyCustomTabEntity("更多",R.drawable.bottom07,R.drawable.bottom08));

        HoemFragment hoemFragment = new HoemFragment();
        BlankFragment blankFragment = new BlankFragment();
        BlankFragment2 blankFragment2 = new BlankFragment2();
        BlankFragment3 blankFragment3 = new BlankFragment3();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.ll,hoemFragment);
        fragmentTransaction.add(R.id.ll,blankFragment);
        fragmentTransaction.add(R.id.ll,blankFragment2);
        fragmentTransaction.add(R.id.ll,blankFragment3);

        fragmentTransaction.show(hoemFragment);
        fragmentTransaction.hide(blankFragment);
        fragmentTransaction.hide(blankFragment2);
        fragmentTransaction.hide(blankFragment3);

        fragmentTransaction.commit();

        comm.setTabData(customTabEntities);
        comm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==0){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.show(hoemFragment);
                    fragmentTransaction1.hide(blankFragment);
                    fragmentTransaction1.hide(blankFragment2);
                    fragmentTransaction1.hide(blankFragment3);

                    fragmentTransaction1.commit();
                }else if (position==1){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.show(blankFragment);
                    fragmentTransaction1.hide(blankFragment2);
                    fragmentTransaction1.hide(blankFragment3);

                    fragmentTransaction1.commit();
                }else if (position==2){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.hide(blankFragment);
                    fragmentTransaction1.show(blankFragment2);
                    fragmentTransaction1.hide(blankFragment3);

                    fragmentTransaction1.commit();
                }else if (position==3){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.hide(blankFragment);
                    fragmentTransaction1.hide(blankFragment2);
                    fragmentTransaction1.show(blankFragment3);

                    fragmentTransaction1.commit();
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