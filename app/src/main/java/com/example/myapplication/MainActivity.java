package com.example.myapplication;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.demo.Demo;
import com.example.framework.manager.FiannceUserManager;
import com.example.myapplication.fragment.home.HomeFragment;
import com.example.myapplication.fragment.invest.InvestFragment;
import com.example.myapplication.fragment.more.MoreFragment;
import com.example.myapplication.fragment.mymoney.MymoneyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        EventBus.getDefault().register(this);
        btnHome.setChecked(true);

        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        mymoneyFragment = new MymoneyFragment();
        moreFragment = new MoreFragment();

        fragmentManger();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_home:
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

                        SharedPreferences login1 = getSharedPreferences("login", MODE_PRIVATE);
                        boolean is_login = login1.getBoolean("is_login", false);
                        if (is_login){

                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setIcon(R.drawable.ic_launcher_foreground);
                            builder.setTitle("登录");
                            builder.setMessage("还没有登录！");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ARouter.getInstance().build(Demo.AROUTE_PATH_LOGIN).navigation();
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
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

    @Subscribe(sticky = true)
    public void Event(String event){
        if (event.equals("home_data")){
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("zrf", "onRestart: ");
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
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        //注销eventbus
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
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

    private long keyBackTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - keyBackTime > 2000){
                keyBackTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            }else {
                finish();
                onDestroy();
            }
        }else if (event.getKeyCode() == KeyEvent.KEYCODE_HOME){
            return false;
        }
        return true;
    }
}