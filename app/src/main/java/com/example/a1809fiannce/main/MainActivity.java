package com.example.a1809fiannce.main;

import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.main.home.HoemFragment;
import com.example.a1809fiannce.main.invest.InvestFragment;
import com.example.a1809fiannce.main.more.MoreFragment;
import com.example.a1809fiannce.main.property.PropertyFragment;
import com.example.a1809fiannce.tab.MyCustomTabEntity;
import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private CommonTabLayout actHomeCt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.frag_home), R.mipmap.bottom01, R.mipmap.bottom02));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.investment), R.mipmap.bottom03, R.mipmap.bottom04));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.my_assets), R.mipmap.bottom05, R.mipmap.bottom06));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.even_more), R.mipmap.bottom07, R.mipmap.bottom08));


        HoemFragment hoemFragment = new HoemFragment();
        InvestFragment investFragment = new InvestFragment();
        PropertyFragment propertyFragment = new PropertyFragment();
        MoreFragment moreFragment = new MoreFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.act_home_ll, hoemFragment);
        fragmentTransaction.add(R.id.act_home_ll, investFragment);
        fragmentTransaction.add(R.id.act_home_ll, propertyFragment);
        fragmentTransaction.add(R.id.act_home_ll, moreFragment);

        fragmentTransaction.hide(investFragment);
        fragmentTransaction.hide(propertyFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.show(hoemFragment);

        fragmentTransaction.commit();


        actHomeCt.setTabData(customTabEntities);
        actHomeCt.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                if (position == 0) {
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.show(hoemFragment);
                    fragmentTransaction1.hide(investFragment);
                    fragmentTransaction1.hide(propertyFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                } else if (position == 1) {
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.show(investFragment);
                    fragmentTransaction1.hide(propertyFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                } else if (position == 2) {
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.hide(investFragment);
                    fragmentTransaction1.show(propertyFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                } else if (position == 3) {
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(hoemFragment);
                    fragmentTransaction1.hide(investFragment);
                    fragmentTransaction1.hide(propertyFragment);
                    fragmentTransaction1.show(moreFragment);
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

    @Override
    protected void initView() {
        actHomeCt = (CommonTabLayout) findViewById(R.id.act_home_ct);
    }


    private long i;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - i > 2000) {
                Toast.makeText(this, "再按一次结束", Toast.LENGTH_SHORT).show();
                i = System.currentTimeMillis();
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}