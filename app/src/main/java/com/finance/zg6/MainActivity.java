package com.finance.zg6;


import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.framework.manager.CacheManager;
import com.finance.net.bean.HomeBean;
import com.finance.zg.R;
import com.finance.zg6.ui.HomeFragment;
import com.finance.zg6.ui.investment.InvestmentFragment;
import com.finance.zg6.ui.MoreFragment;
import com.finance.zg6.ui.MyAssetsFragment;
import com.finance.zg6.ui.bean.CustomBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private CommonTabLayout common;
    private android.widget.LinearLayout li;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        LogUtils.json(homeBean);

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new CustomBean("首页",R.mipmap.bottom01,R.mipmap.bottom02));
        customTabEntities.add(new CustomBean("投资",R.mipmap.bottom03,R.mipmap.bottom04));
        customTabEntities.add(new CustomBean("我的资产",R.mipmap.bottom05,R.mipmap.bottom06));
        customTabEntities.add(new CustomBean("更多",R.mipmap.bottom07,R.mipmap.bottom08));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        InvestmentFragment investmentFragment = new InvestmentFragment();
        MyAssetsFragment myAssetsFragment = new MyAssetsFragment();
        MoreFragment moreFragment = new MoreFragment();

        fragmentTransaction.add(R.id.li,homeFragment);
        fragmentTransaction.add(R.id.li,investmentFragment);
        fragmentTransaction.add(R.id.li,myAssetsFragment);
        fragmentTransaction.add(R.id.li,moreFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(investmentFragment);
        fragmentTransaction.hide(myAssetsFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();

        common.setTabData(customTabEntities);
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                switch (position){
                    case 0:
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(investmentFragment);
                        fragmentTransaction.hide(myAssetsFragment);
                        fragmentTransaction.hide(moreFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.hide(homeFragment);
                        fragmentTransaction2.show(investmentFragment);
                        fragmentTransaction2.hide(myAssetsFragment);
                        fragmentTransaction2.hide(moreFragment);
                        fragmentTransaction2.commit();
                        break;
                    case 2:
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.hide(homeFragment);
                        fragmentTransaction3.hide(investmentFragment);
                        fragmentTransaction3.show(myAssetsFragment);
                        fragmentTransaction3.hide(moreFragment);
                        fragmentTransaction3.commit();
                        break;
                    case 3:
                        FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.hide(homeFragment);
                        fragmentTransaction4.hide(investmentFragment);
                        fragmentTransaction4.hide(myAssetsFragment);
                        fragmentTransaction4.show(moreFragment);
                        fragmentTransaction4.commit();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initView() {

        common = (CommonTabLayout) findViewById(R.id.common);
        li = (LinearLayout) findViewById(R.id.li);
    }

    private long i;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - i >2000){
                Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                i =System.currentTimeMillis();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}