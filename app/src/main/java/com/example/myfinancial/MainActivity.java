package com.example.myfinancial;


import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.myfinancial.home.HomeFragment;
import com.example.myfinancial.inves.InvestFragment;
import com.example.myfinancial.more.MoreFragment;
import com.example.myfinancial.mymoney.MyMoneyFragment;
import com.example.net.bean.HomeBean;
import com.example.net.comm.CusComm;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {
    private HomeBean homeBean;
    private com.flyco.tablayout.CommonTabLayout tab;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<>();
    private ArrayList<CustomTabEntity> cusComms = new ArrayList<>();

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //获取对象
        list.add(new HomeFragment());
        list.add(new InvestFragment());
        list.add(new MyMoneyFragment());
        list.add(new MoreFragment());
        strings.add(getString(R.string.homeTool));
        strings.add(getString(R.string.invesTtool));
        strings.add(getString(R.string.myMoneyTool));
        strings.add(getString(R.string.moreTool));

        cusComms.add(new CusComm(strings.get(0), R.drawable.select_home, R.drawable.unselect_home));
        cusComms.add(new CusComm(strings.get(1), R.drawable.select_wallet, R.drawable.unselect_wallet));
        cusComms.add(new CusComm(strings.get(2), R.drawable.select_money, R.drawable.unselect_money));
        cusComms.add(new CusComm(strings.get(3), R.drawable.select_more, R.drawable.unselect_more));
        tab.setTextSelectColor(Color.BLUE);
        tab.setTextUnselectColor(Color.BLACK);
        tab.setTabData(cusComms, this, R.id.vp, list);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        String page = extras.getString("page");
        if (page!=null){
            tab.setCurrentTab(Integer.parseInt(page));
        }
    }

    @Override
    public void initView() {
        tab = (CommonTabLayout) findViewById(R.id.tab);


    }

    @Override
    public int getbandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH", "activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH", "activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH", "activity up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH onTouchEvent", "activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH onTouchEvent", "activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH onTouchEvent", "activity up");
                break;
        }
        return super.onTouchEvent(event);
    }
}