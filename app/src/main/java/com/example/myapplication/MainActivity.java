package com.example.myapplication;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.demo.Demo;
import com.example.myapplication.fragment.home.HomeFragment;
import com.example.myapplication.fragment.invest.InvestFragment;
import com.example.myapplication.fragment.more.MoreFragment;
import com.example.myapplication.fragment.mymoney.MymoneyFragment;

@Route(path = Demo.AROUTE_PATH)
public class MainActivity extends BaseActivity {

    private android.widget.RadioButton btnHome;
    private android.widget.RadioButton btnMoney;
    private android.widget.RadioButton btnMymoney;
    private RadioButton btnMore;
    private RadioGroup group;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MymoneyFragment mymoneyFragment;
    private MoreFragment moreFragment;

    @Override
    protected void initData() {

        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        mymoneyFragment = new MymoneyFragment();
        moreFragment = new MoreFragment();

        btnHome.setChecked(true);
        fragmentManger();


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_home:
//                        Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(investFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);

                        break;
                    case R.id.btn_money:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(investFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.btn_mymoney:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(investFragment);
                        fragmentTransaction.show(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.btn_more:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(investFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.show(moreFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    public void fragmentManger(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_linear,homeFragment);
        fragmentTransaction.add(R.id.main_linear, investFragment);
        fragmentTransaction.add(R.id.main_linear,mymoneyFragment);
        fragmentTransaction.add(R.id.main_linear,moreFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(investFragment);
        fragmentTransaction.hide(mymoneyFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();


    }

    @Override
    protected void initView() {
//透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
//        banner = (Banner) findViewById(R.id.banner);
//        tab = (TabLayout) findViewById(R.id.tab);
//        vp = (ViewPager) findViewById(R.id.vp);
        btnHome = (RadioButton) findViewById(R.id.btn_home);
        btnMoney = (RadioButton) findViewById(R.id.btn_money);
        btnMymoney = (RadioButton) findViewById(R.id.btn_mymoney);
        btnMore = (RadioButton) findViewById(R.id.btn_more);
        group = (RadioGroup) findViewById(R.id.group);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private long keybackTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - keybackTime > 2000){
                keybackTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            }else {
                finish();
            }
        }else if (event.getKeyCode() == KeyEvent.KEYCODE_HOME){
            return false;
        }

        return true;
    }
}