package com.finance.zg6.main;


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
import com.finance.zg6.bean.CustomBean;
import com.finance.zg6.main.home.HomeFragment;
import com.finance.zg6.main.investment.InvestmentFragment;
import com.finance.zg6.main.more.MoreFragment;
import com.finance.zg6.main.myassets.MyAssetsFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

@Route(path ="/main/MainActivity")
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
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_home),R.mipmap.bottom02,R.mipmap.bottom01));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_investment),R.mipmap.bottom04,R.mipmap.bottom03));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_my_assets),R.mipmap.bottom06,R.mipmap.bottom05));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_more),R.mipmap.bottom08,R.mipmap.bottom07));

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
                Toast.makeText(this, getString(R.string.mianActivity_exit_the_program_toast), Toast.LENGTH_SHORT).show();
                i =System.currentTimeMillis();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}