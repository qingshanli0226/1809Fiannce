package com.example.gitproject;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.gitproject.invest.InvestFragment;
import com.example.gitproject.home.HomeFragment;
import com.example.gitproject.mine.MineFragment;
import com.example.gitproject.more.MoreFragment;
import com.example.gitproject.welcome.WelcomeActivity;
import com.example.net.bean.LoginBean;
import com.example.user.login.LoginActivity;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Route(path = CommonConstant.APP_MAIN_PATH)
public class MainActivity extends BaseActivity implements CacheUserManager.ILoginChange {


    private ArrayList<CustomTabEntity> tabEntitys;
    private RadioGroup mainRadiogroup;
    private RadioButton oneBtn;
    private RadioButton twoBtn;
    private RadioButton threeBtn;
    private RadioButton fourBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void initView() {
        //沉浸式
//        BarUtils.transparentStatusBar(this);
        tabEntitys = new ArrayList<>();
        mainRadiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        oneBtn = (RadioButton) findViewById(R.id.one_btn);
        twoBtn = (RadioButton) findViewById(R.id.two_btn);
        threeBtn = (RadioButton) findViewById(R.id.three_btn);
        fourBtn = (RadioButton) findViewById(R.id.four_btn);
    }

    @Override
    public void initPresenter() {

    }

    HomeFragment homeFragment = new HomeFragment();
    InvestFragment investFragment = new InvestFragment();
    MineFragment mineFragment = new MineFragment();
    MoreFragment moreFragment = new MoreFragment();

    @Override
    public void initData() {

        fragmentManager();

        oneBtn.setChecked(true);

        //选择
        mainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.one_btn:
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(investFragment);
                        fragmentTransaction.hide(mineFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.two_btn:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(investFragment);
                        fragmentTransaction.hide(mineFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.three_btn:
                        //判断是否登录过
                        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
                        if (loginBean != null) {
                            fragmentTransaction.hide(homeFragment);
                            fragmentTransaction.hide(investFragment);
                            fragmentTransaction.show(mineFragment);
                            fragmentTransaction.hide(moreFragment);
                        } else {
                            //FrameArouter
                            FrameArouter.getInstance().build(CommonConstant.USER_LOGIN_PATH).navigation();
                        }
                        break;
                    case R.id.four_btn:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(investFragment);
                        fragmentTransaction.hide(mineFragment);
                        fragmentTransaction.show(moreFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Intent intent1 = FrameArouter.getInstance().getIntent();
        setIntent(intent1);

        oneBtn.setChecked(true);
    }

    private void fragmentManager() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_linear, homeFragment);
        fragmentTransaction.add(R.id.main_linear, investFragment);
        fragmentTransaction.add(R.id.main_linear, mineFragment);
        fragmentTransaction.add(R.id.main_linear, moreFragment);
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(investFragment);
        fragmentTransaction.hide(mineFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();


    }


    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onLoginChange: "+loginBean);

    }
}