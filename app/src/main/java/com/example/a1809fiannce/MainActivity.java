package com.example.a1809fiannce;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a1809fiannce.bean.MyCustomTabEntity;
import com.example.a1809fiannce.home.HomeFragment;
import com.example.a1809fiannce.investment.InvestmentFragment;
import com.example.a1809fiannce.more.MoreFragment;
import com.example.a1809fiannce.myAssets.MyAssetsFragment;
import com.example.framework.BaseActivity;

import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.mode.LoginBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private LinearLayout ll;
    private CommonTabLayout comm;
    private HomeFragment homeFragment;
    private InvestmentFragment investmentFragment;
    private MyAssetsFragment myAssetsFragment;
    private MoreFragment moreFragment;

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
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.customTab_name1),R.drawable.bottom01,R.drawable.bottom02));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.customTab_name2),R.drawable.bottom03,R.drawable.bottom04));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.customTab_name3),R.drawable.bottom05,R.drawable.bottom06));
        customTabEntities.add(new MyCustomTabEntity(getResources().getString(R.string.customTab_name4),R.drawable.bottom07,R.drawable.bottom08));

        homeFragment = new HomeFragment();
        investmentFragment = new InvestmentFragment();
        myAssetsFragment = new MyAssetsFragment();
        moreFragment = new MoreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.ll,homeFragment);
        fragmentTransaction.add(R.id.ll,investmentFragment);
        fragmentTransaction.add(R.id.ll,myAssetsFragment);
        fragmentTransaction.add(R.id.ll,moreFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(investmentFragment);
        fragmentTransaction.hide(myAssetsFragment);
        fragmentTransaction.hide(moreFragment);

        fragmentTransaction.commit();

        comm.setTabData(customTabEntities);
        comm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==0){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.show(homeFragment);
                    fragmentTransaction1.hide(investmentFragment);
                    fragmentTransaction1.hide(myAssetsFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                }else if (position==1){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(homeFragment);
                    fragmentTransaction1.show(investmentFragment);
                    fragmentTransaction1.hide(myAssetsFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                }else if (position==2){

                    LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
                    if (loginBean==null){
                        FiannceArouter.getInstance().getIUserInterface().openLoginActivity(MainActivity.this,null);
                    }

                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(homeFragment);
                    fragmentTransaction1.hide(investmentFragment);
                    fragmentTransaction1.show(myAssetsFragment);
                    fragmentTransaction1.hide(moreFragment);

                    fragmentTransaction1.commit();
                }else if (position==3){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction1.hide(homeFragment);
                    fragmentTransaction1.hide(investmentFragment);
                    fragmentTransaction1.hide(myAssetsFragment);
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

    private long i;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - i >2000){
                Toast.makeText(this, getResources().getString(R.string.Exit), Toast.LENGTH_SHORT).show();
                i =System.currentTimeMillis();
                return true;
            }else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        comm.setCurrentTab(0);

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

        fragmentTransaction1.show(homeFragment);
        fragmentTransaction1.hide(investmentFragment);
        fragmentTransaction1.hide(myAssetsFragment);
        fragmentTransaction1.hide(moreFragment);

        fragmentTransaction1.commit();
    }

    @Override
    public void onConnected() {
        super.onConnected();
        Toast.makeText(this, "当前有网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Toast.makeText(this, "当前没有网络", Toast.LENGTH_SHORT).show();
    }
}